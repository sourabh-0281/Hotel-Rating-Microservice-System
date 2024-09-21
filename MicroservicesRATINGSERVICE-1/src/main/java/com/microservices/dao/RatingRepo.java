package com.microservices.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer>{

	List<Rating> findByUserid(int id);
	List<Rating> findByHotelid(int id);
}
