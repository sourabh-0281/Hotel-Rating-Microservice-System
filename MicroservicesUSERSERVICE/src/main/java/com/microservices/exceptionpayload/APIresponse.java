package com.microservices.exceptionpayload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder      //this annotation will help to assign values in single line and build it For eg : see in Globalexception class 
public class APIresponse {

	private String message;
	private boolean success;
	private HttpStatus status;
}
