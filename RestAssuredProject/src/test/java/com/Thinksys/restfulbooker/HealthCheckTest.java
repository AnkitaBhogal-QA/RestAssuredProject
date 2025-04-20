package com.Thinksys.restfulbooker;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


/**
 * 
 * @author Ankita
 *
 */


public class HealthCheckTest extends BaseTest {

	
	/*
	 * A simple health check endpoint to confirm whether the API is up and running.
	 * Response:  201 Created
	 */
	
	@Test
	public void healthCheckTest()
	{
		 Response response = RestAssured.given(specs).get("/ping");

	     int statusCode = response.getStatusCode();
	     
	     System.out.println("Response: " + response.asString());
	     
	     Assert.assertEquals(statusCode, 201, "Expected status code is not returned.");
	}
	
	
	@Test
	public void headersAndCookiesTest()
	{
		Header someheader = new Header("some_name", "some_value");
		specs.header(someheader);
		
		Cookie somecookie = new Cookie.Builder("some_Cookie_name", "some_cookie_value").build();
		specs.cookie(somecookie);
		
		 Response response = RestAssured.given(specs).
				 cookie(somecookie).
				 header(someheader).log().all().
				 get("/ping");

	     //Get Headers
		 Headers header = response.getHeaders();
		 System.out.println("Headers: " + header);
		 
		 //Two-ways to get the particular parameter from the headers
		 
		 //1. we dont need to hard cord the param name while sys out
		 Header serverHeader1 = header.get("Server");
		 System.out.println(serverHeader1.getName() + ";" + serverHeader1.getValue());
		 
		 //2. we have to hard cord the param name while sys out
		 String serverHeader2 = header.getValue("Report-To");
		 System.out.println("Report-To" + serverHeader2);
		 
		 //Get Cookies
		 Cookies cookie = response.getDetailedCookies();
		 System.out.println("Cookies: " + cookie);
	}
	
}
