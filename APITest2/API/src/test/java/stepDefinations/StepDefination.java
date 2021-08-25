package stepDefinations;

import java.util.Properties;

import org.junit.Assert;

import APITest.API.GlobalValues;
import APITest.API.payLoad;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reusableMethods.jsonParse;

//@RunWith(Cucumber.class)
public class StepDefination {
	static GlobalValues gv = new GlobalValues();
	Properties prop = gv.PropertyFile();
	String baseURI = prop.getProperty("baseURL");
	// jsonParse j = new jsonParse();
	RequestSpecification reqSpec;
	RequestSpecification reqSpec2;
	RequestSpecification reqSpecwithHeader;

	Response response;
	Response response2;
	Response responseForCreateRequest;
	Response responseForUpdateRequest;
	String token;
	String example;
	int first;
	int bookingidforCreatedBooking;
	// post

	@Given("^Login with payload \"([^\"]*)\" \"([^\"]*)\"$")
	// @Given("^Login with payload \"username\" \"password\"$")
	public void login_with_payload_username_password(String username, String password) throws Throwable {

		RestAssured.baseURI = baseURI;

		/*
		 * RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		 * reqBuilder.setBaseUri(baseURI);
		 * 
		 * reqSpec = reqBuilder.build(); reqSpec.given().header("Content-Type",
		 * "application/json").body(payLoad.LoginDetails(username, password));
		 */
		reqSpec = RestAssured.given().header("Content-Type", "application/json")
				.body(payLoad.LoginDetails(username, password));
		// String response = reqSpec

		// .when().post("auth").then().assertThat().statusCode(200).extract().response().asString();
		// System.out.println(response);

		// capture token
		// jsonParse j = new JsonParse();
		// JsonPath js = jsonParse.rawToJson(response);
		// String token = js.getString("token");
		// System.out.println(token);

	}

	@When("^user login with Post http request$")
	public void user_login_with_Post_http_request() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		response = reqSpec.when().post("auth");

	}

	@Then("^the API call got success with status code \"([^\"]*)\"$")
	public void the_api_call_got_success_with_status_code_200(int code) throws Throwable {
		int actualcode = response.getStatusCode();
		String res = response.getBody().asString();
		JsonPath js = jsonParse.rawToJson(res);
		token = js.getString("token");
		example = token;
		Assert.assertEquals(code, actualcode);
		// System.out.println("-----------------------------Magic begins from
		// here----------------------------------");
		// System.out.println(res);
		System.out.println(token);
	}

	@Given("^API request for Booking$")
	public void api_request_for_Booking() throws Throwable {
		RestAssured.baseURI = baseURI;
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();
		reqSpec2 = RestAssured.given().header("Content-Type", "application/json");
		reqSpecwithHeader = reqSpec2.header("Accept", "application/json");

		/*
		 * String response2 = given().header("Content-Type",
		 * "application/json").when().get("booking").then().assertThat()
		 * .statusCode(200).extract().response().asString();
		 * System.out.println(response2);
		 */
	}

	@When("^Get http request$")
	public void get_http_request() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();
		response2 = reqSpec2.when().get("booking");

	}

	@Then("^Get all booking ids$")
	public void get_all_booking_ids() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();

		String res = response2.getBody().asString();
		JsonPath js2 = jsonParse.rawToJson(res);
		first = js2.getInt("[0].bookingid");
		System.out.println(res);

	}

	@Then("^Verify the booking details firstname, lastname, checkin, checkout$")
	public void verify_the_booking_details_firstname_lastname_checkin_checkout() throws Throwable {

		String response3 = reqSpec2.when().get("booking/" + first + "").then().assertThat().statusCode(200).extract()
				.response().asString();
		// System.out.println(response3);
		JsonPath js3 = jsonParse.rawToJson(response3);
		String firstname = js3.getString("firstname");
		String lastname = js3.getString("lastname");
		String checkin = js3.getString("bookingdates.checkin");
		String checkout = js3.getString("bookingdates.checkout");

		System.out.println(firstname + "," + lastname + "," + checkin + "," + checkout);
	}

	@When("^Post http request using \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void post_http_request_using(String firstname, String lastname, int totalprice, Boolean depositpaid,
			String checkin, String checkout, String additionalneeds) throws Throwable {

		responseForCreateRequest = reqSpecwithHeader.body(
				payLoad.CreateBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds))
				.when().post("booking");
		// .then().assertThat().statusCode(200).extract().response().asString();
		// System.out.println(response6);
	}

	@Then("^verify that the booking is created with status code \"([^\"]*)\"$")
	public void verify_that_the_booking_is_created_with_status_code(int code) throws Throwable {

		int actualcode = responseForCreateRequest.getStatusCode();
		String res = responseForCreateRequest.getBody().asString();
		JsonPath js = jsonParse.rawToJson(res);
		Assert.assertEquals(code, actualcode);
		bookingidforCreatedBooking = js.getInt("bookingid");

		// System.out.println(bookingidforCreatedBooking);
	}

	@When("^Put http request using \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void put_http_request_using(String firstname, String lastname, int totalprice, Boolean depositpaid,
			String checkin, String checkout, String additionalneeds) throws Throwable {
		System.out.println(token);
		responseForUpdateRequest = reqSpecwithHeader.header("Cookie", "token=" + token + "").body(
				payLoad.UpdateBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds))
				.when().put("booking/" + first + "");
		// System.out.println(responseForUpdateRequest);

	}

	@Then("^verify that the booking is updated with status code \"([^\"]*)\"$")
	public void verify_that_the_booking_is_updated_with_status_code(int code) throws Throwable {

		int actualcode = responseForUpdateRequest.getStatusCode();
		Assert.assertEquals(code, actualcode);
		String res = responseForUpdateRequest.getBody().asString();
		JsonPath js = jsonParse.rawToJson(res);
		// System.out.println(js.getString("firstname"));

	}

	@Given("^Get the token\"([^\"]*)\" \"([^\"]*)\"$")
	public void get_the_token(String username, String password) throws Throwable {
		reqSpec = RestAssured.given().header("Content-Type", "application/json")
				.body(payLoad.LoginDetails(username, password));
		reqSpec.log().all();
		response = reqSpec.when().post("auth");
		String res = response.getBody().asString();
		JsonPath js = jsonParse.rawToJson(res);
		token = js.getString("token");
		System.out.println(token);

		reqSpec2 = RestAssured.given().header("Content-Type", "application/json");
		response2 = reqSpec2.when().get("booking");
		String res2 = response2.getBody().asString();
		JsonPath js2 = jsonParse.rawToJson(res2);
		first = js2.getInt("[0].bookingid");

	}

	@When("^Delete http request using \"([^\"]*)\"$")
	public void delete_http_request_using(int bookingid) throws Throwable {

		responseForUpdateRequest = reqSpecwithHeader.header("Cookie", "token=" + token + "").when()
				.delete("booking/" + bookingid + "");

	}

	@Then("^verify that the booking is deleted with status code \"([^\"]*)\"$")
	public void verify_that_the_booking_is_deleted_with_status_code(int code) throws Throwable {

		int actualcode = responseForUpdateRequest.getStatusCode();
		Assert.assertEquals(code, actualcode);
	}

}
