package com.example.springhomework.exception;


public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}