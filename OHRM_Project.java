 package Scenario1;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.Utility;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class OHRM_Project {
	public static WebDriver driver = new ChromeDriver();

	String fPath = "D:\\Ruksar_SeleniumDemos\\MyMavenProject\\ExcelFiles\\LoginData.xlsx";
	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test;
	String extentreportpath = "D:\\Ruksar_SeleniumDemos\\MyMavenProject\\ExtentReport\\report.html";

	@Test(dataProvider = "LoginData")

	public void OhrmLogin(String un, String ps) {
		driver.findElement(By.name("username")).sendKeys(un);
		driver.findElement(By.name("password")).sendKeys(ps);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();

	}

	@DataProvider
	public Object[][] LoginData() {
		int totalRows = sheet.getPhysicalNumberOfRows();
		String[][] LoginData = new String[totalRows - 1][2];

		for (int i = 0; i < totalRows - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < 2; j++) {
				cell = row.getCell(j);
				LoginData[i][j] = cell.getStringCellValue();
			}
		}
		return LoginData;

	}

	@AfterMethod
	public void afterMethod() throws Exception {
		String current_url = driver.getCurrentUrl();
		Assert.assertTrue(current_url.contains(current_url), "URL does not match");
		if (current_url.contains("dashboard")) {
			System.out.println("Test Case Pass");
			Thread.sleep(2000);
			Utility.getScreenshot(driver);
			test = report.createTest("OHRM Valid Login");
			test.log(Status.PASS, MarkupHelper.createLabel("OHRM Login:Pass", ExtentColor.GREEN));
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
			driver.findElement(By.linkText("Logout")).click();
		} else {
			System.out.println("Test Case Fail");
			Thread.sleep(20000);
			Utility.getScreenshot(driver);
			test = report.createTest("OHRM Invalid Login");
			test.log(Status.FAIL, MarkupHelper.createLabel("OHRM Login:Fail", ExtentColor.RED));
		}
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		file = new File(fPath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Extent Report Initialization
		htmlreport = new ExtentSparkReporter(extentreportpath);
		report = new ExtentReports();
		report.attachReporter(htmlreport);

		// Setting environment for Extent report
		report.setSystemInfo("Computer Name", "Lenovo");
		report.setSystemInfo("Tester Name", "Ruksar");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Google Chrome");

		// Configuration of the report
		htmlreport.config().setDocumentTitle("ORHM_Project_Report");
		htmlreport.config().setReportName("OHRM_Project_Details");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setTimeStampFormat("EEEE MM DD, YYYY HH:mm:ss");

	}

	@AfterTest
	public void afterTest() throws IOException {
		wb.close();
		fis.close();
		report.flush();
		driver.quit();
	}
}
