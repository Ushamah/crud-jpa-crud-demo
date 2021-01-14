package com.ushwamala.springboot.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    //An exception handler for a employeeNotFoundException
    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> employee_NF_ExceptionHandler
    (EmployeeNotFoundException employee_NF_Exception){

        //create a student error response
        EmployeeExceptionResponse error = createResponseError(employee_NF_Exception);

        //Create a ResponseEntity
        ResponseEntity<EmployeeExceptionResponse> response =
                new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        //return the response
        return response;
    }


    //Add an exception handler for any othe thrown exception
    @ExceptionHandler
    public ResponseEntity<EmployeeExceptionResponse> badRequestException
    (Exception exception){

        //create a student error response
        EmployeeExceptionResponse error = createResponseError(exception);

        //Create a ResponseEntity
        ResponseEntity<EmployeeExceptionResponse> response =
                new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        //return the response
        return response;
    }

    private static EmployeeExceptionResponse createResponseError
            (Exception exception){

        EmployeeExceptionResponse error = new EmployeeExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

       return error;
    }


}
