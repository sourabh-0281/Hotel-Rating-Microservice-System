package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.entities.User;
import com.microservices.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
    //create
	@PostMapping("")
	public ResponseEntity<User> createuser(@RequestBody User u) {
	User saveuser = userservice.saveuser(u);
	return ResponseEntity.status(HttpStatus.CREATED).body(saveuser);
	}
	
	
	
	//single user get  
	//CIRCUIT BREAKER
	@GetMapping("/{uid}")
	@CircuitBreaker(name = "userbreaker",fallbackMethod = "fallbackmethod")
	public ResponseEntity<User> getuserbyid(@PathVariable int uid) {
		System.out.println(retrycount);
		User user = userservice.getuserbyid(uid);
		return ResponseEntity.ok(user);
		}
	
	
	//all user get
	//RETRY
	//RATE LIMITER
	int retrycount=0;
	@GetMapping("")
	//@Retry(name = "retrybreaker",fallbackMethod = "fallbackmethodRetry")
	@RateLimiter(name = "retrybreaker",fallbackMethod = "fallbackmethodRetry")
	public ResponseEntity<List<User>> getall(){
		retrycount++;
		System.out.println(retrycount);
		List<User>u = userservice.getalluser();
		return ResponseEntity.ok(u);
	}

	
	//FALLBACK METHODS
	//--FOR CIRCUIT BREAKER
	/*  -here return type should be same as it is in where we have use @CircuitBreaker
	 *  -parameter should also be same ,only add exception
	 *  
	 *  if any service is not working this method will get called
	 */
	public ResponseEntity<User> fallbackmethodCircuitbreaker(int userid,Exception ex){
		 User user = User .builder()
		 		  .id(000)
		 		  .email("dummy@gmail.com")
		 		  .name("dummy")
		 		  .about("this is dummy because some error is service")
		 		  .build();
	     return new ResponseEntity<>(user, HttpStatus.OK);
	} 
	
	
	//--FOR RETRY/RATE LIMITER
	public ResponseEntity<List<User>> fallbackmethodRetry(Exception ex){
		 User user = User .builder()
		 		  .id(000)
		 		  .email("dummy@gmail.com")
		 		  .name("dummy")
		 		  .about("this is dummy because some error is service")
		 		  .build();
		 List<User> of = List.of(user);
	     return new ResponseEntity<List<User>>(of, HttpStatus.OK);
	}
}  
   