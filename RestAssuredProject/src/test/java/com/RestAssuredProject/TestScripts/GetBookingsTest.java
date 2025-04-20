package com.RestAssuredProject.TestScripts;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestAssuredProject.GenericLib.BaseTest;
import com.RestAssuredProject.GenericLib.ResponseLogger;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Ankita
 *
 */

public class GetBookingsTest extends BaseTest {
	
	/*
	 * Returns the ids of all the bookings that exist within the API. 
	 * Can take optional query strings to search and return a subset of booking ids.
	 * Response: 200 OK[
  {
    "bookingid": 1
  },
  {
    "bookingid": 2
  },
  {
    "bookingid": 3
  },
  {
    "bookingid": 4
  }
]
	 */
	
	@Test
	public void getAllBookingIds()
	{
		
		
		Response response  = RestAssured.given(specs).get("/booking");
		
		response.print();
		
		int statusCode = response.statusCode();
		
		Assert.assertEquals(statusCode,  200, "Status code is not 200");
		
		ResponseLogger.logResponseToSharedFile(response, "getAllBookingIds");
		
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		
		Assert.assertFalse(bookingIds.isEmpty() , "List is empty but it should not be");
	}
	
	
	@Test
	public void getBookingIdsWithFilter()
	{
		//specs.queryParam("firstname", "Ankita");
		//specs.queryParam("lastname", "Bhogal");
		
		specs.queryParam("search", "firstname:Ankita|lastname:Bhogal");
		Response response  = RestAssured.given(specs).get("/booking");
		
		response.print();
		
		int statusCode = response.statusCode();
		
		Assert.assertEquals(statusCode,  200, "Status code is not 200");
		
		ResponseLogger.logResponseToSharedFile(response, "getBookingIdsWithFilter");
		
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		
		Assert.assertFalse(bookingIds.isEmpty() , "List is empty but it should not be");
	}

}
