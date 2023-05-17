package com.example.demo.validators;


import com.example.demo.entity.ValidatorError;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;


@Component
public class NumberValidator {
    private static final Logger logger = LoggerFactory.getLogger(NumberValidator.class);

    public ValidatorError validateParameters(Double inp1, Double inp2) {
        ValidatorError resp = new ValidatorError();
        resp.setStatus("OK");
        if(inp1 < 0.0) {
            logger.error("Parameter cannot be negative");
            resp.addErrorMess("Parameter 1 cannot be negative");
            resp.setStatus("Error");
        }
        if(inp1 == 0.0) {
            logger.error("Parameter 1 cannot be 0");
            resp.addErrorMess("Parameter cannot be 0");
            resp.setStatus("Error");

        }
        if(inp2 < 0.0) {
            logger.error("Parameter 2 cannot be negative");
            resp.addErrorMess("Parameter cannot be negative");
            resp.setStatus("Error");

        }
        if(inp2 == 0.0) {
            logger.error("Parameter 2 cannot be 0");
            resp.addErrorMess("Parameter cannot be 0");
            resp.setStatus("Error");

        }
        return resp;
    }

    public ValidatorError validateParameter(Double inp) {
        ValidatorError resp = new ValidatorError();
        resp.setStatus("OK");
        if(inp < 0.0) {
            logger.error("Parameter cannot be negative");
            resp.addErrorMess("Parameter 1 cannot be negative");
            resp.setStatus("Error");
        }
        if(inp == 0.0) {
            logger.error("Parameter 1 cannot be 0");
            resp.addErrorMess("Parameter cannot be 0");
            resp.setStatus("Error");
        }
        return resp;
    }

}
