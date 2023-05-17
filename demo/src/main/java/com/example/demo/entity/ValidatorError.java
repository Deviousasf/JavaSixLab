package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidatorError {

    private List<String> errorMess = new ArrayList<String>();
    private String status;

    public List<String> getErrorMess() {
        return errorMess;
    }

    public ValidatorError(String status) {
        this.status = status;
    }
    public ValidatorError() {}
    public void addErrorMess(String errorMessage) {
        this.errorMess.add(errorMessage);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidatorError that = (ValidatorError) o;
        return Objects.equals(errorMess, that.errorMess) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMess, status);
    }
}


