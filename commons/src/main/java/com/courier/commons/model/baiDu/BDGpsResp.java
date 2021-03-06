package com.courier.commons.model.baiDu;

import java.io.Serializable;

/**
 * http请求操作工具类
 *
 */
public class BDGpsResp implements Serializable{

    private static final long serialVersionUID = -1848135644226822786L;

    public static class Base implements Serializable{
        private static final long serialVersionUID = 2642610206020542420L;
        private String status;
        private Result result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    public static class Result implements Serializable{
        private static final long serialVersionUID = -509730044267179498L;
        private Location location;
        private Integer precise;
        private Integer confidence;
        private String level;

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Integer getPrecise() {
            return precise;
        }

        public void setPrecise(Integer precise) {
            this.precise = precise;
        }

        public Integer getConfidence() {
            return confidence;
        }

        public void setConfidence(Integer confidence) {
            this.confidence = confidence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
    public static class Location implements Serializable{
        private static final long serialVersionUID = -5382652208941118790L;
        private Double lng;
        private Double lat;

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }
    }


}


















