package com.example.demo.service;

import com.example.demo.entity.Params;
import com.example.demo.entity.Result;
import org.springframework.stereotype.Service;

@Service
public class ActionsService {

    public Result act(Params params){
        return new Result(params.getParam1() + params.getParam2(), params.getParam1() -params.getParam2(), params.getParam1() * params.getParam2(), params.getParam1() / params.getParam2(), params);
    }

//    public List<Result> bulkResult(List<Params> params){
//        Map<Params,Result> mapResults = new HashMap<>();
//        params.forEach(bulkParams -> {
//            mapResults.put(bulkParams, act(bulkParams));
//        });
//        List<Result> results = new ArrayList<>();
//        mapResults.entrySet().stream().
//                forEach(entry -> results.
//                        add(new Result(entry.getValue().getSum(), entry.getValue().getSub(),entry.getValue().getMul(),entry.getValue().getDiv(),entry.getValue().getParam())));
//        return results;
//    }

   /* public Double maxSum(List<Result> results){
        return results.stream().mapToDouble(Result::getSum).max().getAsDouble();
    }

    public Double minSum(List<Result> results){
        return results.stream().mapToDouble(Result::getSum).min().getAsDouble();
    }*/
}
