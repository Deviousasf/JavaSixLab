package com.example.demo.database;

import com.example.demo.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {
    private Repository repository;

    @Autowired
    public RepositoryService(Repository repository){
        this.repository = repository;
    }

    public void save(Result result){
        repository.save(new Entity(result));
    }

    public List<Entity> getAllEntities(){
        return repository.findAll();
    }
}
