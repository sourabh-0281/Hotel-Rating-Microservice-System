package com.microservices.services;

import java.util.List;

import com.microservices.entity.Hotel;

public interface HotelService {

	//create
	Hotel create(Hotel h);
	
	//get single
	Hotel getbyid(int id);
	
	//get all
	List<Hotel> getall();
}
