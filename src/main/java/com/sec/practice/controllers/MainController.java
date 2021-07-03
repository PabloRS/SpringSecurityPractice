package com.sec.practice.controllers;

import com.sec.practice.securingweb.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private MetricService metricService;

    @GetMapping("/")
    public String getMainApp() {
        metricService.increaseCount("/", 200);
        return "Main controller";
    }

    @GetMapping("/status-metric")
    @ResponseBody
    public Map getStatusMetric() {
        Map<Integer, Integer> response = metricService.getStatusMetric();
        return response;
    }
}
