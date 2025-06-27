package com.testcases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commonmethods.Common_Method_POST;
import com.commonmethods.Common_Method_GET;
import com.reports.DataLibrary;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC02_POSTRequest extends Common_Method_GET {
	    
	    @BeforeTest
	    public void startTest() {
	    	testcaseName = "Creating a new Employee";
			testcaseDec = "TC02 Creating Employee";
			author = "Automation Tester";
			category = "Functional Test Case";
	    }
	    
	    @Test(dataProvider = "fetchData")
	    public void createEmployee(String baseURI, String name, String email, String gender, String status) {
	        
	        // Set base URI
	        RestAssured.baseURI = baseURI;
	        
	        // Create request payload
	        JSONObject requestBody = new JSONObject();
	        requestBody.put("name", name);
	        requestBody.put("email", email);
	        requestBody.put("gender", gender);
	        requestBody.put("status", status);
	        
	        // Send POST request and get response
	        Response response = Common_Method_POST.sendPostRequest(baseURI, requestBody);
	        
	        // Verify Status Code (201 - Created)
	        Common_Method_POST.verifyStatusCode(response, 201);
	        
	        // Verify Response Data
	        Common_Method_POST.verifyResponseData(response, "name", name);
	        Common_Method_POST.verifyResponseData(response, "email", email);
	        Common_Method_POST.verifyResponseData(response, "gender", gender);
	        Common_Method_POST.verifyResponseData(response, "status", status);
	    }
	    
	    @DataProvider(name = "fetchData")
	    public String[][] getdata() throws IOException {
	        return DataLibrary.readdata(); // Ensure Excel contains valid fields for POST (name, email, gender, status)
	    }
	}


