package com.Thinksys.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 * @author Ankita
 *
 */

public class CreateBookingsTest extends BaseTest {

	/*
	 * Creates a new booking in the API { "firstname" : "Jim", "lastname" : "Brown",
	 * "totalprice" : 111, "depositpaid" : true, "bookingdates" : { "checkin" :
	 * "2018-01-01", "checkout" : "2019-01-01" }, "additionalneeds" : "Breakfast" }
	 */

	@Test(enabled = false)
	public void createBookingTest() {
		Response response = createBooking();
		response.print();

		int statusCode = response.statusCode();

		// Validation
		Assert.assertEquals(statusCode, 200, "Status code is not 200");

		// Here we will use soft assert for validation so that even if it fails it does
		// not impacts our execution
		SoftAssert softassert = new SoftAssert();

		String firstName = response.jsonPath().getString("booking.firstname");
		softassert.assertEquals(firstName, "Ankita", "Firstname does not match");

		String lastName = response.jsonPath().getString("booking.lastname");
		softassert.assertEquals(lastName, "Bhogal", "Lastname does not match");

		String checkin = response.jsonPath().getString("booking.bookingdates.checkin");
		softassert.assertEquals(checkin, "2025-04-04", "Checkin does not match");

		softassert.assertAll();

	}

	@Test
	public void createBookingPOJOTest() {

		Response response = createBookingWithPOJO();

		int statusCode = response.statusCode();

		// Validation
		Assert.assertEquals(statusCode, 200, "Status code is not 200");

		// Here we will use soft assert for validation so that even if it fails it does
		// not impacts our execution
		SoftAssert softassert = new SoftAssert();

		String firstName = response.jsonPath().getString("booking.firstname");
		softassert.assertEquals(firstName, "Ankita", "Firstname does not match");

		String lastName = response.jsonPath().getString("booking.lastname");
		softassert.assertEquals(lastName, "Bhogal", "Lastname does not match");

		String checkin = response.jsonPath().getString("booking.bookingdates.checkin");
		softassert.assertEquals(checkin, "2025-04-04", "Checkin does not match");

		softassert.assertAll();

	}

	/*
	 * private Response createwithPOJO() { Bookingdates bookingdates = new
	 * Bookingdates("2025-04-04", "2025-04-08"); Booking booking = new
	 * Booking("Ankita", "Bhogal", 200, false, bookingdates, "Baby crib");
	 * 
	 * Response response = RestAssured.given(specs). contentType(ContentType.JSON).
	 * body(booking). post("/booking");
	 * 
	 * response.print(); Bookingid bookingid = response.as(Bookingid.class);
	 * 
	 * 
	 * System.out.println("Request booking : " + booking.toString());
	 * System.out.println("Response booking: " + bookingid.getBooking().toString());
	 * return response; }
	 */

}
