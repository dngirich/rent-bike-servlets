package com.belhard.dao.factory;

import com.belhard.dao.BikeDao;
import com.belhard.dao.ParkingDao;
import com.belhard.dao.RentItemDao;
import com.belhard.dao.SupportItemDao;
import com.belhard.dao.UserDao;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao getUserDao();

    public abstract BikeDao getBikeDao();

    public abstract ParkingDao getParkingDao();

    public abstract SupportItemDao getSupportItemDao();
    public abstract RentItemDao getRentItemDao();
    

    static {
        daoFactory = new MySQLDaoFactory();
    }

    public static DaoFactory getFactory() {
        return daoFactory;
    }

}
