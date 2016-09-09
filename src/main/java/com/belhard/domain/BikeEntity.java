package com.belhard.domain;

public class BikeEntity extends AbstractEntity {

    private static final long serialVersionUID = 6297385302078230511L;
    private Integer bikeId;
    private String type;
    private String marka;
    private String size;
    private Boolean avalaible;
    private Integer parkingId;

    public BikeEntity() {
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

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    @Override
    public String toString() {
        return "BikeEntity{" + "bikeId=" + bikeId + ", type=" + type + ", marka="
                + marka + ", size=" + size + ", avalaible=" + avalaible
                + ", parkingId=" + parkingId + '}';
    }

}
