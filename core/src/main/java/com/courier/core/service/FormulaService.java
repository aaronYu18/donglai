package com.courier.core.service;

import com.courier.commons.constant.ParamKey;
import com.courier.commons.vModel.MyCount;
import com.courier.db.dao.FormulaMapper;
import com.courier.db.dao.crud.BaseManager;
import com.courier.db.dao.crud.BaseMapper;
import com.courier.db.dao.crud.Page;
import com.courier.db.entity.Formula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aaron_yu on 15/5/10.
 */
@Service
@Transactional
public class FormulaService extends BaseManager<Formula> {
    private static final Logger logger = LoggerFactory.getLogger(FormulaService.class);

    @Autowired private FormulaMapper formulaMapper;
    @Override public Formula getEntity() { return new Formula(); }
    @Override public BaseMapper<Formula> getBaseMapper() { return formulaMapper; }




    /**
     * 多条件查询  (pageNo PAGE_SIZE 不传默认查所有）
     */
    public Page findByFilters(Map<String, Object> filters, Integer pageNo, Integer pageSize){

        if (filters == null) filters = new HashMap<String, Object>();
        int total = 0;

        if (pageNo != null && pageSize != null) {
            if (pageNo < 1) pageNo = 1;
            filters.put(ParamKey.Page.NUM_KEY, (pageNo - 1) * pageSize);
            filters.put(ParamKey.Page.SIZE_KEY, pageSize);
        }
        List<Formula> list = formulaMapper.findByFilters(filters);

        return new Page<Formula>(list, total);
    }



    public List<Formula> findByFilters(Map<String, Object> filters){
        return formulaMapper.findByFilters(filters);
    }
    public MyCount countByFilters(Map<String, Object> filters){
        return formulaMapper.countByFilters(filters);
    }


    public int countSubByFilters() {
        return formulaMapper.countSubByFilters();
    }

    public List<Formula> findSubByFilters(int pageNo, int pageSize) {
        return formulaMapper.findSubByFilters(pageNo, pageSize);
    }

    public List<Formula> getFormulaByColorNum(String colorNum, Long id) {
        if(StringUtils.isEmpty(colorNum) || id ==null || id == 0l) return null;
        return formulaMapper.getFormulaByColorNum(colorNum, id);
    }

    public byte calculateStar(Long id, Long parentId) {
        if(id == null || id == 0 || parentId == null || parentId == 0 ) return 0;
        return formulaMapper.calculateStar(id, parentId);
    }

    public void deleteById(Long id) {
        formulaMapper.deleteById(id);
    }
}
