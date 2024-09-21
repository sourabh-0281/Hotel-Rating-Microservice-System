package com.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.dao.RatingRepo;
import com.microservices.entity.Rating;

@Service
public class RatingServiceImplementation implements RatingService{

	@Autowired
	private RatingRepo rr;
	
	@Override
	public Rating create(Rating r) {
		// TODO Auto-generated method stub
		return rr.save(r);
	}

	@Override
	public List<Rating> getratings() {
		// TODO Auto-generated method stub
		return rr.findAll();
	}

	@Override
	public List<Rating> getratingbyuserid(int id) {
		// TODO Auto-generated method stub
		return rr.findByUserid(id);
	}

	@Override
	public List<Rating> getratingbyhotelid(int id) {
		// TODO Auto-generated method stub
		return rr.findByHotelid(id);
	}

	
}
