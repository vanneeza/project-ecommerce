package com.ecommerce.app.service.impl;

import com.ecommerce.app.dto.ChartItemDTO;
import com.ecommerce.app.entity.Chart;
import com.ecommerce.app.entity.ChartItem;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.repository.ChartItemRepository;
import com.ecommerce.app.service.ChartItemService;
import com.ecommerce.app.service.ChartService;
import com.ecommerce.app.utils.exception.InsufficientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartItemServiceImpl implements ChartItemService {
    ChartItemRepository chartItemRepository;
    ProductServiceImpl productService;


    @Autowired
    public ChartItemServiceImpl(ChartItemRepository chartItemRepository, ProductServiceImpl productService) {
        this.chartItemRepository = chartItemRepository;
        this.productService = productService;
    }

    @Override
    public ChartItemDTO save(ChartItem chartItem) {
        Optional<Product> product = productService.getById(chartItem.getProduct().getId());
        ChartItemDTO chartItemDTO = new ChartItemDTO();
        int subTotal = product.orElse(new Product()).getPrice() * chartItem.getQuantity();
        chartItem.setPrice(subTotal);
        int setStock = product.get().getStock() - chartItem.getQuantity();

        if (setStock >= 0 ){
            product.get().setStock(setStock);
        } else throw new InsufficientException(String.format("%s Stock is not enough to be bought", product.get().getName()));

        ChartItem chartItem1 = chartItemRepository.save(chartItem);
        chartItemDTO.setId(chartItem1.getId());
        chartItemDTO.setChartId(chartItem.getChart().getId());
        chartItemDTO.setQuantity(chartItem.getQuantity());
        chartItemDTO.setTotal(subTotal);
        product.ifPresent(chartItemDTO::setProduct);



        return chartItemDTO;
    }

    @Override
    public Optional<ChartItem> getById(String chartItemId) {
        return chartItemRepository.findById(chartItemId);
    }

    @Override
    public List<ChartItem> getAll() {
        return chartItemRepository.findAll();
    }

    @Override
    public ChartItem update(ChartItem chartItem) {
        return chartItemRepository.save(chartItem);
    }

    @Override
    public Optional<ChartItem> delete(String id) {
        Optional<ChartItem> chartItem = chartItemRepository.findById(id);
        chartItemRepository.deleteById(id);

        return chartItem;
    }
}
