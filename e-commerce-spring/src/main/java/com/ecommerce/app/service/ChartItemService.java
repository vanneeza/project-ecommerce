package com.ecommerce.app.service;

import com.ecommerce.app.dto.ChartItemDTO;
import com.ecommerce.app.entity.ChartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ChartItemService {
    ChartItemDTO save(ChartItem chartItem);
    Optional<ChartItem> getById(String chartItemId);
    List<ChartItem> getAll();
    ChartItem update(ChartItem chartItem);
    Optional<ChartItem> delete(String id);
}
