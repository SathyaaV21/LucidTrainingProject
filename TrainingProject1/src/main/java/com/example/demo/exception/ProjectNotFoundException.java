package com.example.demo.exception;

public class ProjectNotFoundException extends Exception{
	public ProjectNotFoundException(String errorResponse) {
		super(errorResponse);
	}
}
