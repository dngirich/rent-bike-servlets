package com.belhard.beans;

import java.io.Serializable;
import java.util.Date;

public class RentItemBean implements Serializable {

    private static final long serialVersionUID = 6297383302078230511L;
    private int id;
    private int bikeId;
    private int userId;
    private Date date;
    private Boolean status;

    public RentItemBean() {
        super();
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RentItemBean{" + "id=" + id + ", bikeId=" + bikeId + ", userId=" + userId + ", date=" + date + ", status=" + status + '}';
    }

}
