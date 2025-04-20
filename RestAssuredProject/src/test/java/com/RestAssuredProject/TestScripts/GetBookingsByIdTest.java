package com.RestAssuredProject.TestScripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.RestAssuredProject.GenericLib.BaseTest;
import com.RestAssuredProject.GenericLib.ResponseLogger;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

/**
 * 
 * @author Ankita
 *
 */

public class GetBookingsByIdTest extends BaseTest {
	
	
	/*
	 * Returns a specific booking based upon the booking id provided
	 * Response: 200 OK
{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
	 */
	
	@Test
	public void getBookingsById()
	{
		
		Response responseCreate = createBooking();
		responseCreate.print();

		int bookingId = responseCreate.jsonPath().getInt("bookingid");
		
		specs.pathParam("bookingID", bookingId);
		
		Response response = RestAssured.given(specs).get("/booking/{bookingID}");
		
		response.print();
		
		int statusCode= response.statusCode();
		
		//Here we have used hard assert because response matter to our execution
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		ResponseLogger.logResponseToSharedFile(response, "getBookingsById");
		
		//Here we will use soft assert for validation so that even if it fails it does not impacts our execution
		SoftAssert softassert = new SoftAssert();
		
		String firstName = response.jsonPath().getString("firstname");
		softassert.assertEquals(firstName, "Ankita", "Firstname does not match");
		
		String lastName = response.jsonPath().getString("lastname");
		softassert.assertEquals(lastName, "Bhogal", "Lastname does not match");
		
		String checkin = response.jsonPath().getString("bookingdates.checkin");
		softassert.assertEquals(checkin, "2025-04-04", "Checkin does not match");
		
		softassert.assertAll();
	}
	
	@Test
	public void getBookingsXMLById()
	{
		
		
		Response responseCreate = createBooking();
		responseCreate.print();
		
		Header xml = new Header("Accept", "application/xml");
		specs.header(xml);

		int bookingId = responseCreate.jsonPath().getInt("bookingid");
		
		specs.pathParam("bookingID", bookingId);
		
		Response response = RestAssured.given(specs).get("/booking/{bookingID}");
		
		response.print();
		
		int statusCode= response.statusCode();
		
		//Here we have used hard assert because response matter to our execution
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		ResponseLogger.logResponseToSharedFile(response, "getBookingsXMLById");
		
		//Here we will use soft assert for validation so that even if it fails it does not impacts our execution
		SoftAssert softassert = new SoftAssert();
		
		String firstName = response.xmlPath().getString("booking.firstname");
		softassert.assertEquals(firstName, "Ankita", "Firstname does not match");
		
		String lastName = response.xmlPath().getString("booking.lastname");
		softassert.assertEquals(lastName, "Bhogal", "Lastname does not match");
		
		String checkin = response.xmlPath().getString("booking.bookingdates.checkin");
		softassert.assertEquals(checkin, "2025-04-04", "Checkin does not match");
		
		softassert.assertAll();
	}

}
