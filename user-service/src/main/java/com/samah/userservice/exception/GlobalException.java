package com.samah.userservice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
        @ExceptionHandler(UserNotFoundException.class)
        public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
            return problemDetail;
        }
        @ExceptionHandler(RuntimeException.class)
        public ProblemDetail handleRuntimeException(UserNotFoundException ex) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
            return problemDetail;
    }
//        public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
}
