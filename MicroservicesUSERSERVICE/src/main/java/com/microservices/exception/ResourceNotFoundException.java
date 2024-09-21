package com.microservices.exception;

//HERE WE ARE NOT DEFINING GLOBAL EXCEPTION HANDLER WE CAN ALSO DO IT IN CONTROLLER (controller based handler) 
public class ResourceNotFoundException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException() {
	   super("User not found");
	}
  public ResourceNotFoundException(String message) {
	   super(message);
}
}
