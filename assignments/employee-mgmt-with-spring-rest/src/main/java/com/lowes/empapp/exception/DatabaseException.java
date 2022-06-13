package com.lowes.empapp.exception;

import com.lowes.empapp.controller.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message, Throwable cause) {
        super(message,cause);
    }
}


