package com.belhard.service.factory;

import com.belhard.service.impl.ParkingServiceImpl;
import com.belhard.service.BikeService;
import com.belhard.service.ParkingService;
import com.belhard.service.RentItemService;
import com.belhard.service.SupportItemService;
import com.belhard.service.UserService;
import com.belhard.service.impl.BikeServiceImpl;
import com.belhard.service.impl.RentItemServiceImpl;
import com.belhard.service.impl.SupportItemServiceImpl;
import com.belhard.service.impl.UserServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory {

    private final UserService userService;
    private final BikeService bikeService;
    private final ParkingService parkingService;
    private final SupportItemService supportItemService;
    private final RentItemService rentItemService;

    public ServiceFactoryImpl() {
        this.userService = new UserServiceImpl();
        this.bikeService = new BikeServiceImpl();
        this.parkingService = new ParkingServiceImpl();
        this.supportItemService = new SupportItemServiceImpl();
        this.rentItemService = new RentItemServiceImpl();
    }

    @Override
    public UserService getUserService() {
        return this.userService;
    }

    @Override
    public BikeService getBikeService() {
        return this.bikeService;
    }

    @Override
    public ParkingService getParkingService() {
        return this.parkingService;
    }

    @Override
    public SupportItemService getSupportItemService() {
        return supportItemService;
    }

    @Override
    public RentItemService getRentItemService() {
        return rentItemService;
    }

}
