package com.courier.db.vmodel;

import java.io.Serializable;

/**
 * Created by admin on 2016/6/23.
 */
public class VFormulaItem implements Serializable{
    private static final long serialVersionUID = -4461843152627017404L;

    private String number;
    private double dosage;

    public VFormulaItem() {
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
