package com.example.demo.memory;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Params;
import com.example.demo.entity.Result;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    private Storage storage = new Storage();

    @Test
    public void testClear(){
        Result result1 = new Result(3.0,4.0,5.0,6.0,new Params(1.0,2.0));
        Result result2 = new Result(3.5,4.5,5.5,6.5,new Params(1.5,2.5));
        Result result3 = new Result(3.3,4.3,5.3,6.3,new Params(1.3,2.3));
        List<Result> list= new ArrayList<Result>();
        list.add(result1);
        list.add(result2);
        list.add(result3);
        storage.saveResults(list);
        assertEquals(storage.getStorageCount(),Integer.valueOf(3));
        storage.clearMemory();
        assertEquals(storage.getStorageCount(),Integer.valueOf(0));
    }

    @Test
    public void testSave(){
        Result result1 = new Result(3.0,4.0,5.0,6.0,new Params(1.0,2.0));
        Result result2 = new Result(3.5,4.5,5.5,6.5,new Params(1.5,2.5));
        Result result3 = new Result(3.3,4.3,5.3,6.3,new Params(1.3,2.3));
        storage.saveResult(result1);
        storage.saveResult(result2);
        storage.saveResult(result3);
        List<Result> list = storage.getAllResults();
        assertEquals(list.size(),3);

    }

    @Test
    public void testGet(){
        Result result1 = new Result(3.0,4.0,5.0,6.0,new Params(1.0,2.0));
        Result result2 = new Result(3.5,4.5,5.5,6.5,new Params(1.5,2.5));
        Result result3 = new Result(3.3,4.3,5.3,6.3,new Params(1.3,2.3));
        storage.saveResult(result1);
        storage.saveResult(result2);
        storage.saveResult(result3);
        Result result = storage.getResult(new Params(1.0,2.0));
        assertNotNull(result);
        assertEquals(result,result1);
    }
}
