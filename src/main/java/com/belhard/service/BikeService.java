package com.belhard.service;

import com.belhard.domain.BikeEntity;
import java.util.List;

public interface BikeService {

    void deleteBike(Integer bikeid);

    BikeEntity getBikeById(Integer bikeid);

    List<BikeEntity> getAllBikes();

    void createBike(BikeEntity bike);

    void updateBike(BikeEntity bike);

    List<BikeEntity> showAvalaibleBike();

    void rentBike(Integer bikeid, Integer userId);

    void returnBike(Integer bikeid, Integer userId);

    List<BikeEntity> showBikeByParkingId(Integer parkingId);

}
