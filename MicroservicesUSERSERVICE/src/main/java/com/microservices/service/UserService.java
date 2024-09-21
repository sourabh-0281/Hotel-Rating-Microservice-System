package com.microservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.entities.User;

public interface UserService {

	//create
	User saveuser(User u);
	
	//get all user
	List<User> getalluser();
	
	//get user by id
	User getuserbyid(int id);
	
	//delete user
	void deletealluser();
	
	//delete user by id
	void deleteuserbyid(int id);
	
	//update user
	User updateuser(int id);
}
