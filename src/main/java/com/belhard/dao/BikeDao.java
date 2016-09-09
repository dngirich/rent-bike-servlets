package com.belhard.dao;

import java.util.List;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.BikeEntity;

public interface BikeDao {

    void createBike(BikeEntity bike) throws DaoException;

    void deleteBike(Integer bikeId) throws DaoException;

    void updateBike(BikeEntity bike) throws DaoException;

    List<BikeEntity> getAllBikes() throws DaoException;

    BikeEntity getBikeById(Integer bikeId) throws DaoException;

    List<BikeEntity> showAvalaibleBike() throws DaoException;

    void rentBike(Integer bikeId) throws DaoException;

    void returnBike(Integer bikeId) throws DaoException;

    List<BikeEntity> showBikeByParkingId(Integer parkingId) throws DaoException;

}
