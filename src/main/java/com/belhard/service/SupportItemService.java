package com.belhard.service;

import com.belhard.domain.SupportItemEntity;
import java.util.List;

public interface SupportItemService {

    void createItem(SupportItemEntity supportItem);

    List<SupportItemEntity> getAllItems();

    List<SupportItemEntity> unperformedItem();

    SupportItemEntity getItemById(Integer bikeId);

    void repairItem(Integer bikeId);
}
