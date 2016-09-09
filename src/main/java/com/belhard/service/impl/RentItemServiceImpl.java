package com.belhard.service.impl;

import com.belhard.dao.RentItemDao;
import com.belhard.dao.factory.DaoFactory;
import com.belhard.domain.RentItemEntity;
import com.belhard.service.RentItemService;
import java.util.List;

public class RentItemServiceImpl implements RentItemService {

    private RentItemDao rentItemDao = DaoFactory.getFactory().getRentItemDao();

    @Override
    public List<RentItemEntity> historyRent(Integer userId) {
        return rentItemDao.historyRent(userId);
    }

    @Override
    public RentItemEntity findTakenByUser(Integer userId) {
        return rentItemDao.findTakenByUser(userId);
    }
}
