package com.courier.db.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2016/6/17.
 */
@Table(name = "formula")
public class Formula extends BaseEntity implements Cloneable{
    private static final long serialVersionUID = -6231076007657472715L;
    private Long id;
    private Long userId;
    private String number;
    private String carBrand;
    private String carBrandEn;
    private String carVendor;
    private String carVendorEn;
    private String carModel;
    private String carModelEn;
    private String paintBrand;
    private String paintBrandEn;
    private String color;
    private String englishColor;
    private String colorNum;
    private String year;
    private String remarks;
    private String remarksEn;
    private String image;
    private Integer commentCount;
    private Byte starCount;
    private Byte isVerified;
    private String customFields;
    private String category;
    private Date created;
    private Date deleted;
    private Integer rank;
    private Date verifiedDate;
    private String type;
    private Integer sumscore;
    private Integer countLike;
    private Integer countClick;
    private Integer countCollect;
    private String lightness;
    private String lightnessEn;
    private String saturation;
    private String saturationEn;
    private String hue;
    private String hueEn;
    private String parentId;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "car_brand")
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    @Column(name = "car_brand_en")
    public String getCarBrandEn() {
        return carBrandEn;
    }

    public void setCarBrandEn(String carBrandEn) {
        this.carBrandEn = carBrandEn;
    }
    @Column(name = "car_model")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    @Column(name = "car_model_en")
    public String getCarModelEn() {
        return carModelEn;
    }

    public void setCarModelEn(String carModelEn) {
        this.carModelEn = carModelEn;
    }
    @Column(name = "car_vendor")
    public String getCarVendor() {
        return carVendor;
    }

    public void setCarVendor(String carVendor) {
        this.carVendor = carVendor;
    }
    @Column(name = "car_vendor_en")
    public String getCarVendorEn() {
        return carVendorEn;
    }

    public void setCarVendorEn(String carVendorEn) {
        this.carVendorEn = carVendorEn;
    }
    @Column
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Column
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Column(name = "color_num")
    public String getColorNum() {
        return colorNum;
    }

    public void setColorNum(String colorNum) {
        this.colorNum = colorNum;
    }
    @Column(name = "comment_count")
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    @Column(name = "count_click")
    public Integer getCountClick() {
        return countClick;
    }

    public void setCountClick(Integer countClick) {
        this.countClick = countClick;
    }
    @Column(name = "count_collect")
    public Integer getCountCollect() {
        return countCollect;
    }

    public void setCountCollect(Integer countCollect) {
        this.countCollect = countCollect;
    }
    @Column(name = "count_like")
    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    @Column(name = "custom_fields")
    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
    @Column(name = "english_color")
    public String getEnglishColor() {
        return englishColor;
    }

    public void setEnglishColor(String englishColor) {
        this.englishColor = englishColor;
    }
    @Column
    public String getHue() {
        return hue;
    }

    public void setHue(String hue) {
        this.hue = hue;
    }
    @Column(name = "hue_en")
    public String getHueEn() {
        return hueEn;
    }

    public void setHueEn(String hueEn) {
        this.hueEn = hueEn;
    }
    @Column
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Column(name = "is_verified")
    public Byte getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Byte isVerified) {
        this.isVerified = isVerified;
    }
    @Column
    public String getLightness() {
        return lightness;
    }

    public void setLightness(String lightness) {
        this.lightness = lightness;
    }
    @Column(name = "lightness_en")
    public String getLightnessEn() {
        return lightnessEn;
    }

    public void setLightnessEn(String lightnessEn) {
        this.lightnessEn = lightnessEn;
    }
    @Column
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    @Column(name = "paint_brand")
    public String getPaintBrand() {
        return paintBrand;
    }

    public void setPaintBrand(String paintBrand) {
        this.paintBrand = paintBrand;
    }
    @Column(name = "paint_brand_en")
    public String getPaintBrandEn() {
        return paintBrandEn;
    }

    public void setPaintBrandEn(String paintBrandEn) {
        this.paintBrandEn = paintBrandEn;
    }
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @Column
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
    @Column
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name = "remarks_en")
    public String getRemarksEn() {
        return remarksEn;
    }

    public void setRemarksEn(String remarksEn) {
        this.remarksEn = remarksEn;
    }
    @Column
    public String getSaturation() {
        return saturation;
    }

    public void setSaturation(String saturation) {
        this.saturation = saturation;
    }
    @Column(name = "saturation_en")
    public String getSaturationEn() {
        return saturationEn;
    }

    public void setSaturationEn(String saturationEn) {
        this.saturationEn = saturationEn;
    }
    @Column(name = "star_count")
    public Byte getStarCount() {
        return starCount;
    }

    public void setStarCount(Byte starCount) {
        this.starCount = starCount;
    }
    @Column
    public Integer getSumscore() {
        return sumscore;
    }

    public void setSumscore(Integer sumscore) {
        this.sumscore = sumscore;
    }
    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name="user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "verified_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }
    @Column
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public Formula clone(){
        Formula o = null;
        try{
            o = (Formula)super.clone();
            }catch(CloneNotSupportedException e){
                e.printStackTrace();
            }
        return o;
    }

}
