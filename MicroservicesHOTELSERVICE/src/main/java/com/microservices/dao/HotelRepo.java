package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Integer>{

}
