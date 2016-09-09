package com.belhard.dao.factory;

import com.belhard.dao.mysql.MySqlBikeDao;
import com.belhard.dao.BikeDao;
import com.belhard.dao.ParkingDao;
import com.belhard.dao.RentItemDao;
import com.belhard.dao.SupportItemDao;
import com.belhard.dao.UserDao;
import com.belhard.dao.mysql.MySqlParkingDao;
import com.belhard.dao.mysql.MySqlRentItemDao;
import com.belhard.dao.mysql.MySqlSupportItemDao;
import com.belhard.dao.mysql.MySqlUserDao;

public class MySQLDaoFactory extends DaoFactory {

    private final UserDao userDao;
    private final BikeDao bikeDao;
    private final ParkingDao parkingDao;
    private final SupportItemDao supportItemDao;
    private final RentItemDao rentItemDao;

    public MySQLDaoFactory() {
        this.userDao = new MySqlUserDao();
        this.bikeDao = new MySqlBikeDao();
        this.parkingDao = new MySqlParkingDao();
        this.supportItemDao = new MySqlSupportItemDao();
        this.rentItemDao = new MySqlRentItemDao();
    }

    @Override
    public UserDao getUserDao() {
        return this.userDao;
    }

    @Override
    public BikeDao getBikeDao() {
        return this.bikeDao;
    }

    @Override
    public ParkingDao getParkingDao() {
        return this.parkingDao;
    }

    @Override
    public SupportItemDao getSupportItemDao() {
        return supportItemDao;
    }

    @Override
    public RentItemDao getRentItemDao() {
        return rentItemDao;
    }

}
