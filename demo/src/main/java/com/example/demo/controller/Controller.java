package com.example.demo.controller;

import com.example.demo.entity.BulkResult;
import com.example.demo.entity.Params;
import com.example.demo.entity.Result;
import com.example.demo.entity.ValidatorError;
import com.example.demo.memory.Storage;
import com.example.demo.service.ActionsService;
import com.example.demo.service.CounterService;
import com.example.demo.validators.NumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/lab")
public class Controller {

    private ActionsService service;

    private NumberValidator val;

    private Storage storage;

    private CounterService counterService;


    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(ActionsService service, NumberValidator val, Storage storage, CounterService counterService) {
        this.service = service;
        this.val = val;
        this.storage = storage;
        this.counterService = counterService;
    }


    @GetMapping("/actions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <Object> actions(@RequestParam(name = "param1", required = true) Double param1,
                                           @RequestParam(name = "param2", required = true) Double param2) {


        logger.info("1. Validation starts");

        ValidatorError resp = val.validateParameters(param1, param2);
        if(resp.getErrorMess().size()!=0){
            resp.addErrorMess("Input number is not valid");
            resp.setStatus(HttpStatus.BAD_REQUEST.name());

            return new ResponseEntity(resp,HttpStatus.BAD_REQUEST);
        }
        Params inp = new Params(param1,param2);
        //Result result = storage.getResult(inp);
        Result result;
        try {
            logger.info("2. Actions start");
            counterService.increment();
            counterService.sinIncrement();
            result = service.act(inp);
        }catch(Exception exp) {
            resp.addErrorMess("Internal Server Error");
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("Internal Server Error");
            return new ResponseEntity(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        storage.saveResult(result);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @PostMapping("/act")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BulkResult> postResults(@RequestBody List<Params> paramList){

        List<Result> results = new ArrayList<>();
        //service.bulkResult(paramList);
        //storage.saveResults(results);
        //for(int i = 0; i < paramList.size(); i++)
        for(Params bulkParams: paramList)
        {
            if((val.validateParameters(bulkParams.getParam1(), bulkParams.getParam2())).getStatus().equals("OK")){
                results.add(service.act(bulkParams));
            }
            else {
                Result errRes = new Result();
                errRes.setParam(bulkParams);
                errRes.setMessage("Invalid Parameters!");
                results.add(errRes);
            }
        }

        storage.saveResults(results);

        BulkResult bulkResult = new BulkResult(maxSum(results),minSum(results),results);


        return new ResponseEntity<BulkResult>(bulkResult,HttpStatus.OK);
    }




    @GetMapping("/getResults")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Result>> getAllResults() {
        return ResponseEntity.ok(storage.getAllResults());
    }
    @GetMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Storage> clearInMemory() {
        storage.clearMemory();
        return ResponseEntity.ok(storage);
    }

    @GetMapping("/calls")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map> countCalls() {return ResponseEntity.ok(counterService.getAsMap());}

    @GetMapping("/calls/clear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> resetCalls(){
        counterService.reset();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public Double maxSum(List<Result> results){
        return results.stream().mapToDouble(Result::get).max().getAsDouble();
    }

    public Double minSum(List<Result> results){
        return results.stream().mapToDouble(Result::get).min().getAsDouble();
    }
}
