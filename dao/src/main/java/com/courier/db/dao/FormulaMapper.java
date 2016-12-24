package com.courier.db.dao;

import com.courier.commons.vModel.MyCount;
import com.courier.db.dao.crud.BaseMapper;
import com.courier.db.dao.crud.CRUDTemplate;
import com.courier.db.dao.crud.ExtSqlProp;
import com.courier.db.dao.crud.SearchFilter;
import com.courier.db.entity.Formula;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by aaron_yu on 15/5/27.
 */
public interface FormulaMapper extends BaseMapper<Formula> {
    @SelectProvider(type = CRUDTemplate.class, method = "get")
    @Override
    public Formula get(Formula obj);

    @SelectProvider(type = CRUDTemplate.class, method = "findBy")
    @Override
    public List<Formula> findBy(Formula obj, List<SearchFilter> searchFilterList, ExtSqlProp sqlProp, int pageNumber, int limit);

    @SelectProvider(type = CRUDTemplate.class, method = "findAll")
    @Override
    public List<Formula> findAll(Formula Formula, List<SearchFilter> searchFilterList, ExtSqlProp sqlProp);

    MyCount countByFilters(Map<String, Object> filters);

    List<Formula> findByFilters(Map<String, Object> filters);

    int countSubByFilters();

    List<Formula> findSubByFilters(@Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

    List<Formula> getFormulaByColorNum(@Param("colorNum") String colorNum, @Param("id") Long id);

    byte calculateStar(@Param("id")Long id, @Param("parentId")Long parentId);

    void deleteById(@Param("id")Long id);
}
