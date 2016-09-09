package com.belhard.dao;

import java.util.List;
import com.belhard.dao.exceptions.DaoException;
import com.belhard.domain.SupportItemEntity;

public interface SupportItemDao {

    void createItem(SupportItemEntity supportItem) throws DaoException;

    List<SupportItemEntity> getAllItems() throws DaoException;

    List<SupportItemEntity> unperformedItem() throws DaoException;

    SupportItemEntity getItemById(Integer bikeId) throws DaoException;

    void repairItem(Integer bikeId) throws DaoException;
}
