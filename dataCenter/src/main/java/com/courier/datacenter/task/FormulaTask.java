package com.courier.datacenter.task;

import com.courier.commons.util.DateUtil;
import com.courier.commons.util.thread.ThreadUtil;
import com.courier.commons.vModel.MyCount;
import com.courier.core.service.FormulaItemService;
import com.courier.core.service.FormulaService;
import com.courier.core.service.PaintBrandService;
import com.courier.datacenter.thread.FormulaDeal;
import com.courier.db.dao.crud.Page;
import com.courier.db.entity.Formula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by admin on 2015/10/22.
 */
@Component
public class FormulaTask {
    private static Logger jobLogger = LoggerFactory.getLogger("COPY_LOG");
    private static Logger updateSubLogger = LoggerFactory.getLogger("UPDATE_SUB_LOG");

    @Autowired private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired private FormulaService formulaService;
    @Autowired private FormulaItemService formulaItemService;
    @Autowired private PaintBrandService paintBrandService;
    private final int pageSize = 1000;


    // todo   复制前一天配方
    public void yesterdayFCopy(){
        jobLogger.info("begin to copy yesterday formula ......");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date endT = DateUtil.getEndT(calendar);
        Date beginT = DateUtil.getBeginT(calendar);


        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("beginT", DateUtil.toSeconds(beginT));
        filters.put("endT", DateUtil.toSeconds(endT));

        Page page = formulaService.findByFilters(filters, null, null);

        if(page == null || page.getResult() == null || page.getResult().size() == 0)
            jobLogger.info("yesterday none formula need to copy......");

        List<Formula> result = page.getResult();
        for (Formula formula : result){
            dealSingle(formula);
        }
    }

    // todo   自定义复制时间段
    public void customFCopy(Date beginT, Date endT){
        String beginTStr = "", endTStr = "";
        Map<String, Object> filters = new HashMap<String, Object>();
        if(beginT != null) {
            beginTStr = DateUtil.toSeconds(beginT);
            filters.put("beginT", beginTStr);
        }
        if (endT != null) {
            endTStr = DateUtil.toSeconds(endT);
            filters.put("endT", endTStr);
        }

        MyCount count = formulaService.countByFilters(filters);
        if(count == null) return;
        Integer totalNo = count.getTotalNo();
        Integer maxId = count.getMaxId();
        filters.put("maxId", maxId);
        jobLogger.info("begin to copy formula, begin time is {}, end time is {}, total num is {} ", beginTStr, endTStr, totalNo);


        int pageCount = (totalNo + pageSize - 1) / pageSize;

        for (int i = 1; i <= pageCount; i++) {
            filters.put("pageSize", pageSize);
            filters.put("pageNo", (i - 1) * pageSize);
            List<Formula> formulas = formulaService.findByFilters(filters);
            dealBatch(formulas);
        }
    }

    // todo 更新自配方星级
    public void updateStar() {
        int totalNum = formulaService.countSubByFilters();
        if(totalNum == 0) return;

        updateSubLogger.info("need to update formula number is {}", totalNum);
        int pageCount = (totalNum + pageSize - 1) / pageSize;

        for (int i = 1; i <= pageCount; i++) {
            List<Formula> formulas = formulaService.findSubByFilters((i - 1) * pageSize, pageSize);
            if(formulas != null && formulas.size() > 0){
                for(Formula formula : formulas){
                    calculateStar(formula);

                }
            }
        }
    }



    // todo 单个用户
    public void dealSingle(Formula formula) {
        if(formula == null || formula.getId() == null || formula.getId() == 0l) return;

        FormulaDeal formulaDeal = new FormulaDeal(formula, formulaService, formulaItemService, paintBrandService);
        Thread thread = new Thread(formulaDeal);
        threadPoolTaskExecutor.execute(thread);
    }

    private void dealBatch(List<Formula> formulas) {
        if (CollectionUtils.isEmpty(formulas)) return;

        for (Formula formula : formulas) {
            dealSingle(formula);
            ThreadUtil.doSleepByMicSec(500);
        }
    }

    public void calculateStar(Formula formula) {
        String parentId = formula.getParentId();
        if(!StringUtils.isEmpty(parentId)){
            Byte oldStar = formula.getStarCount();
            Long pId = Long.valueOf(parentId);
            Long id = formula.getId();
            byte starCount = formulaService.calculateStar(id, pId);
            formula.setStarCount(starCount);
            formulaService.update(formula);
            updateSubLogger.info("update formula star count, formula id is {}, old star count is {}, new star count is {}", id, oldStar, starCount);
        }
    }
}
