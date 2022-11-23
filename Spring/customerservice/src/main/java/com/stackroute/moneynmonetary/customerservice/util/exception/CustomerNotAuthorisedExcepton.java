package com.stackroute.moneynmonetary.customerservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.UNAUTHORIZED,reason = "Customer not authorised")
public class CustomerNotAuthorisedExcepton extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
