package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class LocationRestController 
{
	@ExceptionHandler(DeleteLocationException.class)
    public ResponseEntity<?> handleDeleteException(DeleteLocationException e,WebRequest req)
    {
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_FOUND);		
    }
	
	@ExceptionHandler(UpdateLocationException.class)
    public ResponseEntity<?> handleUpdateException(UpdateLocationException e1,WebRequest req1)
    {
		return new ResponseEntity<>(e1.toString(),HttpStatus.NOT_FOUND);		
    }
	
	@ExceptionHandler(AddLocationException.class)
    public ResponseEntity<?> handleAddException(AddLocationException e3,WebRequest req3)
    {
		return new ResponseEntity<>(e3.toString(),HttpStatus.NOT_FOUND);		
    }
}
