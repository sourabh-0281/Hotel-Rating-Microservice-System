package com.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.dao.HotelRepo;
import com.microservices.entity.Hotel;
import com.microservices.exception.ResourceNotFoundException;

@Service
public class HotelServiceImplementation implements HotelService {

	@Autowired
	private HotelRepo hotelrepo;
	
	@Override
	public Hotel create(Hotel h) {
		
		return hotelrepo.save(h);
	}

	@Override
	public Hotel getbyid(int id) {
		// TODO Auto-generated method stub
		return hotelrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found"));
	}

	@Override
	public List<Hotel> getall() {
		// TODO Auto-generated method stub
		return hotelrepo.findAll();
	}

}
