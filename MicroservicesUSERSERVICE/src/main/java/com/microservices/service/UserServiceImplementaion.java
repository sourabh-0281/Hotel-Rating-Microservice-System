package com.microservices.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.dao.UserRepo;
import com.microservices.entities.Hotel;
import com.microservices.entities.Rating;
import com.microservices.entities.User;
import com.microservices.exception.ResourceNotFoundException;
import com.microservices.feign.HotelserviceFeign;
import com.microservices.feign.RatingServiceFeign;
import com.netflix.discovery.converters.Auto;

@Service
public class UserServiceImplementaion implements UserService {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private RestTemplate resttemplate;
	
	@Autowired
	private HotelserviceFeign hotelservicefeign;

	@Autowired
	private RatingServiceFeign ratingservicefeign;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImplementaion.class);

	// save user
	@Override
	public User saveuser(User u) {
		return userrepo.save(u);
	}

	//	HERE WE ARE USING FEIGN CLIENT IN THIS METHOD
	// get all user
	@Override
	public List<User> getalluser() {
		List<User> all = userrepo.findAll();		
		
		List<User> updatedusers = all.stream().map(user->{
			//API call to ratings
			//Rating[] ratingsofuser = resttemplate.getForObject("http://RATING-MICROSERVICE/rating/user/" + user.getId(),Rating[].class);
			
			List<Rating> ratings = ratingservicefeign.getratings(user.getId());
			
			ratings=ratings.stream().map(r->{
				
				//API to call hotel
			    /* ResponseEntity<Hotel> forEntity = resttemplate.getForEntity("http://HOTEL-MICROSERVICE/hotel/"+r.getHotelid(), Hotel.class);
			         above is eg of resttemplate to get hotel 
			     */
				
				//This is equivalent to making an HTTP GET request to the HOTEL-MICROSERVICE.
		         Hotel hotel=hotelservicefeign.gethotel(r.getHotelid());//THIS IS USE OF FEIGN CLIENT
		         
		         //set hotel to rating
		         r.setHotel(hotel);
		         
		         return r;
			}).collect(Collectors.toList());
			
			user.setRatings(ratings);
	       return user;
		}).collect(Collectors.toList());
return updatedusers;
	}

	// get user by id
	@Override
	public User getuserbyid(int id) {

		User user = userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id = " + id));

		// FROM HERE WE CALL THE RATING SERVICE AND GET RATING
		//api call to rating
	    //WE CAN USE THIS TO CALL API
		Rating[] ratingsofuser = resttemplate.getForObject("http://RATING-MICROSERVICE/rating/user/" + user.getId(),Rating[].class);	
		List<Rating> ratings = Arrays.stream(ratingsofuser).toList();
		logger.info("{}", ratings);
       
		//FROM HERE WE CALL HOTEL SERVICE AND STORE IN RATING
		ratings.stream().map(rating->{
			     //api call to hotel
	        	 //OR  WE CAN USE THIS TO CALL API
		         ResponseEntity<Hotel> forEntity = resttemplate.getForEntity("http://HOTEL-MICROSERVICE/hotel/"+rating.getHotelid(), Hotel.class);
		         Hotel hotel=forEntity.getBody();
		         //set hotel to rating
		         rating.setHotel(hotel);
		         //return rating
		         return rating;  	
		    }).collect(Collectors.toList());
		
		user.setRatings(ratings);

		return user;
	}

	// delete all user
	@Override
	public void deletealluser() {
	}

	// delete user by userid
	@Override
	public void deleteuserbyid(int id) {
	}

	// update user
	@Override
	public User updateuser(int id) {
		return null;
	}
}