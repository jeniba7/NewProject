package com.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.commonmethods.Common_Method_GET;
import com.reports.DataLibrary;

import io.restassured.RestAssured;

public class TC01_GETRequest extends Common_Method_GET {
	
	@BeforeTest
	public void startTest() {
		testcaseName="Fetching the Employee details";
		testcaseDec="TC01 Getting Employee Details";
		author="Automation tester";
		category="Functional Test Case";
	}
	
	@Test (dataProvider ="fetchData")
	public void getEmployeeDetails(String baseURI, String UserId) {
		
		    RestAssured.baseURI=baseURI;
		    httprequest = RestAssured.given(); 
		   		    
		    Common_Method_GET.getResponseBody(UserId);
		    Common_Method_GET.getStatusCode(UserId);
		    Common_Method_GET.getStatusLine(UserId);
		    Common_Method_GET.verifyStatusCode(UserId,"200");
		  //  Common_Methods.verifyData(UserId, "data.first_name","Sarvin Dhawan"); // Use for reqres.in
		  //  Common_Methods.verifyData(UserId, "data.last_name", "Atmaja Guneta");
	}
	
	@DataProvider(name ="fetchData")
	public String[][] getdata() throws IOException{
		return DataLibrary.readdata();
	}

}
