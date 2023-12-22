package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Chart;
import com.ecommerce.app.service.ChartService;
import com.ecommerce.app.utils.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.CHART_PATH)
@Slf4j
public class ChartController {
    ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService) {

        this.chartService = chartService;
    }

    @PostMapping
    public Chart save(@RequestBody Chart chart){
        log.info("Received request to save Chart: {}", chart);
        return chartService.save(chart);
    }

    @GetMapping
    public List<Chart> getAll(){
        return chartService.getAll();
    }

    @GetMapping("/{chartId}")
    public Optional<Chart> getById(@PathVariable String chartId){
        return chartService.getById(chartId);
    }

    @PutMapping
    public Chart update(@RequestBody Chart chart){
        return chartService.save(chart);
    }

    @DeleteMapping("/{chartId}")
    public void deleted(@PathVariable String chartId){
        chartService.delete(chartId);
    }
}