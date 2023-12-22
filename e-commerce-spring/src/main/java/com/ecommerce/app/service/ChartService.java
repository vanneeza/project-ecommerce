package com.ecommerce.app.service;

import com.ecommerce.app.entity.Chart;

import java.util.List;
import java.util.Optional;

public interface ChartService {
    Chart save(Chart chart);
    Optional<Chart> getById(String chartId);
    List<Chart> getAll();

    Chart update(Chart chart);
    void delete(String id);
}
