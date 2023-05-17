package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CounterService {

    private Integer count = 0;
    private Integer sinCount = 0;

    public void increment(){
        count++;
    }
    public synchronized void sinIncrement() {
        sinCount++;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSinCount() {
        return sinCount;
    }

    public void setSinCount(Integer sinCount) {
        this.sinCount = sinCount;
    }

    public Map<String,Integer> getAsMap() {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("Synchronized calls", sinCount);
        map.put("Not synchronized calls",count);
        return map;
    }

    public void reset(){
        sinCount = 0;
        count = 0;
    }
}
