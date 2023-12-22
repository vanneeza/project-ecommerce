package com.ecommerce.app.repository;

import com.ecommerce.app.entity.ChartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartItemRepository extends JpaRepository<ChartItem, String> {
}
