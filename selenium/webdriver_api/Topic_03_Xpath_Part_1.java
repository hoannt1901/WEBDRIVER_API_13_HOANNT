package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_03_Xpath_Part_1 {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	String username="mngr231005";
	String password="duvabyq";

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
		driver.get("");
	}

	@Test
	public void TC_01() {
		System.out.println("Mở ra trang AUT");
		driver.get("http://live.demoguru99.com");
		
		System.out.println("Click vào My Account link ở dưới Footer");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}
	@Test
	public void TC_02_Start_With() {	
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String welcomeMsgID = driver.findElement(By.xpath("//td[starts-with(text(),'Manger Id :')]")).getText();
		System.out.println("Text="+welcomeMsgID);
		Assert.assertTrue(welcomeMsgID.contains(username));
	}
	@Test

	public void TC_03() {
		driver.get("");
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}