package com.courier.datacenter.thread;

import com.courier.core.service.FormulaItemService;
import com.courier.core.service.FormulaService;
import com.courier.core.service.PaintBrandService;
import com.courier.db.vmodel.VFormulaItem;
import com.courier.db.entity.Formula;
import com.courier.db.entity.FormulaItem;
import com.courier.db.entity.PaintBrand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by admin on 2016/1/19.
 */
@Transactional
public class FormulaDeal implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(FormulaDeal.class);
    private static Logger jobLogger = LoggerFactory.getLogger("COPY_LOG");

    private final String NORMAL = "normal";
    private final String SIMILARITY = "similarity";

    private FormulaService formulaService;
    private FormulaItemService formulaItemService;
    private PaintBrandService paintBrandService;
    private Formula formula;

    public FormulaDeal(Formula formula, FormulaService formulaService, FormulaItemService formulaItemService, PaintBrandService paintBrandService) {
        this.formula = formula;
        this.formulaService = formulaService;
        this.formulaItemService = formulaItemService;
        this.paintBrandService = paintBrandService;
    }

    @Override
    public void run() {
        if(formula == null) return;
        Set<PaintBrand> sets = new HashSet<PaintBrand>();

        String onwings = "onwings高飞";
        String fixs = "fixs";
        String setType = SIMILARITY;
        String type = formula.getType();
        String carVendor = formula.getCarVendor();
        String paint_brand = formula.getPaintBrand();
        PaintBrand paintBrand = paintBrandService.findByName(paint_brand);
        if(paintBrand == null){
            jobLogger.error("paint brand not exist, paint brand name is {}", paint_brand);
            return;
        }


        // 正常的只复制现有需求提的
//        if(NORMAL.equalsIgnoreCase(type)){
            // todo 第一种情况（包含第二种情况）
            if(paint_brand.equalsIgnoreCase(onwings))
                sets.add(paintBrandService.findByName(fixs));

            // todo 第三种情况
            Long parent_id = paintBrand.getParentId();
            PaintBrand related = paintBrandService.get(parent_id);
            if(related != null && related.getName().equalsIgnoreCase(onwings))
                sets.add(paintBrandService.findByName(fixs));

            // todo 第四种情况
            if(paint_brand.equalsIgnoreCase(fixs))
                sets.add(paintBrandService.findByName(onwings));
//        }

        // 高相似还需要复制以前未复制的
        if(SIMILARITY.equalsIgnoreCase(type))
            sets.addAll(getRelatedPaintBrandNew(carVendor, paintBrand));

        // todo 如果配方为正常配方，则需要检查配方相似度
        if(NORMAL.equalsIgnoreCase(type)){
            List<Formula> similarityFormulas = getTopSimilarityFormulas(formula);
            if(CollectionUtils.isEmpty(similarityFormulas)){ setType = NORMAL;}
        }


        List<Long> needDelIds = new ArrayList<Long>();
        Iterator<PaintBrand> iterator = sets.iterator();
        while (iterator.hasNext()) {
            PaintBrand relatedP = iterator.next();
            if(relatedP != null) {
                Long buildId = copyFormula(relatedP, paintBrand, setType);
                if(buildId != null) needDelIds.add(buildId);
            }
        }

        if(!CollectionUtils.isEmpty(needDelIds)){
            // TODO: 2016/7/18 删除复制的无效记录
            for(Long id : needDelIds){
                formulaItemService.deleteByFId(id);
                formulaService.deleteById(id);
            }
        }
    }

    private Long copyFormula(PaintBrand related, PaintBrand paintBrand, String setType) {
        Formula dbFormula = formula.clone();
        Long sourceId = dbFormula.getId();

        dbFormula.setId(null);
        dbFormula.setPaintBrand(related.getName());
        dbFormula.setPaintBrandEn(related.getEnglishName());

        Date createTime = dbFormula.getCreateTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.DATE, 1);
        dbFormula.setCreateTime(calendar.getTime());

        dbFormula.setType(setType);
        dbFormula.setParentId(sourceId.toString());

        formulaService.save(dbFormula);

        List<FormulaItem> items = formulaItemService.findByFid(sourceId);
        if(!CollectionUtils.isEmpty(items)){
            for(FormulaItem item : items){
                if(item == null) continue;
                FormulaItem newItem = new FormulaItem();
                Long mId = null;
                if(related.getParentId() == null){
                    try{
                    mId = formulaItemService.findMBy3Key(item.getMasterbatchId(), paintBrand.getId(), related.getId());
                    }catch (Exception e){
                        logger.error("find masterBatch by 3 keys failed, mId {}, oldPid {}, newPid {}, exception is {}", item.getMasterbatchId(), paintBrand.getId(), related.getId(),
                                e.getMessage());
                        mId = formulaItemService.findMBy2Key(item.getMasterbatchId(), paintBrand.getId());
                    }
                }else {
                    mId = formulaItemService.findMBy2Key(item.getMasterbatchId(), paintBrand.getId());
                }

                if(mId == null) {
                    jobLogger.info("copy formula failed, error is : The corresponding color master does not exist, formulaId is {}, paint brand is {}, new paint brand is {}",
                            sourceId, paintBrand.getName(), related.getName());
                    return dbFormula.getId();
                }
                newItem.setFormulaId(dbFormula.getId());
                newItem.setMasterbatchId(mId);
                newItem.setDosage(item.getDosage());
                newItem.setNumber(0l);

                formulaItemService.save(newItem);
            }
        }

        jobLogger.info("old formulaId : {}, old paintBrand : '{}', new formulaId : {}, new paintBrand : '{}'", sourceId, paintBrand.getName(), dbFormula.getId(), related.getName());
        return null;
    }


    private List<PaintBrand> getRelatedPaintBrandNew(String carVendor, PaintBrand paintBrand) {
        if (paintBrand == null) return null;
        PaintBrand related = null;
        List<PaintBrand> list = new ArrayList<PaintBrand>();

        Long parent_id = paintBrand.getParentId();
        if (parent_id == null) {
            if (paintBrand.getIsRoot() == 1) {   //找出高飞对应的纯正/OEM
                related = paintBrandService.findByParentIdAndCarVendorName(paintBrand.getId(), carVendor);
                if (related != null)
                    list.add(related);                  //复制为纯正

                list.add(paintBrandService.findByName("矩阵色彩"));   //复制为赛浪
                list.add(paintBrandService.findByName("沃德姆"));   //复制为快猫
            } else {//OEM对应高飞
                related = paintBrandService.findByName("onwings高飞");
                list.add(related);
            }
        } else {   //找到纯正对应的高飞
            related = paintBrandService.get(parent_id);
            list.add(related);
        }
        return list;
    }

    // todo 查找高相似配方列表
    public List<Formula> getTopSimilarityFormulas(Formula formula){

        List<Float> percents = paintBrandService.findPerByCNoAndVM(formula.getColorNum(), formula.getCarVendor(), formula.getCarModel());
        float percent = 95.0f;
        if(percents != null && percents.size() > 0)
            percent = percents.get(0);

        return getTopSimilarityFormulas(formula, percent);
    }


    public List<Formula> getTopSimilarityFormulas(Formula formula, float percent) {

        String colorNum = null;
        if(!formula.getColorNum().equals("") && formula.getColorNum()!= null){
            colorNum = formula.getColorNum();
        }else{
            String custom_fields = formula.getCustomFields();
            if(!org.springframework.util.StringUtils.isEmpty(custom_fields)){
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Map<String, String> map = mapper.readValue(custom_fields, Map.class);
                    colorNum = map.get("colorNum");
                } catch (IOException e) {
                    logger.error("map convert error");
                }
            }
        }
        List<Formula> formulaList = formulaService.getFormulaByColorNum(colorNum, formula.getId());
        List<Formula> retList = checkSimilarity(formula, formulaList, percent);
        return retList;
    }

    private List<Formula> checkSimilarity(Formula baseFormula, List<Formula> formulaList, float percent){
        if(CollectionUtils.isEmpty(formulaList) || baseFormula == null) return null;

        Map<String, Double> baseMap = buildMapByFormula(baseFormula.getId());

        List<Formula> calList = new ArrayList<Formula>();
        for(Formula obj : formulaList){
            Map<String, Double> itemMap = buildMapByFormula(obj.getId());
            double similarVal = calculateSimilary(baseMap, itemMap);
            if(similarVal >= percent)
                calList.add(obj);
        }

        return calList;
    }

    private Map<String, Double> buildMapByFormula(Long formulaId){

        List<VFormulaItem> formulaItems = formulaItemService.findVByFid(formulaId);
        Map<String, Double> result = new HashMap<String, Double>();
        double total = 0;
        if(formulaItems != null && formulaItems.size() > 0){
            for (VFormulaItem formulaItem : formulaItems) {
                total += total+formulaItem.getDosage();
            }

            for (VFormulaItem formulaItem : formulaItems) {
                String number = formulaItem.getNumber();

                List<String> similarNum = formulaItemService.findSimilarNum(number);
                if(similarNum != null && similarNum.size() > 0){
                    result.put(similarNum.get(0), formulaItem.getDosage()/total);
                }else{
                    result.put(number, formulaItem.getDosage()/total);
                }
            }
        }
        return result;
    }


    private static double calculateSimilary(Map<String, Double> mapFOne, Map<String, Double> mapFTwo) {
        float divisor = 0f;
        float dividend = 0f;

        Iterator<String> it = mapFOne.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            double one = mapFOne.get(key), two = 0f;

            if (mapFTwo.containsKey(key)) {
                two = mapFTwo.get(key);
                mapFTwo.remove(key);
            }
            divisor += Math.abs(one - two);
            dividend += one + two;
        }

        Iterator<String> itTwo = mapFTwo.keySet().iterator();
        while (itTwo.hasNext()) {
            String key = (String) itTwo.next();
            double one = 0f, two = mapFTwo.get(key);

            if (mapFOne.containsKey(key)) {
                one = mapFOne.get(key);
            }

            divisor += Math.abs(one - two);
            dividend += one + two;
        }
        return (1 - divisor / dividend)*100;
    }


}
