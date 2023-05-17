package com.example.demo.memory;

import com.example.demo.entity.Params;
import com.example.demo.entity.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component
public class Storage {

    private Map<Params, Result> dStorage = new HashMap<Params,Result>();

    public void saveResult(Result result) {
        dStorage.put(result.getParam(),result);
    }

    public void saveResults(List<Result> results) {
        for (int i = 0; i < results.size(); i++) {
            dStorage.put(results.get(i).getParam(),results.get(i));
        }
    }
    public Result getResult(Params params) {
        return dStorage.get(params);
    }

    public Integer getStorageCount() {
        return dStorage.size();
    }

    public void clearMemory(){
        dStorage.clear();
    }

    public List<Result>  getAllResults() {
        List<Result> resultList = new ArrayList<Result>();
        dStorage.forEach((key,numb) -> resultList.add(numb));
        return resultList;
    }
}
