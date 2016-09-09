package com.belhard.dao.mysql;

import com.belhard.dao.BikeDao;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.BikeEntity;
import com.belhard.dao.mysql.db.ConnectionPool;
import com.belhard.dao.mysql.db.ResultSetConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlBikeDao implements BikeDao {

    @Override
    public void createBike(BikeEntity bike) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();

            statement = connection.prepareStatement("insert into Bikes(type,marka,size,avalaible,fk_parking_id) values (?, ?, ?, ?, ?)");
            statement.setString(1, bike.getType());
            statement.setString(2, bike.getMarka());
            statement.setString(3, bike.getSize());
            statement.setBoolean(4, bike.getAvalaible());
            statement.setInt(5, bike.getParkingId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public void deleteBike(Integer bikeId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("delete from Bikes where id=?");
            statement.setInt(1, bikeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public void updateBike(BikeEntity bike) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("update Bikes set type=?, marka=?, size=?, avalaible=?, fk_parking_id=? where id=?");
            statement.setString(1, bike.getType());
            statement.setString(2, bike.getMarka());
            statement.setString(3, bike.getSize());
            statement.setBoolean(4, bike.getAvalaible());
            statement.setInt(5, bike.getParkingId());
            statement.setInt(6, bike.getBikeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public List<BikeEntity> getAllBikes() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<BikeEntity> result = new ArrayList<>();

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from Bikes");
            set = statement.executeQuery();

            while (set.next()) {
                BikeEntity entity = ResultSetConverter.createBikeEntity(set);
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
    public BikeEntity getBikeById(Integer bikeId) throws DaoException {
        if (bikeId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from Bikes where id=?");
            statement.setInt(1, bikeId);
            set = statement.executeQuery();

            if (set.next()) {
                BikeEntity entity = ResultSetConverter.createBikeEntity(set);
                return entity;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }

        return null;
    }

    @Override
    public List<BikeEntity> showAvalaibleBike() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<BikeEntity> result = new ArrayList<>();
        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from Bikes where avalaible=true");
            set = statement.executeQuery();

            while (set.next()) {
                BikeEntity entity = ResultSetConverter.createBikeEntity(set);
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
    public void rentBike(Integer bikeId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("update Bikes set avalaible=false where id=?");
            statement.setInt(1, bikeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public void returnBike(Integer bikeId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("update Bikes set avalaible=true where id=?");
            statement.setInt(1, bikeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public List<BikeEntity> showBikeByParkingId(Integer parkingId) throws DaoException {

        if (parkingId == null) {
            return null;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<BikeEntity> result = new ArrayList<>();
        try {
            connection = ConnectionPool.getPool().getConnection();

            statement = connection.prepareStatement("select * from Bikes where fk_parking_id=?");
            statement.setInt(1, parkingId);

            set = statement.executeQuery();

            while (set.next()) {
                BikeEntity entity = ResultSetConverter.createBikeEntity(set);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }

        return result;

    }

}
