package com.commonmethods;


import org.testng.Assert;
import com.reports.GenerateReports;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class Common_Method_DELETE extends GenerateReports {

		     public static RequestSpecification httprequest;
		     public static Response response;

		     //Bearer Token for authentication (Replace with a valid token)
		     private static final String BEARER_TOKEN = "3323d383a36524e377d3b568f380acf861256cd13c8914121ab33ea28d6d39fc";

		     //Method to send a DELETE request with Authorization
		     public static Response sendDeleteRequest(String baseURI, String userId) {
		         RestAssured.baseURI = baseURI;
		         httprequest = RestAssured.given();

		         //Setting Headers
		         httprequest.header("Authorization", "Bearer " + BEARER_TOKEN); //Add Authorization Token

		         //Sending DELETE request to remove the user
		         response = httprequest.request(Method.DELETE, "/" + userId); // DELETE requires User ID in URL

		         //Printing response body
		         System.out.println("Response Body: " + response.getBody().asString());

		         return response;
		     }

		     //Method to verify Status Code (204 - No Content expected for DELETE)
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
		 }


