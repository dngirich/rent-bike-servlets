package com.belhard.beans;

import java.io.Serializable;

public class BikeBean implements Serializable {

    private static final long serialVersionUID = 6297385302078230511L;
    private Integer bikeId;
    private String type;
    private String marka;
    private String size;
    private Boolean avalaible;
    private Integer parkingId;

    public BikeBean() {
        super();
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public String getMarka() {
        return marka;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAvalaible(Boolean avalaible) {
        this.avalaible = avalaible;
    }

    public Boolean getAvalaible() {
        return avalaible;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    @Override
    public String toString() {
        return "BikeBean{" + "bikeId=" + bikeId + ", type=" + type + ", marka=" + marka + ", size=" + size + ", avalaible=" + avalaible + ", parkingId=" + parkingId + '}';
    }

}
