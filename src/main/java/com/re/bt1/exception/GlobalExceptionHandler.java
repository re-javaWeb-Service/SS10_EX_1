package com.re.bt1.exception;

import com.re.bt1.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.monitor.StringMonitor;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(
                HttpStatus.NOT_FOUND
        )
                .body(ApiResponse.<Void>builder()
                        .code(404)
                        .success(false)
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex){
        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        )
                .body(ApiResponse.<Void>builder()
                        .success(false)
                        .message(ex.getMessage())
                        .code(400)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error-> errors.put(error.getField(),
                                                        error.getDefaultMessage())
                );
        return ResponseEntity.badRequest()
                .body(ApiResponse.<Map<String,String>>builder()
                        .success(false)
                        .code(400)
                        .data(errors)
                        .message("Validate Invalid")
                        .build()
                );

    }

}


