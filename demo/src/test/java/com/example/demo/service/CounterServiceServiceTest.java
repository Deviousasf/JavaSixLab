package com.example.demo.service;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class CounterServiceServiceTest {
    private CounterService counterService = new CounterService();

    @Test
    public void testReset(){
        counterService.setCount(3);
        counterService.setSinCount(3);
        counterService.reset();
        assertEquals(counterService.getCount(),Integer.valueOf(0));
        assertEquals(counterService.getSinCount(),Integer.valueOf(0));
    }

    @Test
    public void testIncrement(){
        counterService.reset();
        for(int i = 0;i<3;i++) {
            counterService.increment();
            counterService.sinIncrement();
        }
        Map<String,Integer> expMap = counterService.getAsMap();
        assertEquals(expMap.size(),2);
        assertEquals(expMap.get("Synchronized calls"),Integer.valueOf(3));
        assertEquals(expMap.get("Not synchronized calls"),Integer.valueOf(3));
    }
}

