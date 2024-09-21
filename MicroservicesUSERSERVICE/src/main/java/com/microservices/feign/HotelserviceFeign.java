package com.microservices.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.entities.Hotel;

@FeignClient(name = "HOTEL-MICROSERVICE")
public interface HotelserviceFeign {

	/* Feign uses the @GetMapping("/hotel/{hid}") annotation to know which endpoint to call on the HOTEL-MICROSERVICE.
       It replaces {hid} in the URL with the actual value of id in UserServiceImplementaion and makes the HTTP GET request.
	 * */
	
	@GetMapping("/hotel/{hid}")
	 Hotel gethotel(@PathVariable("hid") int id);
}
