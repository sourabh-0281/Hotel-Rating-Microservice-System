package com.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservices.exceptionpayload.APIresponse;

@RestControllerAdvice
/* It is a specialized version of @ControllerAdvice in Spring, specifically for handling exceptions in RESTful web services.
 *  It allows you to handle exceptions across all REST controllers in a single place.
     It provides a global exception handling mechanism, 
 * */
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)//in whole project if this type of exception occurs ,this methods will get called
	public ResponseEntity<APIresponse> handlerResourceNotFoundException(ResourceNotFoundException re)	{
		
		String message = re.getMessage();
		APIresponse apIresponse = APIresponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<APIresponse>(apIresponse, HttpStatus.NOT_FOUND);
	}
}
