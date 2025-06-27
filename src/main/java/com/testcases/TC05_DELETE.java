package com.testcases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commonmethods.Common_Method_DELETE;
import com.commonmethods.Common_Method_GET;
import com.reports.DataLibrary;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC05_DELETE  extends Common_Method_GET {
	    
	    @BeforeTest
	    public void startTest() {
	    	testcaseName = "Deleting an Employee";
			testcaseDec = "TC05 Deleting Employee";
			author = "Automation Tester";
			category = "Functional Test Case";
	    }
	    
	    @Test(dataProvider = "fetchData")
	    public void deleteEmployee(String baseURI, String userId) {
	        
	        // Set base URI
	        RestAssured.baseURI = baseURI;
	        
	        // Send DELETE request and get response
	        Response response = Common_Method_DELETE.sendDeleteRequest(baseURI, userId);
	       
	        // Verify Status Code (204 - No Content)
	        Common_Method_DELETE.verifyStatusCode(response, 204);
	    }
	    
	    
	    // This DataProvider is different from other requests because we need to read only two column from excel
	    @DataProvider(name = "fetchData")
	    public String[][] getdata() throws IOException {
	        String[][] rawData = DataLibrary.readdata();
	        
	        // Ensure we return only two columns (baseURI & userId)
	        String[][] filteredData = new String[rawData.length][2];
	        
	        for (int i = 0; i < rawData.length; i++) {
	            filteredData[i][0] = rawData[i][0]; // baseURI
	            filteredData[i][1] = rawData[i][1]; // userId
	        }
	        
	        return filteredData;
	    }}

