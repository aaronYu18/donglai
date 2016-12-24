package com.courier.db.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2016/6/17.
 */
@Table(name = "formula_item")
public class FormulaItem extends BaseEntity {
    private static final long serialVersionUID = 8735317819230626257L;
    private Long id;
    private Long formulaId;
    private Long number;
    private Long masterbatchId;
    private Double dosage;
    private Date deleted;



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

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
    @Column
    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }
    @Column(name = "formula_id")
    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }
    @Column(name = "masterbatch_id")
    public Long getMasterbatchId() {
        return masterbatchId;
    }

    public void setMasterbatchId(Long masterbatchId) {
        this.masterbatchId = masterbatchId;
    }
    @Column
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

}
