package com.belhard.service.impl;

import com.belhard.service.*;
import com.belhard.dao.BikeDao;
import com.belhard.dao.factory.DaoFactory;
import java.util.List;
import com.belhard.dao.RentItemDao;
import com.belhard.domain.BikeEntity;
import com.belhard.domain.RentItemEntity;
import java.util.Date;

public class BikeServiceImpl implements BikeService {

    private BikeDao bikeDao = DaoFactory.getFactory().getBikeDao();
    private RentItemDao rentItemDao = DaoFactory.getFactory().getRentItemDao();

    @Override
    public void deleteBike(Integer id) {
        bikeDao.deleteBike(id);
    }

    @Override
    public BikeEntity getBikeById(Integer id) {
        return bikeDao.getBikeById(id);
    }

    @Override
    public List<BikeEntity> getAllBikes() {
        return bikeDao.getAllBikes();
    }
    @Override
    public void createBike(BikeEntity bike) {
        bikeDao.createBike(bike);
    }
    @Override
    public void updateBike(BikeEntity bike) {
        bikeDao.updateBike(bike);
    }
    @Override
    public List<BikeEntity> showAvalaibleBike() {
        return bikeDao.showAvalaibleBike();
    }
    @Override
    public void rentBike(Integer bikeid, Integer userId) {
        bikeDao.rentBike(bikeid);
        RentItemEntity rentItem = new RentItemEntity();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeid);
        rentItem.setStatus(false);
        rentItem.setDate(new Date());
        rentItemDao.createItem(rentItem);
    }
    @Override
    public void returnBike(Integer bikeid, Integer userId) {
        bikeDao.returnBike(bikeid);
        RentItemEntity rentItem = new RentItemEntity();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeid);
        rentItem.setStatus(true);
        rentItem.setDate(new Date());
        rentItemDao.createItem(rentItem);
    }
    @Override
    public List<BikeEntity> showBikeByParkingId(Integer id) {
        return bikeDao.showBikeByParkingId(id);
    }

}
