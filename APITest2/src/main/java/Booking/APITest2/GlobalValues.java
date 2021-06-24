package Booking.APITest2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalValues {

	public Properties PropertyFile() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\61435\\eclipse-workspace\\APITest2\\src\\main\\java\\Booking\\APITest2\\global.properties");
		prop.load(fis);

		// Can be used in Future
		// FileOutputStream fos=new FileOutputStream("");
		return prop;
	}
}
