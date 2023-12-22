package com.ecommerce.app.dto;

import com.ecommerce.app.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChartItemDTO {
    private String id;
    private String chartId;
    private Product product;
    private Integer quantity;
    private Integer total;
}
