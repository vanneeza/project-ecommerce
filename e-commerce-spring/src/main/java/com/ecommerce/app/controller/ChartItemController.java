package com.ecommerce.app.controller;


import com.ecommerce.app.dto.ChartItemDTO;
import com.ecommerce.app.entity.ChartItem;
import com.ecommerce.app.service.ChartItemService;
import com.ecommerce.app.utils.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.CHART_ITEM_PATH)
@Slf4j
public class ChartItemController {
    ChartItemService chartItemService;

    @Autowired
    public ChartItemController(ChartItemService chartItemService) {

        this.chartItemService = chartItemService;
    }

    @PostMapping
    public ChartItemDTO save(@RequestBody ChartItem chartItem){
        log.info("Received request to save ChartItem: {}", chartItem);
        return chartItemService.save(chartItem);
    }

    @GetMapping
    public List<ChartItem> getAll(){
        return chartItemService.getAll();
    }

    @GetMapping("/{chartItemId}")
    public Optional<ChartItem> getById(@PathVariable String chartItemId){
        return chartItemService.getById(chartItemId);
    }

    @PutMapping
    public ChartItemDTO update(@RequestBody ChartItem chartItem){
        return chartItemService.save(chartItem);
    }

    @DeleteMapping("/{chartItemId}")
    public Optional<ChartItem> deleted(@PathVariable String chartItemId){

        return chartItemService.delete(chartItemId);
    }
}