package Booking.APITest2;

public class payLoad {

	public static String LoginDetails(String username, String password) {

		return ("{\r\n" + "    \"username\" : \"" + username + "\",\r\n" + "    \"password\" : \"" + password + "\"\r\n"
				+ "}");
	}

	public static String CreateBooking(String firstname, String lastname, int totalprice, boolean depositpaid,
			String checkin, String checkout, String additionalneeds) {

		return ("{\r\n" + "    \"firstname\" : \"" + firstname + "\",\r\n" + "    \"lastname\" : \"" + lastname
				+ "\",\r\n" + "    \"totalprice\" : " + totalprice + ",\r\n" + "    \"depositpaid\" : " + depositpaid
				+ ",\r\n" + "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"" + checkin + "\",\r\n"
				+ "        \"checkout\" : \"" + checkout + "\"\r\n" + "    },\r\n" + "    \"additionalneeds\" : \""
				+ additionalneeds + "\"\r\n" + "}");
	}

	public static String UpdateBooking(String firstname, String lastname, int totalprice, boolean depositpaid,
			String checkin, String checkout, String additionalneeds) {

		return ("{\r\n" + "    \"firstname\" : \"" + firstname + "\",\r\n" + "    \"lastname\" : \"" + lastname
				+ "\",\r\n" + "    \"totalprice\" : " + totalprice + ",\r\n" + "    \"depositpaid\" : " + depositpaid
				+ ",\r\n" + "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"" + checkin + "\",\r\n"
				+ "        \"checkout\" : \"" + checkout + "\"\r\n" + "    },\r\n" + "    \"additionalneeds\" : \""
				+ additionalneeds + "\"\r\n" + "}");
	}
}
