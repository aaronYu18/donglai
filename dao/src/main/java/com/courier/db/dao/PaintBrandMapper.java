package com.courier.db.dao;

import com.courier.db.dao.crud.BaseMapper;
import com.courier.db.dao.crud.CRUDTemplate;
import com.courier.db.dao.crud.ExtSqlProp;
import com.courier.db.dao.crud.SearchFilter;
import com.courier.db.entity.Formula;
import com.courier.db.entity.PaintBrand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by aaron_yu on 15/5/27.
 */
public interface PaintBrandMapper extends BaseMapper<PaintBrand> {
    @SelectProvider(type = CRUDTemplate.class, method = "get")
    @Override
    public PaintBrand get(PaintBrand obj);

    @SelectProvider(type = CRUDTemplate.class, method = "findBy")
    @Override
    public List<PaintBrand> findBy(PaintBrand obj, List<SearchFilter> searchFilterList, ExtSqlProp sqlProp, int pageNumber, int limit);

    @SelectProvider(type = CRUDTemplate.class, method = "findAll")
    @Override
    public List<PaintBrand> findAll(PaintBrand obj, List<SearchFilter> searchFilterList, ExtSqlProp sqlProp);

    PaintBrand findByName(@Param("name")String name);

    PaintBrand findByParentIdAndCarVendorName(@Param("parentId")Long parentId, @Param("carVendorName")String carVendorName);

    List<Float> findPerByCNoAndVM(@Param("color_num")String color_num, @Param("car_vendor")String car_vendor, @Param("car_model")String car_model);
}
