package com.wanted.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException ile) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ile.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runTimeExceptionHandler(RuntimeException re) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(re.getMessage());
    }
}
