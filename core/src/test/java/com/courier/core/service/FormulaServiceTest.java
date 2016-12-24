package com.courier.core.service;

import com.courier.db.dao.crud.Page;
import com.courier.db.entity.Formula;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by bin on 2015/11/30.
 */
@ContextConfiguration(locations = {"classpath:applicationCoreContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaServiceTest {

    @Autowired
    FormulaService formulaService;

    @Test
    public void findByFiltersTest() throws Exception {
        Page<Formula> page =  formulaService.findByFilters(null, 1, 10);
        assertNotNull(page);
    }

}