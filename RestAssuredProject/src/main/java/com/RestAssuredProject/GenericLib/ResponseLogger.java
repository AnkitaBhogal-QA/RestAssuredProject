package com.RestAssuredProject.GenericLib;

import io.restassured.response.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResponseLogger extends BaseTest {

    private static final String LOG_FILE = "logs/api-test-log.txt";

    // Append response to a shared log file
    public static void logResponseToSharedFile(Response response, String testName) {
        try {
          //  Files.createDirectories(Paths.get("logs/"));
           // FileWriter writer = new FileWriter(LOG_FILE, true); // `true` enables append mode

        	String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("========== " + testName + " ==========\n");
            writer.write("Execution Time: " + currentDateTime + "\n");
            writer.write("Status Code: " + response.getStatusCode() + "\n");
            writer.write("Response Body:\n" + response.asPrettyString() + "\n");
            writer.write("========== " + testName + " Ends ==========\n\n");

          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
