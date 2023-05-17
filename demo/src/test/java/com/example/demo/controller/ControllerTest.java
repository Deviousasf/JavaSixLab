package com.example.demo.controller;

import com.example.demo.entity.BulkResult;
import com.example.demo.entity.Params;
import com.example.demo.entity.Result;
import com.example.demo.entity.ValidatorError;
import com.example.demo.memory.Storage;
import com.example.demo.service.ActionsService;
import com.example.demo.service.CounterService;
import com.example.demo.validators.NumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @Mock
    private ActionsService service;
    @Mock
    private NumberValidator val;
    @Mock
    private Storage storage;
    @Mock
    private CounterService counterService;

    @InjectMocks
    Controller controller = new Controller(service,val,storage, counterService);

    @Test
    public void testWithValidationErrors(){
        Double inp1 = Double.valueOf(4.0);
        Double inp2 = Double.valueOf(-1.0);
        ValidatorError errors = new ValidatorError();
        errors.addErrorMess("Default error");

        when(val.validateParameters(any(Double.class), any(Double.class))).thenReturn(errors);
        ResponseEntity<Object> responseEntity = controller.actions(inp1,inp2);
        ValidatorError errors2 =  (ValidatorError) responseEntity.getBody();
        assertNotNull(errors2);
        assertEquals(errors, errors2);
    }

    @Test
    public void testGood(){
        Double inp1 = Double.valueOf(4.0);
        Double inp2 = Double.valueOf(1.0);
        Result result = new Result(5.0,3.0,4.0,4.0,new Params(4.0,1.0));

        when(val.validateParameters(any(Double.class), any(Double.class))).thenReturn(new ValidatorError());
        doNothing().when(counterService).increment();
        doNothing().when(counterService).sinIncrement();
        when(service.act(any(Params.class))).thenReturn(result);
        ResponseEntity<Object> responseEntity = controller.actions(inp1,inp2);
        Result result1 = (Result) responseEntity.getBody();
        assertNotNull(result1);
        assertEquals(result,result1);
    }

    @Test
    public void testPost(){
        List<Params> list = new ArrayList<>();
        Params params1 = new Params(1.0,2.0);
        Params params2 = new Params(1.1,2.1);
        Params params3 = new Params(1.2,2.2);
        Params params4 = new Params(1.3,2.3);
        Params params5 = new Params(1.4,2.4);
        list.add(params1);
        list.add(params2);
        list.add(params3);
        list.add(params4);
        list.add(params5);

        when(val.validateParameters(any(Double.class), any(Double.class))).thenReturn(new ValidatorError("OK"));
        when(service.act(any(Params.class))).thenReturn(new Result(3.0, 1.0,2.0,2.0, new Params(2.0, 1.0)));
        ResponseEntity<BulkResult> responseEntity = controller.postResults(list);
        List<Result> results = responseEntity.getBody().getResults();
        assertNotNull(results);
        assertTrue(results.size() == 5);
    }

    @Test
    public void testPostErr(){
        List<Params> list = new ArrayList<>();
        Params params1 = new Params(0.0,-2.0);
        list.add(params1);

        when(val.validateParameters(any(Double.class), any(Double.class))).thenReturn(new ValidatorError("Error"));
        Result ErrResult = new Result();
        ErrResult.setParam(params1);
        ErrResult.setMessage("Invalid Parameters!");

        ResponseEntity<BulkResult> responseEntity = controller.postResults(list);
        List<Result> results = responseEntity.getBody().getResults();
        Result result = results.get(0);
        assertEquals(result.getMessage(), "Invalid Parameters!");
        assertNotNull(results);
    }
    
    @Test
    public void testGetAllResults(){
        when(storage.getAllResults()).thenReturn(new ArrayList<Result>());
        ResponseEntity<List<Result>> responseEntity = controller.getAllResults();
        List<Result> results = responseEntity.getBody();
        assertNotNull(results);
        assertTrue(results.size() == 0);
    }

    @Test
    public void testClear(){
        doNothing().when(storage).clearMemory();
        ResponseEntity<Storage> responseEntity = controller.clearInMemory();
        verify(storage,times(1)).clearMemory();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetCalls(){
        Map<String,Integer> map = new HashMap<>();
        when(counterService.getAsMap()).thenReturn(map);
        ResponseEntity<Map> responseEntity = controller.countCalls();
        verify(counterService,times(1)).getAsMap();
        assertEquals(map,responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void testResetCalls(){
        doNothing().when(counterService).reset();
        ResponseEntity<Void> responseEntity = controller.resetCalls();
        verify(counterService,times(1)).reset();
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }
}
