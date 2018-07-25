package com.aspectworks.active24.api.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RequestCounter implements Serializable {
    int currentRequestCount;
    Map<String, Integer> currentMethodCountMap=new HashMap<>();

    public int getCurrentRequestCount() {
        return currentRequestCount;
    }

    public void setCurrentRequestCount(int currentRequestCount) {
        this.currentRequestCount = currentRequestCount;
    }

    public Map<String, Integer> getCurrentMethodCountMap() {
        return currentMethodCountMap;
    }

    public void setCurrentMethodCountMap(Map<String, Integer> currentMethodCountMap) {
        this.currentMethodCountMap = currentMethodCountMap;
    }

    public void addToCurrentMethodCountMap(String key,Integer value){
        currentMethodCountMap.put(key,value);
    }

}
