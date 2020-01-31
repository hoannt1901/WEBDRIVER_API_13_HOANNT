package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_06_Textbox_TextArea {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
    String username = "mngr243737";
    String password = "vugerAb";
    String customerID;
    
    // Inout in New Customer (user)/ Output (server) data
    String customername = "Jason Staham";
    String gender = "male";
    String dateOfBirth ="1983-06-06";
    String address ="255 PA Hamlet";
    String city = "Hawaii";
    String state = "NewYork";
    String pin = "999777";
    String phone = "0988555777";
    String email = "jsstaham"+ randomNumber() + "@hotmail.com";

    //Input in Edit Customer
    String editAddress ="255 PA Boxing";
    String editCity = "new Jesey";
    String editState = "Stock";
    String editPin = "888666";
    String editPhone = "0958666777";
    String editEmail = "jsstaham"+ randomNumber() + "@gmail.com";
    
    // Locator for New/ edit customer form
    By nameTextbox = By.name("name");
    By genderRadio = By.xpath("//input[@value='m']");
    By genderTextbox = By.name("gender");
    By dobTextbox=By.name("dob");
    By addressTextArea = By.name("addr");
    By cityTextbox = By.name("city");
    By StateTextbox = By.name("state");
    By pinTextbox = By.name("pinno");
    By phoneTextbox = By.name("telephoneno");
    By emailTextbox = By.name("emailid");
    By passwordTextbox = By.name("password");
    	
    
	// Pre-Condition
	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		String homepageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homepageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : "+ username +"']")).isDisplayed());
		
		
	}

	@Test
	public void TC_01_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// input data to New Customer form
		driver.findElement(nameTextbox).sendKeys(customername);
		driver.findElement(genderRadio).click();
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(StateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3'and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		// verify output data = input data\
		Assert.assertEquals(customername, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Thread.sleep(5000);
		System.out.println("Customer ID at New Customer from ="+ customerID);
		
	}
	@Test
	public void TC_02_Edit_Customer() {	
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		System.out.println("Customer ID at Edit Customer from = "+ customerID );
		
		driver.findElement(By.name("AccSubmit")).click();
		
		// Verify Name/ Gender / DOB is disabled fields
		Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());
		
		// Verify output at Edit Customer form = input at new Customer from
		Assert.assertEquals(customername, driver.findElement(nameTextbox).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderTextbox).getAttribute("value"));
		Assert.assertEquals(dateOfBirth, driver.findElement(dobTextbox).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextArea).getText());
		Assert.assertEquals(city, driver.findElement(cityTextbox).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(StateTextbox).getAttribute("value"));
		Assert.assertEquals(pin, driver.findElement(pinTextbox).getAttribute("value"));
		Assert.assertEquals(phone, driver.findElement(phoneTextbox).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailTextbox).getAttribute("value"));
		
		// Edit  data at Edit Customer form
		
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(editAddress);
		
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		
		driver.findElement(StateTextbox).clear();
		driver.findElement(StateTextbox).sendKeys(editState);
		
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(editPhone);
		
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
	}
	@Test

	public void TC_03() {
		driver.get("");
	}
	
	public int randomNumber() {
		Random rand= new Random();
		return rand.nextInt(100000);
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}