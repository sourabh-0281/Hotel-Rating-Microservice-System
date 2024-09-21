package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
