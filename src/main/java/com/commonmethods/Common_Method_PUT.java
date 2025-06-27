package com.commonmethods;

import org.json.simple.JSONObject;
import org.testng.Assert;
import com.reports.GenerateReports;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;


public class Common_Method_PUT extends GenerateReports {

	    public static RequestSpecification httprequest;
	    public static Response response;

	    //Bearer Token for authentication 
	    private static final String BEARER_TOKEN = "3323d383a36524e377d3b568f380acf861256cd13c8914121ab33ea28d6d39fc";

	    //Method to send a PUT request with Authorization
	    public static Response sendPutRequest(String baseURI, String userId, JSONObject requestBody) {
	        RestAssured.baseURI = baseURI;
	        httprequest = RestAssured.given();

	        //Setting Headers
	        httprequest.header("Content-Type", "application/json");
	        httprequest.header("Authorization", "Bearer " + BEARER_TOKEN); //Add Authorization Token

	        //Adding request body
	        httprequest.body(requestBody.toString());

	        //Sending PUT request to update the user details
	        response = httprequest.request(Method.PUT, "/" + userId); //PUT requires User ID in URL

	        //Printing response body
	        System.out.println("Response Body: " + response.getBody().asString());

	        return response;
	    }

	    //Method to verify Status Code
	    public static void verifyStatusCode(Response response, int expectedStatusCode) {
	        int actualStatusCode = response.getStatusCode();
	        System.out.println("Expected Status Code: " + expectedStatusCode + ", Actual Status Code: " + actualStatusCode);

	        if (actualStatusCode == expectedStatusCode) {
	            reportStep("Status Code Matched. Successful Response", "pass");
	        } else {
	            reportStep("Status Code is mismatched. Failure Response", "fail");
	            Assert.fail("Expected Status Code: " + expectedStatusCode + ", but got: " + actualStatusCode);
	        }
	    }

	 //Method to verify Response Data
	    public static void verifyResponseData(Response response, String fieldname, String expectedValue) {
	        JsonPath jsonPath = response.jsonPath();
	        Object valueObj = jsonPath.getJsonObject(fieldname);

	        if (valueObj == null) {
	            System.out.println("Field name " + fieldname + " not found or is null");
	            reportStep("Field name " + fieldname + " not found or returned null", "fail");
	            Assert.fail("Field name " + fieldname + " not found or is null");
	        } else {
	            String actualValue = valueObj.toString();
	            System.out.println("Field name: " + fieldname + ", Expected Value: " + expectedValue + ", Actual Value: " + actualValue);

	            if (actualValue.equals(expectedValue)) {
	                reportStep("Actual and Expected value matches for the field: " + fieldname + " successfully", "pass");
	            } else {
	                reportStep("Actual and Expected value doesn't match for the field: " + fieldname, "fail");
	                Assert.fail("Expected Value: " + expectedValue + ", but got: " + actualValue);
	            }
	        }
	    }
	}