package com.testcases;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commonmethods.Common_Method_PUT;
import com.commonmethods.Common_Method_GET;
import com.reports.DataLibrary;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC03_PUTRequest extends Common_Method_GET {
    
    @BeforeTest
    public void startTest() {
    	testcaseName = "Updating Employee details";
		testcaseDec = "TC03 Updating Employee";
		author = "Automation Tester";
		category = "Functional Test Case";
    }
    
    @Test(dataProvider = "fetchData")
    public void updateEmployee(String baseURI, String userId, String name, String email, String gender, String status) {
        
        // Set base URI
        RestAssured.baseURI = baseURI;
        
        // Create request payload
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("gender", gender);
        requestBody.put("status", status);
        
        // Send PUT request and get response
        Response response = Common_Method_PUT.sendPutRequest(baseURI, userId, requestBody);
        
        // Verify Status Code (200 - OK)
        Common_Method_PUT.verifyStatusCode(response, 200);
        
        // Verify Response Data
        Common_Method_PUT.verifyResponseData(response, "name", name);
        Common_Method_PUT.verifyResponseData(response, "email", email);
        Common_Method_PUT.verifyResponseData(response, "gender", gender);
        Common_Method_PUT.verifyResponseData(response, "status", status);
    }
    
    @DataProvider(name = "fetchData")
    public String[][] getdata() throws IOException {
        return DataLibrary.readdata(); // Ensure Excel contains UserID + valid fields for PUT (name, email, gender, status)
    }
}