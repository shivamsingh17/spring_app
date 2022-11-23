package com.stackroute.moneynmonetary.customerservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Customer already exists")
public class CustomerAlreadyExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
