package com.microservices.service;

import java.util.List;

import com.microservices.entity.Rating;

public interface RatingService {

	//create
	Rating create(Rating r);
	
	//get all ratings
	List<Rating>getratings();
	
	//get ratings by userid
	List<Rating>getratingbyuserid(int id);
	
	//get ratings by hotelid
	List<Rating>getratingbyhotelid(int id);
	
	//
}
