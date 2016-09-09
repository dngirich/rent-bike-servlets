package com.belhard.dao.mysql;

import com.belhard.dao.RentItemDao;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.RentItemEntity;
import com.belhard.dao.mysql.db.ConnectionPool;
import com.belhard.dao.mysql.db.ResultSetConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MySqlRentItemDao implements RentItemDao {


    @Override
    public void createItem(RentItemEntity rentItem) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();

            statement = connection.prepareStatement("insert into RentItem (fk_bikes_id,fk_users_id,date,status) values (?, ?, ?, ?)");
            statement.setInt(1, rentItem.getBikeId());
            statement.setInt(2, rentItem.getUserId());
            statement.setTimestamp(3, new Timestamp(rentItem.getDate().getTime()));
            statement.setBoolean(4, rentItem.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public List<RentItemEntity> historyRent(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<RentItemEntity> result = new ArrayList<>();

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from RentItem where fk_users_id =? order by date desc");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                RentItemEntity entity = ResultSetConverter.createRentItemEntity(set);
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
    public RentItemEntity findTakenByUser(Integer userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        RentItemEntity result = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("SELECT * FROM rentitem where fk_users_id=? order by date desc");
            statement.setInt(1, userId);
            set = statement.executeQuery();

            if (set.next()) {
                result = ResultSetConverter.createRentItemEntity(set);
                if (result.getStatus()) {
                    result = null;
                }
            }            
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }
        
        return result;
    }
}
