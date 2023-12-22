package com.ecommerce.app.service.impl;

import com.ecommerce.app.entity.Chart;
import com.ecommerce.app.entity.ChartItem;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.repository.ChartRepository;
import com.ecommerce.app.service.ChartItemService;
import com.ecommerce.app.service.ChartService;
import com.ecommerce.app.service.CustomerService;
import com.ecommerce.app.service.ProductService;
import com.ecommerce.app.utils.exception.InsufficientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class ChartServiceImpl implements ChartService {

    ChartRepository chartRepository;
    CustomerService customerService;
    ChartItemService  chartItemService;
    ProductService productService;


    @Autowired
    public ChartServiceImpl(ChartRepository chartRepository, CustomerService customerService, ChartItemService chartItemService, ProductService productService) {
        this.chartRepository = chartRepository;
        this.customerService = customerService;
        this.chartItemService = chartItemService;
        this.productService = productService;
    }

    @Override
    public Chart save(Chart chart) {
        Optional<Customer> customer = customerService.getById(chart.getCustomer().getId());
        chart.setCustomer(customer.get());
        Chart saveChart = chartRepository.save(chart);

        Integer totalPrice = 0;
        for (ChartItem chartItem : chart.getItems()){
            String productID = chartItem.getProduct().getId();
            Optional<Product> product = productService.getById(productID);
            int setStock = product.get().getStock() - chartItem.getQuantity();

            if (setStock >= 0 ){
                product.get().setStock(setStock);
            } else throw new InsufficientException(String.format("%s Stock is not enough to be bought", product.get().getName()));
            totalPrice = product.get().getPrice() * chartItem.getQuantity();

            chartItem.setPrice(totalPrice);
            chartItem.setProduct(product.get());
            chartItem.setChart(saveChart);

            chartItemService.save(chartItem);
        }

        log.info("Chart Service Logging = {}", chart);

        return saveChart;
    }

    @Override
    public Optional<Chart> getById(String chartId) {
        return chartRepository.findById(chartId);
    }

    @Override
    public List<Chart> getAll() {
        return chartRepository.findAll();
    }

    @Override
    public Chart update(Chart chart) {
        return chartRepository.save(chart);
    }

    @Override
    public void delete(String id) {
        chartRepository.deleteById(id);
    }
}
