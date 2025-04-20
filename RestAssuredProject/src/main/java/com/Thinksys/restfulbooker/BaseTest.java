package com.Thinksys.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Ankita
 *
 */


public class BaseTest {
	public RequestSpecification specs;
	
	/*
	 * This method is use to setup the base URL before every method execution
	 */
	
	@BeforeMethod
	public void setUp()
	{
		specs = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
	}
	
	/*
	 * This method is use to create booking using JSON Object
	 */
	protected Response createBooking() {
		//Create JSON body for Post request
		JSONObject body = new JSONObject();
		body.put("firstname", "Ankita");
		body.put("lastname", "Bhogal");
		body.put("totalprice", 111);
		body.put("depositpaid", false);
	
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2025-04-04");
		bookingdates.put("checkout", "2025-04-08");
		
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Breakfast");
		
		//Post the request
		Response response = RestAssured.given(specs).
				contentType(ContentType.JSON).
				body(body.toString()).
				post("/booking");
		return response;
	}
	
	
	/*
	 * This method is use to create booking using POJO-Plain Old JAVA Object
	 */
	protected Response createBookingWithPOJO() {
		Bookingdates bookingdates = new Bookingdates("2025-04-04", "2025-04-08");
		Booking booking = new Booking("Ankita", "Bhogal", 200, false, bookingdates, "Baby crib");
		
		Response response = RestAssured.given(specs).
				contentType(ContentType.JSON).
				body(booking).
				post("/booking");
		
		//response.print();
		Bookingid bookingid = response.as(Bookingid.class);
		
		
		System.out.println("Request booking : " + booking.toString());
		System.out.println("Booking ID: "+ bookingid.getBookingid() +"\nResponse booking: " + bookingid.getBooking().toString());
		return response;
	}
	
	
}
