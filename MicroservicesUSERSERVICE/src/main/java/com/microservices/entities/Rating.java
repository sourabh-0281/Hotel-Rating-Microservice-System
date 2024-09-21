package com.microservices.entities;

import java.util.List;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//WE ARE NOT USING ENTITY HERE BECAUSE WE ARE NOT STORING DATA IN DB,WE WILL GET THIS FROM  ANOTHER MICROSERVICE 
public class Rating {

	private int ratingid;
	private int userid;
	private int hotelid;
	private int rating;
	private String feedback;
	private Hotel hotel;
}
