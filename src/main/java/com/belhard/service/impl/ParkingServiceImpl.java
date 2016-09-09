package com.belhard.service.impl;

import com.belhard.service.*;
import com.belhard.dao.ParkingDao;
import com.belhard.dao.factory.DaoFactory;
import com.belhard.domain.ParkingEntity;
import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    private ParkingDao parkingDao = DaoFactory.getFactory().getParkingDao();

    @Override
    public void deleteParking(Integer id) {
        parkingDao.deleteParking(id);
    }

    @Override
    public ParkingEntity getParkingById(Integer id) {
        return parkingDao.getParkingById(id);
    }

    @Override
    public List<ParkingEntity> getAllParking() {
        return parkingDao.getAllParking();
    }

    @Override
    public void addParking(ParkingEntity parking) {
        parkingDao.addParking(parking);
    }

    @Override
    public void updateParking(ParkingEntity parking) {
        parkingDao.updateParking(parking);
    }

}
