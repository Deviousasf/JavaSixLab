package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

public class Result {

    private Params param;

    public Params getParam() {
        return param;
    }

    public void setParam(Params param) {
        this.param = param;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double sum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double sub;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double mul;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double div;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = new  String(message);
    }

    public Result(Double sum, Double sub, Double mul, Double div, Params param){
        this.sum = sum;
        this.sub = sub;
        this.div = div;
        this.mul = mul;
        this.param = new Params(param.getParam1(),param.getParam2());
        this.message = null;
    }
    public Result(){
        this.sum = null;
        this.sub = null;
        this.div = null;
        this.mul = null;
        this.param = new Params(0.0,0.0);
        this.message = null;
    }


    public Double getSum() {return sum;}
    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setSub(Double sub) {
        this.sub = sub;
    }

    public void setMul(Double mul) {
        this.mul = mul;
    }

    public void setDiv(Double div) {
        this.div = div;
    }
    public Double getSub() {
        return sub;
    }

    public Double getMul() {
        return mul;
    }

    public Double getDiv() {
        return div;
    }

    public Double get(){if(sum ==null){return Double.valueOf(0.0);}else return sum;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(param, result.param) && Objects.equals(sum, result.sum) && Objects.equals(sub, result.sub) && Objects.equals(mul, result.mul) && Objects.equals(div, result.div) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(param, sum, sub, mul, div, message);
    }
}
