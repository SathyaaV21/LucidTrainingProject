package com.example.demo.exception;

public class TestcaseNotFoundException extends Exception {
	public TestcaseNotFoundException(String errorResponse) {
		super(errorResponse);
	}
}
