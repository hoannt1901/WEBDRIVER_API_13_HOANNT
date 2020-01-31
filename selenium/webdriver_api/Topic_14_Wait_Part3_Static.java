package webdriver_api;



import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;



public class Topic_14_Wait_Part3_Static {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;



	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s 
		// Aplly cho các hàm FindElement
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Aplly chờ cho page đc load xong
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS );
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_Static() throws Exception  {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start Sleep"+ getCurrentTime());
		Thread.sleep(5000);
		System.out.println("Start End"+ getCurrentTime());
	}
	
	public String getCurrentTime() {
		Date date=new Date();  
		return date.toString();
	}
	@Test
	public void TC_02() {	
		driver.get("");
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