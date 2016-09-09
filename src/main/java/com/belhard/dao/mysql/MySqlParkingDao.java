package com.belhard.dao.mysql;

import com.belhard.dao.ParkingDao;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.ParkingEntity;
import com.belhard.dao.mysql.db.ConnectionPool;
import com.belhard.dao.mysql.db.ResultSetConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlParkingDao implements ParkingDao {

    @Override
    public void addParking(ParkingEntity parking) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();

            statement = connection.prepareStatement("insert into Parking(street) values (?)");
            statement.setString(1, parking.getStreet());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public void deleteParking(Integer parkingId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("delete from Parking where id=?");
            statement.setInt(1, parkingId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public void updateParking(ParkingEntity parking) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("update Parking set street=? where id=?");
            statement.setString(1, parking.getStreet());
            statement.setInt(2, parking.getParkingId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public List<ParkingEntity> getAllParking() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<ParkingEntity> result = new ArrayList<>();

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from Parking order by id asc");
            set = statement.executeQuery();

            while (set.next()) {
                ParkingEntity entity = ResultSetConverter.createParkingEntity(set);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }

        return result;
    }

    @Override
    public ParkingEntity getParkingById(Integer parkingId) throws DaoException {
        if (parkingId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from Parking where id=?");
            statement.setInt(1, parkingId);
            set = statement.executeQuery();

            if (set.next()) {
                ParkingEntity entity = ResultSetConverter.createParkingEntity(set);
                return entity;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }

        return null;
    }
}
