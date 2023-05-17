package com.example.demo.service;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Params;
import com.example.demo.entity.Result;
import org.junit.jupiter.api.Test;
public class ActionsServiceTest {

  //  private Actions actions = mock(Actions.class);

    private ActionsService actionsService = new ActionsService();

    @Test
    public void testAct1() {
        Params params = new Params(3.0,4.0);
        Result result;
        result = actionsService.act(params);
        Result expResult = new Result(7.0,-1.0,12.0,0.75,params);
        assertEquals(result,expResult);
    }



}