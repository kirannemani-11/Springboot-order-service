package com.microservices.order_service.globalexception;

import com.microservices.order_service.exception.ErrorDetails;
import com.microservices.order_service.exception.OrderNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<Map<String, String>> orderNotFound(OrderNotFound ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(errorDetails);
    }
}
