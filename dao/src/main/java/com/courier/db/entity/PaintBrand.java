package com.courier.db.entity;

import javax.persistence.*;

/**
 * Created by admin on 2016/6/17.
 */
@Table(name = "paint_brand")
public class PaintBrand extends BaseEntity {
    private static final long serialVersionUID = 3852251144337919354L;
    private Long id;
    private String name;
    private String englishName;
    private Byte isRoot;
    private Long carBrandId;
    private Long parentId;
    private Long carVendorId;
    private Byte isOpponent;



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
    @Column(name = "car_brand_id")
    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }
    @Column(name = "car_vendor_id")
    public Long getCarVendorId() {
        return carVendorId;
    }

    public void setCarVendorId(Long carVendorId) {
        this.carVendorId = carVendorId;
    }
    @Column(name = "english_name")
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
    @Column(name = "is_opponent")
    public Byte getIsOpponent() {
        return isOpponent;
    }

    public void setIsOpponent(Byte isOpponent) {
        this.isOpponent = isOpponent;
    }
    @Column(name = "is_root")
    public Byte getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Byte isRoot) {
        this.isRoot = isRoot;
    }
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
