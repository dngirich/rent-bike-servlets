package com.belhard.dao;

import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.RentItemEntity;
import java.util.List;

public interface RentItemDao {

    void createItem(RentItemEntity rentItem) throws DaoException;

    List<RentItemEntity> historyRent(Integer userId) throws DaoException;

    RentItemEntity findTakenByUser(Integer userId);

}
