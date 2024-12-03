package Utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static void getScreenshot (WebDriver driver) {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		File dest = new File("D:\\Ruksar_SeleniumDemos\\OhrmScreenshot" + System.currentTimeMillis() + ".png");
		try {

			FileHandler.copy(src, dest);
		}
		catch(IOException e) {
			//TODO Auto-generated Catch Block
			e.printStackTrace();

		}
	}
}
