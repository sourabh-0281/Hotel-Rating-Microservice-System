package com.microservices.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.entities.Rating;

import jakarta.ws.rs.GET;

@FeignClient(name = "RATING-MICROSERVICE")
public interface RatingServiceFeign {

	@GetMapping("rating/user/{uid}")
	List<Rating> getratings(@PathVariable("uid") int uid);
}
