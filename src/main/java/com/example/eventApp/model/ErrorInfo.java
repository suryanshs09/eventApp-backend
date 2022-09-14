package com.example.eventApp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@RequiredArgsConstructor
public class ErrorInfo {
    private final String url;
    private final String message;

    public ErrorInfo(HttpServletRequest request, String message) {
        this.url = request.getRequestURL().toString();
        this.message = message;
    }

    public ErrorInfo(HttpServletRequest request, String message, Throwable throwable) {
        this.url = request.getRequestURL().toString();
        this.message = message + ": " + throwable.getMessage();
    }

}
