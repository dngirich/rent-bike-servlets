package com.belhard.service;

import com.belhard.domain.ParkingEntity;
import java.util.List;

public interface ParkingService {

    void deleteParking(Integer parkingId);

    ParkingEntity getParkingById(Integer parkingId);

    List<ParkingEntity> getAllParking();

    void addParking(ParkingEntity parking);

    void updateParking(ParkingEntity parking);

}
