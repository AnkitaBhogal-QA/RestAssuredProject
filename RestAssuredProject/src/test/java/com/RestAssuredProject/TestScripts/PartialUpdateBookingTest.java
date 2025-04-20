package com.RestAssuredProject.TestScripts;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.RestAssuredProject.GenericLib.BaseTest;
import com.RestAssuredProject.GenericLib.ResponseLogger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 * @author Ankita
 *
 */

public class PartialUpdateBookingTest extends BaseTest {

	/*
	 * Creates a new booking in the API { "firstname" : "Jim", "lastname" : "Brown",
	 * "totalprice" : 111, "depositpaid" : true, "bookingdates" : { "checkin" :
	 * "2018-01-01", "checkout" : "2019-01-01" }, "additionalneeds" : "Breakfast" }
	 */

	@Test
	public void partialUpldateBookingTest() {
		Response responseCreate = createBooking();
		responseCreate.print();

		int bookingId = responseCreate.jsonPath().getInt("bookingid");

		// Create JSON body for Post request
		JSONObject body = new JSONObject();
		body.put("firstname", "Ashutosh");
		

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2025-04-01");
		
		body.put("bookingdates", bookingdates);

		// Update the request
		Response responseUpdate = RestAssured.given(specs).auth().preemptive().basic("admin", "password123")
				.contentType(ContentType.JSON).body(body.toString())
				.patch("/booking/" + bookingId);

		responseUpdate.print();
		int statusCode = responseUpdate.statusCode();
		
		ResponseLogger.logResponseToSharedFile(responseUpdate, "partialUpldateBookingTest");

		// Validation
		Assert.assertEquals(statusCode, 200, "Status code is not 200");

		// Here we will use soft assert for validation so that even if it fails it does
		// not impacts our execution
		SoftAssert softassert = new SoftAssert();

		String firstName = responseUpdate.jsonPath().getString("firstname");
		softassert.assertEquals(firstName, "Ashutosh", "Firstname does not match");

		String checkin = responseUpdate.jsonPath().getString("bookingdates.checkin");
		softassert.assertEquals(checkin, "2025-04-01", "Checkin does not match");

		softassert.assertAll();

	}

}
