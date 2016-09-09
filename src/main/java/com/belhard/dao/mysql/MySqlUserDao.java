package com.belhard.dao.mysql;

import com.belhard.util.Role;
import com.belhard.domain.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.belhard.dao.UserDao;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.dao.mysql.db.ConnectionPool;
import com.belhard.dao.mysql.db.ResultSetConverter;

public class MySqlUserDao implements UserDao {

    @Override
    public UserEntity getUser(String login) throws DaoException {
        UserEntity entity = null;
        List<String> roles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectionPool.getPool().getConnection();

            statement = connection.prepareStatement("SELECT users.id,users.firstName,users.lastName, "
                    + "users.email,users.password,roles.roleName from users \n"
                    + "join users_to_roles on users.id = users_to_roles.fk_user_id\n"
                    + "join roles on users_to_roles.fk_role_id= roles.id\n"
                    + "where email=?");
            statement.setString(1, login);
            set = statement.executeQuery();

            if (set != null) {
                while (set.next()) {
                    roles.add(set.getString("roleName"));
                }
                if (set.previous()) {
                    entity = ResultSetConverter.createUserEntity(set);
                    entity.setRoles(roles);
                }
            }

            return entity;

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }

    }

    @Override
    public UserEntity getUser(String login, String password) throws DaoException {
        UserEntity user = getUser(login);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<UserEntity> getAllUsers() throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<UserEntity> result = new ArrayList<>();

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("SELECT users.id,users.firstName,users.lastName, users.email,users.password,roles.roleName from users \n"
                    + "join users_to_roles on users.id = users_to_roles.fk_user_id\n"
                    + "join roles on users_to_roles.fk_role_id= roles.id\n"
                    + "where roleName=\"client\"");
            set = statement.executeQuery();

            while (set.next()) {
                UserEntity entity = ResultSetConverter.createUserEntity(set);
                result.add(entity);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement, set);
        }

    }

    @Override
    public void updateUser(UserEntity user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("update Users set firstName=?, lastName=?, email=? , password=?"
                    + "where id=?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public UserEntity getUserById(Integer userId) throws DaoException {
        if (userId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("select * from Users where id=?");
            statement.setInt(1, userId);
            set = statement.executeQuery();

            if (set.next()) {
                UserEntity entity = ResultSetConverter.createUserEntity(set);
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
    public void delete(Integer userId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("delete from Users where id=?");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

    @Override
    public List<UserEntity> getAllSupports() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<UserEntity> result = new ArrayList<>();
        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("SELECT users.id,users.firstName,users.lastName, users.email,users.password,roles.roleName from users \n"
                    + "join users_to_roles on users.id = users_to_roles.fk_user_id\n"
                    + "join roles on users_to_roles.fk_role_id= roles.id\n"
                    + "where roleName=\"support\"");
            set = statement.executeQuery();

            while (set.next()) {
                UserEntity entity = ResultSetConverter.createUserEntity(set);
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
    public void addUser(UserEntity user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer idUsers;
        try {
            connection = ConnectionPool.getPool().getConnection();
            statement = connection.prepareStatement("insert into Users(firstName,lastName,email,password) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idUsers = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
                statement = connection.prepareStatement("insert into Users_to_Roles(fk_user_id,fk_role_id) values (?,?)");
                for (Role role : user.getRoles()) {
                    int roleId = role.getId();
                    statement.setInt(1, idUsers);
                    statement.setInt(2, roleId);
                }
                statement.execute();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            ConnectionPool.getPool().closeDbResources(connection, statement);
        }
    }

}
