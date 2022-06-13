package com.lowes.empapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseException extends RuntimeException{
    Logger logger = LoggerFactory.getLogger(DatabaseException.class);
    public DatabaseException(String message) {
        super(message);
        logger.info("inside database exception  after super", getMessage());

    }
}


