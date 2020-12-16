package APITest.Test;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClasses.ResourceDetails;
import utils.Globals;
import utils.setup;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class Getresource extends setup {
	
	
	
	@Test (description = "Check REST server availability.")
	public void ping() {
		RestAssured.baseURI = Globals.baseURL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(CONFIG.getProperty("resourcenum"));
		
				
		String statusLine = response.getStatusLine();
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		System.out.println("Correct status code returned:" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK" );
		System.out.println("Correct status line returned:" +statusLine);
	}
    
	
	@Test (description = "Verify contentType, serverType, acceptLanguage")
	public void basicValidation()
	{
		RestAssured.baseURI = Globals.baseURL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(CONFIG.getProperty("resourcenum"));

		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8" );
		System.out.println("Correct content type returned:" +contentType);

		
		String serverType =  response.header("Server");		
		Assert.assertEquals(serverType, "cloudflare" , "Correct Server value returned");
		System.out.println("Correct Server value returned: " + serverType);
	
		String acceptLanguage = response.header("Content-Encoding");
		Assert.assertEquals(acceptLanguage, "gzip");
		System.out.println("Correct Content-Encoding returned: " + serverType);
	}
	
	
	@Test (description =  "Verify that the starting Employees list is not empty.")
    public void notEmpty() {
		
		RestAssured.baseURI = Globals.baseURL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(CONFIG.getProperty("resourcenum"));
		
		if(!response.asString().isEmpty()){
			System.out.println("Response is Not empty");
		}		
		
    }	
	
	@Test (description = "Verify response time")
	public void validateResponseTime() {
	    when().get(Globals.baseURL+CONFIG.getProperty("resourcenum")).then().time(lessThan(5000L));
	}
	

	
	@Test (description = "Verify the response for the resource ID, Name and Year")
	public void validateResourceDetails(){  		  
		
		RestAssured.baseURI = Globals.baseURL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(CONFIG.getProperty("resourcenum"));
		
				
		ResourceDetails resourceDetails = response.as(ResourceDetails.class);
		
		Assert.assertEquals(resourceDetails.getData().getid()	, "2");
		System.out.println("correct resource ID is returned " );
		Assert.assertEquals(resourceDetails.getData().getname()	, "fuchsia rose");
		System.out.println("correct resource Name is returned ");
		Assert.assertEquals(resourceDetails.getData().getyear()	, "2001");
		System.out.println("correct resource Year is returned " );
		
	}
	
		
	
	@Test (description = "Bad Request")
	public void negTest1(){  		  
		
		Response response= RestAssured.get(Globals.baseURL+"randomtext"+CONFIG.getProperty("resourcenum"));		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode	, 404);
		System.out.println("status code 404 is returned as expected");
		
		
	}
	
	@Test (description = "invalid parameter")
	public void negTest2(){  		  
		
		RestAssured.baseURI = Globals.baseURL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("3");
		
				
		ResourceDetails resourceDetails = response.as(ResourceDetails.class);
		Assert.assertNotEquals(resourceDetails.getData().getid(), "2");
		System.out.println("Resource ID is not equal as expected " );
		
		
		
	}
	
	
	
	
	

}
