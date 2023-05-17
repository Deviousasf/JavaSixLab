package com.example.demo.entity;

import java.util.Objects;

public class Params {

    private Double param1;

    private Double param2;

    public Params(Double param1, Double param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public Params(){
        this.param1 = 0.0;
        this.param2 = 0.0;
    }
    public Double getParam1() {
        return param1;
    }

    public void setParam1(Double param1) {
        this.param1 = param1;
    }

    public Double getParam2() {
        return param2;
    }

    public void setParam2(Double param2) {
        this.param2 = param2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Params params = (Params) o;
        return Objects.equals(param1, params.param1) && Objects.equals(param2, params.param2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param1, param2);
    }
}
