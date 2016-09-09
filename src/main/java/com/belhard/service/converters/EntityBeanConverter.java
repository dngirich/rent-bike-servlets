package com.belhard.service.converters;

import com.belhard.beans.BikeBean;
import com.belhard.beans.ParkingBean;
import com.belhard.beans.RentItemBean;
import com.belhard.beans.SupportItemBean;
import com.belhard.beans.UserBean;
import com.belhard.domain.BikeEntity;
import com.belhard.domain.ParkingEntity;
import com.belhard.domain.RentItemEntity;
import com.belhard.domain.SupportItemEntity;
import com.belhard.domain.UserEntity;
import java.util.ArrayList;
import java.util.List;

public final class EntityBeanConverter {

    public static UserBean transformToUserBean(UserEntity entity) {
        UserBean userbean = new UserBean();
        userbean.setId(entity.getId());
        userbean.setFirstName(entity.getFirstName());
        userbean.setLastName(entity.getLastName());
        userbean.setEmail(entity.getEmail());
        userbean.setPassword(entity.getPassword());
        userbean.setRoles(entity.getRoles());
        return userbean;
    }

    public static List<UserBean> transformToUserBeans(List<UserEntity> entities) {
        List<UserBean> result = new ArrayList<>();
        for (UserEntity entity : entities) {
            UserBean bean = transformToUserBean(entity);
            result.add(bean);
        }
        return result;
    }

    public static BikeBean transformToBikeBean(BikeEntity entity) {
        BikeBean bikeBean = new BikeBean();
        bikeBean.setBikeId(entity.getBikeId());
        bikeBean.setMarka(entity.getMarka());
        bikeBean.setType(entity.getType());
        bikeBean.setSize(entity.getSize());
        bikeBean.setAvalaible(entity.getAvalaible());
        bikeBean.setParkingId(entity.getParkingId());
        return bikeBean;
    }

    public static List<BikeBean> transformToBikeBeans(List<BikeEntity> entities) {
        List<BikeBean> result = new ArrayList<>();
        for (BikeEntity entity : entities) {
            BikeBean bean = transformToBikeBean(entity);
            result.add(bean);
        }
        return result;
    }

    public static ParkingBean transformToParkingBean(ParkingEntity entity) {
        ParkingBean parkingBean = new ParkingBean();
        parkingBean.setParkingId(entity.getParkingId());
        parkingBean.setStreet(entity.getStreet());
        return parkingBean;
    }

    public static List<ParkingBean> transformToParkingBeans(List<ParkingEntity> entities) {
        List<ParkingBean> result = new ArrayList<>();
        for (ParkingEntity entity : entities) {
            ParkingBean bean = transformToParkingBean(entity);
            result.add(bean);
        }
        return result;
    }

    public static RentItemBean transformToRentItemBean(RentItemEntity entity) {
        RentItemBean rentItemBean = new RentItemBean();
        rentItemBean.setId(entity.getId());
        rentItemBean.setBikeId(entity.getBikeId());
        rentItemBean.setDate(entity.getDate());
        rentItemBean.setUserId(entity.getUserId());
        rentItemBean.setStatus(entity.getStatus());
        return rentItemBean;
    }

    public static List<RentItemBean> transformToRentItemBeans(List<RentItemEntity> entities) {
        List<RentItemBean> result = new ArrayList<>();
        for (RentItemEntity entity : entities) {
            RentItemBean bean = transformToRentItemBean(entity);
            result.add(bean);
        }
        return result;
    }

    public static SupportItemBean transformToSupportItemBean(SupportItemEntity entity) {
        SupportItemBean supportItemBean = new SupportItemBean();
        supportItemBean.setItemId(entity.getId());
        supportItemBean.setBikeId(entity.getBikeId());
        supportItemBean.setDescription(entity.getDescription());
        supportItemBean.setStatus(entity.getStatus());
        return supportItemBean;
    }

    public static List<SupportItemBean> transformToSupportItemBeans(List<SupportItemEntity> entities) {
        List<SupportItemBean> result = new ArrayList<>();
        for (SupportItemEntity entity : entities) {
            SupportItemBean bean = transformToSupportItemBean(entity);
            result.add(bean);
        }
        return result;
    }

}
