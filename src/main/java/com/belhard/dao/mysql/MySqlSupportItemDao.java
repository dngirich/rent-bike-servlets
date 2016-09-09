package com.belhard.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.belhard.dao.SupportItemDao;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.SupportItemEntity;
import com.belhard.dao.mysql.db.ConnectionPool;
import com.belhard.dao.mysql.db.ResultSetConverter;

public class MySqlSupportItemDao implements SupportItemDao {


    @Override
    public void createItem (SupportItemEntity supportItem) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();

            statement = connection.prepareStatement("insert into SupportItem(fk_Bikes_id,description,status) values (?, ?, ?)");
            statement.setInt(1, supportItem.getBikeId());
            statement.setString(2, supportItem.getDescription());
            statement.setBoolean(3, supportItem.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public List<SupportItemEntity> getAllItems() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<SupportItemEntity> result = new ArrayList<>();

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from SupportItem");
            set = statement.executeQuery();

            while (set.next()) {
                SupportItemEntity entity = ResultSetConverter.createSupportItemEntity(set);
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
    public List<SupportItemEntity> unperformedItem() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<SupportItemEntity> result = new ArrayList<>();

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from SupportItem where status=0");
            set = statement.executeQuery();

            while (set.next()) {
                SupportItemEntity entity = ResultSetConverter.createSupportItemEntity(set);
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
    public SupportItemEntity getItemById(Integer bikeId) throws DaoException {
        if (bikeId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from SupportItem where fk_Bikes_id=?");
            statement.setInt(1, bikeId);
            set = statement.executeQuery();

            if (set.next()) {
                SupportItemEntity entity = ResultSetConverter.createSupportItemEntity(set);
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
    public void repairItem(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("update SupportItem set status=true where fk_Bikes_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }
}
