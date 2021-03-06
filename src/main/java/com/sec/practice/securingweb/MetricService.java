package com.sec.practice.securingweb;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class MetricService {
    private ConcurrentMap<Integer, Integer> statusMetric;

    public MetricService() {
        statusMetric = new ConcurrentHashMap<>();
    }

    public void increaseCount(String request, int status) {
        Integer statusCount = statusMetric.get(status);
        if(statusCount == null) {
            statusMetric.put(status, 1);
        } else {
            statusMetric.put(status, statusCount+1);
        }
    }

    public Map getStatusMetric() {
        return statusMetric;
    }
}
