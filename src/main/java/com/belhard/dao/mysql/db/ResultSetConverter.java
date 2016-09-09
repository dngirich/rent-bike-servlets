package com.belhard.dao.mysql.db;

import com.belhard.domain.BikeEntity;
import com.belhard.domain.ParkingEntity;
import com.belhard.domain.RentItemEntity;
import com.belhard.domain.SupportItemEntity;
import com.belhard.domain.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class ResultSetConverter {

    private ResultSetConverter() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static UserEntity createUserEntity(ResultSet set) throws SQLException {
        Integer userId = set.getInt("id");
        String firstName = set.getString("firstName");
        String lastName = set.getString("lastName");
        String email = set.getString("email");
        String password = set.getString("password");
        UserEntity entity = new UserEntity();

        entity.setId(userId);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setEmail(email);
        entity.setPassword(password);
        return entity;
    }

    public static BikeEntity createBikeEntity(ResultSet set) throws SQLException {
        Integer bikeid = set.getInt("id");
        String type = set.getString("type");
        String marka = set.getString("marka");
        String size = set.getString("size");
        Boolean avalaible = set.getBoolean("avalaible");
        Integer parking_id = set.getInt("fk_Parking_id");

        BikeEntity entity = new BikeEntity();

        entity.setBikeId(bikeid);
        entity.setType(type);
        entity.setMarka(marka);
        entity.setSize(size);
        entity.setAvalaible(avalaible);
        entity.setParkingId(parking_id);

        return entity;
    }

    public static ParkingEntity createParkingEntity(ResultSet set) throws SQLException {
        Integer parkingid = set.getInt("id");
        String street = set.getString("street");

        ParkingEntity entity = new ParkingEntity();

        entity.setParkingId(parkingid);
        entity.setStreet(street);
        return entity;
    }

    public static RentItemEntity createRentItemEntity(ResultSet set) throws SQLException {

        Integer rentItem_id = set.getInt("id");
        Integer bikes_id = set.getInt("fk_bikes_id");
        Integer users_id = set.getInt("fk_users_id");
        Date date = set.getTimestamp("date");
        Boolean status = set.getBoolean("status");

        RentItemEntity entity = new RentItemEntity();
        entity.setId(rentItem_id);
        entity.setBikeId(bikes_id);
        entity.setUserId(users_id);
        entity.setDate(date);
        entity.setStatus(status);
        return entity;

    }

    public static SupportItemEntity createSupportItemEntity(ResultSet set) throws SQLException {

        Integer supportItem_id = set.getInt("id");
        Integer bikes_id = set.getInt("fk_bikes_id");
        String description = set.getString("description");
        Boolean status = set.getBoolean("status");

        SupportItemEntity entity = new SupportItemEntity();

        entity.setId(supportItem_id);
        entity.setBikeId(bikes_id);
        entity.setDescription(description);
        entity.setStatus(status);
        return entity;

    }

}
