package com.commonmethods;

import org.json.simple.JSONObject;
import org.testng.Assert;
import com.reports.GenerateReports;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Common_Method_GET extends GenerateReports {
    
    public static RequestSpecification httprequest;
    public static Response response;
    
    public static void getResponseBody(String UserId) {
        Response response = httprequest.request(Method.GET, "/" + UserId);
        String responseBody = response.getBody().asString();
        System.out.println("The Response Body is: " + responseBody);
        
        if (responseBody != null && !responseBody.isEmpty()) {
            reportStep("Response Body generated successfully", "pass");
        } else {
            reportStep("Issue in generating the response body", "fail");
            Assert.fail("Response body is empty or null");
        }
    }
    
    public static void getStatusCode(String UserId) {
        Response response = httprequest.request(Method.GET, "/" + UserId);
        int statusCode = response.getStatusCode();
        System.out.println("The Status code of the response is: " + statusCode);
    }
    
    public static void getStatusLine(String UserId) {
        Response response = httprequest.request(Method.GET, "/" + UserId);
        String statusLine = response.getStatusLine();
        System.out.println("The Status line of the response is: " + statusLine);
    }
    
    public static void verifyStatusCode(String UserId, String eCode) {
        Response response = httprequest.request(Method.GET, "/" + UserId);
        int statusCode = response.getStatusCode();
        String actualCode = String.valueOf(statusCode);
        
        System.out.println("Expected Status Code: " + eCode + ", Actual Status Code: " + actualCode);
        
        if (actualCode.equals(eCode)) {
            reportStep("Status Code Matched. Successful Response", "pass");
        } else {
            reportStep("Status Code is mismatched. Failure Response", "fail");
            Assert.fail("Expected Status Code: " + eCode + ", but got: " + actualCode);
        }
    }
    
    public static void verifyData(String UserId, String fieldname, String expectedValue) {
        Response response = httprequest.request(Method.GET, "/" + UserId);
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