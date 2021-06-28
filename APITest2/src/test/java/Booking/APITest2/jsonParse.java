package Booking.APITest2;

import io.restassured.path.json.JsonPath;

public class jsonParse {

	public static JsonPath rawToJson(String res) {
		JsonPath js = new JsonPath(res);
		return js;
	}
}
