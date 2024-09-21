package com.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dao.RatingRepo;
import com.microservices.entity.Rating;
import com.microservices.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	RatingService rs;
	
	//create
	@PostMapping("")
	public ResponseEntity<Rating> create(@RequestBody Rating r){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(rs.create(r));
	}
	
	//get all rating
	@GetMapping("")
	public ResponseEntity<List<Rating>> getallrating(){
		return ResponseEntity.ok(rs.getratings());
	}
	
	//get by userid
	@GetMapping("/user/{uid}")
	public ResponseEntity<List<Rating>> getuserrating(@PathVariable int uid ){
		return ResponseEntity.ok(rs.getratingbyuserid(uid));
	}
	
	//get by hotelid
		@GetMapping("/hotel/{hid}")
		public ResponseEntity<List<Rating>> gethotelrating(@PathVariable int hid ){
			return ResponseEntity.ok(rs.getratingbyhotelid(hid));
		}
		
	
}
