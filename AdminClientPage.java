package Scenario2;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AdminClientPage {
	WebDriver driver;
	Admin_Page ap;
	Login_Page lp;

	@BeforeTest
	public void beforeTest() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		ap = new Admin_Page(driver);
		lp= new Login_Page(driver);
	}

	@Test
	public void testLogin_AdminPage() {
		lp.Login("Admin", "admin123");
		ap.menu_options();
		ap.searchByUserName("Admin");
		ap.searchByUserRole();
		ap.searchByUserStatus();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}




