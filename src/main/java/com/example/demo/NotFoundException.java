package com.example.demo;

public class NotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        // TODO Auto-generated constructor stub
    }

    public NotFoundException(String message) {
        // TODO Auto-generated constructor stub
        super(message);
    }

    public NotFoundException(String message, Throwable e) {
        // TODO Auto-generated constructor stub
        super(message, e);
    }
}
