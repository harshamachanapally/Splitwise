package com.splitwise.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.splitwise.exception.DataNotFoundException;
import com.splitwise.exception.ErrorMessage;

@ControllerAdvice
public class ExceptionController implements HandlerExceptionResolver {
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorMessage> DataException(DataNotFoundException D) throws Exception{
		ErrorMessage em = new ErrorMessage();
		em.setErrorMessage(D.getMessage());
		em.setErrorCode("404");
		return new ResponseEntity<ErrorMessage>(em,HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {

		return null;
	}
	
}
