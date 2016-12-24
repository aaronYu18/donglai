package com.courier.core.service;

import com.courier.db.dao.PaintBrandMapper;
import com.courier.db.dao.crud.BaseManager;
import com.courier.db.dao.crud.BaseMapper;
import com.courier.db.entity.FormulaItem;
import com.courier.db.entity.PaintBrand;
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
public class PaintBrandService extends BaseManager<PaintBrand> {
    private static final Logger logger = LoggerFactory.getLogger(PaintBrandService.class);

    @Autowired private PaintBrandMapper paintBrandMapper;
    @Override public PaintBrand getEntity() { return new PaintBrand(); }
    @Override public BaseMapper<PaintBrand> getBaseMapper() { return paintBrandMapper; }




    public PaintBrand findByName(String name) {
        if(StringUtils.isEmpty(name)) return null;

        return paintBrandMapper.findByName(name);

    }

    public PaintBrand findByParentIdAndCarVendorName(Long parentId, String carVendor) {
        if(parentId == null || parentId == 0l || StringUtils.isEmpty(carVendor)) return null;

        return paintBrandMapper.findByParentIdAndCarVendorName(parentId, carVendor);
    }

    public List<Float> findPerByCNoAndVM(String color_num, String car_vendor, String car_model) {
        if(StringUtils.isEmpty(color_num) || StringUtils.isEmpty(car_vendor)) return null;

        return paintBrandMapper.findPerByCNoAndVM(color_num, car_vendor, car_model);
    }
}
