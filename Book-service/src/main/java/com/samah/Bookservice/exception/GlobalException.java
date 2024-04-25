package com.samah.Bookservice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
        @ExceptionHandler(BookNotFoundException.class)
        public ProblemDetail handleBookNotFoundException(BookNotFoundException ex) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
            return problemDetail;
        }

        @ExceptionHandler(NullPointerException.class)
        public ProblemDetail handleNullPointerException(NullPointerException ex) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
            return problemDetail;
        }
        @ExceptionHandler(RuntimeException.class)
        public ProblemDetail handleRuntimeException(RuntimeException ex) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
            return problemDetail;
    }
        @ExceptionHandler(Exception.class)
        public ProblemDetail handleException(Exception ex) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            return problemDetail;
        }
//        public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
}
