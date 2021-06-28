package Booking.Cucumber.stepDefinations;

import java.util.Properties;

import Booking.APITest2.GlobalValues;
import Booking.APITest2.payLoad;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

//@RunWith(Cucumber.class)
public class StepDefination {
	static GlobalValues gv = new GlobalValues();
	Properties prop = gv.PropertyFile();
	String baseURI = prop.getProperty("baseURL");

	RequestSpecification reqSpec;
	Response response;
	// post

	@Given("^Login with payload \"([^\"]*)\" \"([^\"]*)\"$")
	public void login_with_payload_username_password(String username, String password) throws Throwable {

		RestAssured.baseURI = baseURI;

		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setBaseUri(baseURI);

		reqSpec = reqBuilder.build();
		reqSpec.given().header("Content-Type", "application/json").body(payLoad.LoginDetails(username, password));

		/*
		 * String response = given().header("Content-Type", "application/json")
		 * .body(payLoad.LoginDetails(username,
		 * password)).when().post("auth").then().assertThat().statusCode(200)
		 * .extract().response().asString(); // System.out.println(response);
		 */
		// capture token
		// JsonPath js = jsonParse.rawToJson(response);
		// String token = js.getString("token");
		// System.out.println(token);

	}

	@When("^user login with Post http request$")
	public void user_login_with_Post_http_request() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		response = reqSpec.when().post("auth");
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_api_call_got_success_with_status_code_200(int code) throws Throwable {
		ValidatableResponse res = response.then().assertThat().statusCode(code);
		// JsonPath js = jsonParse.rawToJson(res);
		// String token = js.getString("token");
		System.out.println(res);

	}

}
