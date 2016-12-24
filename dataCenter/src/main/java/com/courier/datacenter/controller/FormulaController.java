package com.courier.datacenter.controller;

import com.courier.commons.util.DateUtil;
import com.courier.core.service.FormulaService;
import com.courier.datacenter.task.FormulaTask;
import com.courier.db.entity.Formula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by admin on 2015/10/28.
 */
@Controller
@RequestMapping(value = "/formula")
public class FormulaController {
    private static final Logger logger = LoggerFactory.getLogger(FormulaController.class);
    private static Logger jobLogger = LoggerFactory.getLogger("BACKUP_LOG");

    @Autowired
    FormulaTask formulaTask;
    @Autowired
    FormulaService formulaService;


    @ResponseBody
    @RequestMapping(value = "/copy/yesterday", method = RequestMethod.GET)
    public String copy(){
        formulaTask.yesterdayFCopy();
        return "deal success";
    }

    @ResponseBody
    @RequestMapping(value = "/copy/custom", method = RequestMethod.GET)
    public String customCopy(@DateTimeFormat(pattern = "yyyy-MM-dd") Date beginT,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endT){

        if(beginT == null || endT == null) return "Parameters cannot be empty";
        beginT = DateUtil.getBeginT(beginT);
        endT = DateUtil.getEndT(endT);

        formulaTask.customFCopy(beginT, endT);

        return "deal success";
    }

    @ResponseBody
    @RequestMapping(value = "/update/star", method = RequestMethod.GET)
    public String updateStar(){

        formulaTask.updateStar();

        return "deal success";
    }

    @ResponseBody
    @RequestMapping(value = "/copy/{formulaId}", method = RequestMethod.GET)
    public String updateStar(@PathVariable @NotNull Long formulaId){
        Formula formula = formulaService.get(formulaId);
        formulaTask.dealSingle(formula);

        return "deal success";
    }

    @ResponseBody
    @RequestMapping(value = "/calculateStar/{formulaId}", method = RequestMethod.GET)
    public String calculateStar(@PathVariable @NotNull Long formulaId){
        Formula formula = formulaService.get(formulaId);
        formulaTask.calculateStar(formula);

        return "deal success";
    }
}
