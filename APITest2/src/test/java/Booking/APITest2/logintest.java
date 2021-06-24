package Booking.APITest2;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class logintest {
	static GlobalValues gv = new GlobalValues();

	public static void main(String[] args) throws IOException {

		// get baseURI
		Properties prop = gv.PropertyFile();
		String baseURI = prop.getProperty("baseURL");
		// post
		RestAssured.baseURI = baseURI;

		String response = given().header("Content-Type", "application/json").body(payLoad.LoginDetails()).when()
				.post("auth").then().assertThat().statusCode(200).extract().response().asString(); // System.out.println(response);

		// capture token
		JsonPath js = new JsonPath(response);
		String token = js.getString("token");
		// System.out.println(token);

		// get BookingId(all)

		String response2 = given().header("Content-Type", "application/json").when().get("booking").then().assertThat()
				.statusCode(200).extract().response().asString();
		// System.out.println(response2);
		JsonPath js2 = new JsonPath(response2);// js2
		int first = js2.getInt("[0].bookingid");

		// System.out.println(first);

		// get the booking

		String response3 = given().header("Content-Type", "application/json").when().get("booking/" + first + "").then()
				.assertThat().statusCode(200).extract().response().asString();
		System.out.println(response3);
		JsonPath js3 = new JsonPath(response3);
		String firstname = js3.getString("firstname");
		String lastname = js3.getString("lastname");
		String checkin = js3.getString("bookingdates.checkin");
		String checkout = js3.getString("bookingdates.checkout");
		// System.out.println(checkin);

		// get BookingId with query parameter

		String response4 = given().header("Content-Type", "application/json").queryParam("firstname", firstname)
				.queryParam("lastname", lastname).when().get("booking").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response4);
		// check in date and check out
		String response5 = given().header("Content-Type", "application/json").queryParam("checkin", checkin)
				.queryParam("checkout", checkout).when().get("booking").then().assertThat().statusCode(200).extract()
				.response().asString();
		// System.out.println(response5);

		// create booking scenario1

		String response6 = given().header("Content-Type", "application/json").header("Accept", "application/json")
				.body(payLoad.CreateBooking()).when().post("booking").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response6);

		// update booking
		String response7 = given().header("Content-Type", "application/json").header("Accept", "application/json")
				.header("Cookie", "token=" + token + "").body(payLoad.UpdateBooking()).when()
				.put("booking/" + first + "").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response7);

	}

}
