package com.belhard.dao;

import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.ParkingEntity;
import java.util.List;

public interface ParkingDao {

    void addParking(ParkingEntity parking)throws DaoException;

    void deleteParking(Integer parkingId)throws DaoException;

    void updateParking(ParkingEntity parking)throws DaoException;

    List<ParkingEntity> getAllParking()throws DaoException;

    ParkingEntity getParkingById(Integer parkingId)throws DaoException;
}
