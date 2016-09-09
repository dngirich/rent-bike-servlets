package com.belhard.beans;

import java.io.Serializable;

public class SupportItemBean implements Serializable {

    private static final long serialVersionUID = 6296383302078230511L;
    private int itemId;
    private int bikeId;
    private String description;
    private Boolean status;

    public SupportItemBean() {
        super();
    }

    public int getItemId() {
        return itemId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "SupportItemBean{" + "itemId=" + itemId + ", bikeId=" + bikeId + ", description=" + description + ", status=" + status + '}';
    }

}
