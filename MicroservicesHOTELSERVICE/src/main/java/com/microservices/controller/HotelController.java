package com.microservices.controller;

import java.util.List;

import javax.sound.sampled.Line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.entity.Hotel;
import com.microservices.services.HotelService;

@RestController 
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hs;
	
	   //create
	  @PostMapping("")
	    public ResponseEntity<Hotel> createhotel(@RequestBody Hotel h){
	    	return ResponseEntity.status(HttpStatus.CREATED).body(hs.create(h));
	    }
	    
		//get single
	  @GetMapping("/{hid}")
	    public ResponseEntity<Hotel> getbyid(@PathVariable int hid){
		  return ResponseEntity.ok(hs.getbyid(hid));
	  }
	    
		//getall
	  @GetMapping("")
	  public ResponseEntity<List<Hotel>> getall(){
		  return ResponseEntity.ok(hs.getall()); 
	  }
}
