package com.branch.branchcodingexercise.aop;

import com.branch.branchcodingexercise.util.BranchGenericException;
import com.branch.branchcodingexercise.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String handleNotFound(UserNotFoundException ex) {
        return "Sorry, User Not Found";
    }

    @ResponseBody
    @ExceptionHandler(BranchGenericException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleNotFound(BranchGenericException ex) {
        return "An internal error occurred";
    }
}