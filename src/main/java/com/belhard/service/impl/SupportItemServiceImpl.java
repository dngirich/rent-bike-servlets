package com.belhard.service.impl;

import com.belhard.service.*;
import com.belhard.dao.SupportItemDao;
import com.belhard.dao.factory.DaoFactory;
import com.belhard.domain.SupportItemEntity;
import java.util.List;

public class SupportItemServiceImpl implements SupportItemService {

    private SupportItemDao supportItemDao = DaoFactory.getFactory().getSupportItemDao();

    @Override
    public void createItem(SupportItemEntity supportItem) {
        supportItemDao.createItem(supportItem);
    }

    @Override
    public List<SupportItemEntity> getAllItems() {
        return supportItemDao.getAllItems();
    }

    @Override
    public List<SupportItemEntity> unperformedItem() {
        return supportItemDao.unperformedItem();
    }

    @Override
    public SupportItemEntity getItemById(Integer bikeId) {
        return supportItemDao.getItemById(bikeId);
    }

    @Override
    public void repairItem(Integer bikeId) {
        supportItemDao.repairItem(bikeId);
    }
}
