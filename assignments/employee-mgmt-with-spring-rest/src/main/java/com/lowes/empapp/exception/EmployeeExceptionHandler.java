package com.lowes.empapp.exception;

import com.lowes.empapp.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
Global Exceptional Handler to handle exceptions across the Controllers
 */

@ControllerAdvice
public class EmployeeExceptionHandler {

    Logger logger = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ResponseMessage> handleErrors(EmployeeException ex) {
        ResponseMessage response = new ResponseMessage("Failure", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<ResponseMessage> handleInputErrors(InputValidationException ex) {
        ResponseMessage response = new ResponseMessage("Failure Input", ex.getErrors());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ResponseMessage> handleDatabaseErrors(DatabaseException ex) {
        ResponseMessage response = new ResponseMessage("Failure db", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGenericErrors(Exception ex) {
        ResponseMessage response = new ResponseMessage("Failure exception", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
