package com.example.demo.database;

import com.example.demo.entity.Result;
import jakarta.persistence.*;

@jakarta.persistence.Entity
@Table(name = "results")
public class Entity {
    @Id
    @Column(name = "id")            // имя столбца талицы для поля
    // стратегия генерации ключа
    @SequenceGenerator(name = "resultsIdSeq", sequenceName = "results_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultsIdSeq")
    private Integer id;

    @Column(name = "param1")
    private Double param1;

    @Column(name = "param2")
    private Double param2;

    @Column(name = "summ")
    private Double summ;

    @Column(name = "sub")
    private Double sub;

    @Column(name = "mul")
    private Double mul;

    @Column(name = "div")
    private Double div;

    public Entity(){

    }

    public Entity(Result result){
        param1 = result.getParam().getParam1();
        param2 = result.getParam().getParam2();
        summ = result.getSum();
        sub = result.getSub();
        mul = result.getMul();
        div = result.getDiv();
    }
}
