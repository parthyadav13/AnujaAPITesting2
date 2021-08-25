package APITest.API;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalValues {

	public Properties PropertyFile() {
		Properties prop = new Properties();
		try {

			FileInputStream fis = new FileInputStream(
					"C:\\Users\\61435\\eclipse-workspace\\APITest2\\src\\main\\java\\Booking\\APITest2\\global.properties");
			prop.load(fis);

		} catch (IOException e) {
			System.out.println(e);

		}

		// Can be used in Future
		// FileOutputStream fos=new FileOutputStream("");
		return prop;
	}
}
