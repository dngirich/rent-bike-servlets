package com.belhard.beans;

import java.io.Serializable;

public class ParkingBean implements Serializable {

    private static final long serialVersionUID = 6297385302078230511L;
    private Integer parkingId;
    private String street;

    public ParkingBean() {
        super();
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "ParkingBean{" + "parkingId=" + parkingId + ", street=" + street + '}';
    }
}
