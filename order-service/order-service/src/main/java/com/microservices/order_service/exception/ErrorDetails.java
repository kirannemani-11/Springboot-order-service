package com.microservices.order_service.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class ErrorDetails {
    private int statuscode;
    private String message;
    private String details;
}
