package com.microservices.exception;

public class ResourceNotFoundException extends RuntimeException {
	 
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ResourceNotFoundException() {
	  super("Hotel does not exist");				
    }
   
   public ResourceNotFoundException(String msg) {
		  super(msg);
	    }
}
