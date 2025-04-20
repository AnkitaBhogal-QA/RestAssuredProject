package com.RestAssuredProject.TestScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestAssuredProject.GenericLib.BaseTest;
import com.RestAssuredProject.GenericLib.ResponseLogger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteBookingTest extends BaseTest {

	@Test
	public void deleteBookingTest() {
		Response responseCreate = createBookingWithPOJO();
		//responseCreate.print();

		int bookingId = responseCreate.jsonPath().getInt("bookingid");

		Response response = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.delete("https://restful-booker.herokuapp.com/booking/" + bookingId);

		response.print();

		int statusCode = response.statusCode();

		Assert.assertEquals(statusCode, 201, "Status code is not 201");
		
		Response responseAfterDelete = RestAssured.given(specs).get("/booking/" +bookingId );
		
		responseAfterDelete.print();
		
		ResponseLogger.logResponseToSharedFile(response, "deleteBookingTest");
		
		Assert.assertEquals(responseAfterDelete.getBody().asString(), "Not Found", "Body should say Not Found");
	}

}
