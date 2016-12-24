package com.courier.core.service;

import com.courier.db.dao.FormulaItemMapper;
import com.courier.db.dao.crud.BaseManager;
import com.courier.db.dao.crud.BaseMapper;
import com.courier.db.entity.FormulaItem;
import com.courier.db.vmodel.VFormulaItem;
import org.apache.activemq.transport.tcp.ExceededMaximumConnectionsException;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by aaron_yu on 15/5/10.
 */
@Service
@Transactional
public class FormulaItemService extends BaseManager<FormulaItem> {
    private static final Logger logger = LoggerFactory.getLogger(FormulaItemService.class);

    @Autowired private FormulaItemMapper formulaItemMapper;
    @Override public FormulaItem getEntity() { return new FormulaItem(); }
    @Override public BaseMapper<FormulaItem> getBaseMapper() { return formulaItemMapper; }




    /**
     * 多条件查询  (pageNo PAGE_SIZE 不传默认查所有）
     */
    public List<FormulaItem> findByFid(Long formulaId){
        if(formulaId == null || formulaId == 0l) return null;

        return formulaItemMapper.findByFid(formulaId);

    }

    public List<VFormulaItem> findVByFid(Long formulaId) {
        if(formulaId == null || formulaId == 0l) return null;

        return formulaItemMapper.findVByFid(formulaId);
    }

    public List<String> findSimilarNum(String number) {
        if(StringUtils.isEmpty(number)) return null;

        return formulaItemMapper.findSimilarNum(number);
    }

    public Long findMBy3Key(Long mId, Long oldPid, Long newPid) {

            return formulaItemMapper.findMBy3Key(mId, oldPid, newPid);

    }

    public Long findMBy2Key(Long mId, Long oldPid) {
        return formulaItemMapper.findMBy2Key(mId, oldPid);
    }

    public void deleteByFId(Long id) {
        formulaItemMapper.deleteByFId(id);
    }
}
