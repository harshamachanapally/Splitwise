package com.splitwise.pojo;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerClass {
	@ExceptionHandler(value = NullPointerException.class)
	public String handleNullPointerException(Exception E){
		System.out.println(E.getMessage());
		return "NullPointerException";
	}
	
	@ExceptionHandler(value = IndexOutOfBoundsException.class)
	public String handleIndexOutOfBoundException(Exception E){
		System.out.println(E.getMessage());
		return "IndexException";
	}
	@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
	@ExceptionHandler(value = HttpSessionRequiredException.class)
	public String handleHttpSessionRequiredException(Exception E){
		System.out.println(E.getMessage());
		return "HttpSessionRequiredException";
	}
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String handleotherException(Exception E){
		System.out.println(E.getMessage());
		return "Exception";
	}

}
