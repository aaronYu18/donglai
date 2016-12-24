package com.courier.db.dao;

import com.courier.db.dao.crud.BaseMapper;
import com.courier.db.dao.crud.CRUDTemplate;
import com.courier.db.dao.crud.ExtSqlProp;
import com.courier.db.dao.crud.SearchFilter;
import com.courier.db.entity.Formula;
import com.courier.db.entity.FormulaItem;
import com.courier.db.vmodel.VFormulaItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by aaron_yu on 15/5/27.
 */
public interface FormulaItemMapper extends BaseMapper<FormulaItem> {
    @SelectProvider(type = CRUDTemplate.class, method = "get")
    @Override
    public FormulaItem get(FormulaItem obj);

    @SelectProvider(type = CRUDTemplate.class, method = "findBy")
    @Override
    public List<FormulaItem> findBy(FormulaItem obj, List<SearchFilter> searchFilterList, ExtSqlProp sqlProp, int pageNumber, int limit);

    @SelectProvider(type = CRUDTemplate.class, method = "findAll")
    @Override
    public List<FormulaItem> findAll(FormulaItem obj, List<SearchFilter> searchFilterList, ExtSqlProp sqlProp);

    List<FormulaItem> findByFid(@Param("formulaId")Long formulaId);

    List<VFormulaItem> findVByFid(@Param("formulaId")Long formulaId);

    List<String> findSimilarNum(@Param("number")String number);

    Long findMBy3Key(@Param("mId")Long mId, @Param("oldPid")Long oldPid, @Param("newPid")Long newPid);

    Long findMBy2Key(@Param("mId")Long mId, @Param("oldPid")Long oldPid);

    void deleteByFId(@Param("fId")Long fId);
}
