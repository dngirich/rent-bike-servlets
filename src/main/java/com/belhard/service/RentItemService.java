package com.belhard.service;

import com.belhard.domain.RentItemEntity;
import java.util.List;

public interface RentItemService {

    List<RentItemEntity> historyRent(Integer userId);

    RentItemEntity findTakenByUser(Integer userId);
}
