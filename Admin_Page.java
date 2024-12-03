package Scenario2;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Admin_Page {
	private WebDriver driver;

	public Admin_Page(WebDriver d) {
		this.driver = d;
		PageFactory.initElements(driver, this);
	}


	@FindBy (xpath = "//*[@id=\\\"app\\\"]/div[1]/div[1]/aside/nav/div[2]/ul")
	private List<WebElement> all_menu;

	@FindBy (xpath = "/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li")
	private List<WebElement> menu_items;   

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")
	private WebElement admin_Link;

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
	private WebElement username;

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
	private WebElement search_button;

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")
	private WebElement records_found;

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]")
	private List<WebElement> drop_down;

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i")
	private List<WebElement> drop_down_items;

	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]")
	private List<WebElement> user_status_items;

	public List<WebElement> getUser_status_items() {
		return user_status_items;
	}

	public List<WebElement> getDrop_down() {
		return drop_down;
	}

	public List<WebElement> getdrop_down_items() {
		return drop_down_items;
	}

	public WebElement getRecords_found() {
		return records_found;
	}

	public WebElement getUsername() {
		return username;
	}

	public WebElement getSearch_button() {
		return search_button;
	}

	public List<WebElement> getMenu_items() {
		return menu_items;
	}

	public WebElement getAdmin_link() {
		return admin_Link;
	}


	@Test 
	public void menu_options() {
		System.out.println("Total number of Menu items:" + getMenu_items().size());
		getAdmin_link().click();
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("admin"), "URL does not match");
		System.out.println("Logged into Admin Page");
	} 

	@Test
	public void searchByUserName(String un) {
		getUsername().sendKeys(un);
		getSearch_button().click();
		try {
			Thread.sleep(2000);
		}catch (InterruptedException e) {
			//TODO Auto-Generated Catch Block
			e.printStackTrace();
		}
		System.out.println("Total records with username: " + un + " is: " + getRecords_found().getText());
		driver.navigate().refresh();
	}
	@Test
	public void searchByUserRole() {
		getDrop_down().get(0).click();
		for (WebElement i : getdrop_down_items()) {
			if(i.getText().contains("Admin")) {
				i.click();
				break;
			}
		}

		getSearch_button().click();
		try {
			Thread.sleep(2000);
		}catch (InterruptedException e) {
			//TODO Auto-Generated Catch Block
			e.printStackTrace();
		}
		System.out.println("Total records with User Role Admin: " + getRecords_found().getText());
		driver.navigate().refresh();
	}


	public void searchByUserStatus() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			//TODO Auto-Generated Catch Block
			e.printStackTrace();
		}
		getDrop_down().get(0).click();
		for(WebElement i : getdrop_down_items()) {
			if(i.getText().contains("Enabled"))
				i.click();
			break;
		}

		getSearch_button().click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			//TODO Auto-Generated Catch Block
			e.printStackTrace();
		}

		System.out.println("Total records enabled: " + getRecords_found().getText());

	}
}










