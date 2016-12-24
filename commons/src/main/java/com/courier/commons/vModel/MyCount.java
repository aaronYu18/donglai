package com.courier.commons.vModel;

import java.io.Serializable;

/**
 * Created by admin on 2016/7/27.
 */
public class MyCount implements Serializable{
    private static final long serialVersionUID = -8422998314791535015L;
    private Integer totalNo;
    private Integer maxId;


    public Integer getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(Integer totalNo) {
        this.totalNo = totalNo;
    }

    public Integer getMaxId() {
        return maxId;
    }

    public void setMaxId(Integer maxId) {
        this.maxId = maxId;
    }
}
