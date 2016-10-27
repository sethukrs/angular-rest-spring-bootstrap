package gamesys.controller;

import gamesys.exception.InvalidDOBException;
import gamesys.exception.DuplicateSSNException;
import gamesys.exception.UserBlackListedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(DuplicateSSNException.class)
    public ResponseEntity<String> handleDuplicateSSNException(DuplicateSSNException e) {
        return new ResponseEntity<String>(ErrorCodes.DUPLICATE_SSN_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserBlackListedException.class)
    public ResponseEntity<String> handleUserBlackListedException(UserBlackListedException e) {
        return new ResponseEntity<String>(ErrorCodes.USER_BLACK_LISTED_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDOBException.class)
    public ResponseEntity<String> handleInvalidDOBException(InvalidDOBException e) {
        return new ResponseEntity<String>(ErrorCodes.INVALID_DOB_EXCEPTION, HttpStatus.BAD_REQUEST);
    }
}
