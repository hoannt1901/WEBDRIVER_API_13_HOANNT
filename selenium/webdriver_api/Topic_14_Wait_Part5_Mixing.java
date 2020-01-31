package webdriver_api;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_14_Wait_Part5_Mixing {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	WebDriverWait explicitWait;
	
	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver,10);
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_Found_Element() {
		driver.get("http://demo.guru99.com/");
		
		//Implicit
		System.out.println("Step 01 - Start TC_01_Found_Element"+ new Date() );
		try {
			WebElement emailTextBox = driver.findElement(By.xpath("//input[@name='emailid']"));
			Assert.assertTrue(emailTextBox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("Switch to catch exception!");
		}
		System.out.println("Step 01 - End TC_01_Found_Element"+ new Date() );
		
		//Explicit
		System.out.println("Step 02 - Start TC_02_Found_Element"+ new Date() );

		try {
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='btnlogin']")));
		}catch (Exception ex) {
			System.out.println("Switch to catch exception!");
		}
		System.out.println("Step 02 - End TC_02_Found_Element"+ new Date() );
	}
	@Test
	public void TC_02_Not_Found_Element() {	
		driver.get("http://demo.guru99.com/");
		
		// Implicit(Not found element)
		System.out.println("Step 01 - Start Implicit:"+ new Date() );
		try {
			WebElement emailTextBox = driver.findElement(By.xpath("//input[@name='automation-testing']"));
			Assert.assertTrue(emailTextBox.isDisplayed());
		} catch (NoSuchElementException ex) {
			System.out.println("Step 01 - Nhảy vào Catch");
			System.out.println(ex.getMessage());
		}
		System.out.println("Step 01 - End Implicit:"+ new Date());
		
		// Explicit (Not found - tham số là web element)
		System.out.println("Step 01 - Start Explicit(WebElement):"+ new Date() );
		try {
			WebElement emailTextBox =   explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='automation-testing']"))));
			Assert.assertTrue(emailTextBox.isDisplayed());
		} catch (Exception ex) {
			System.out.println("Step 02 - Nhảy vào Catch");
			System.out.println(ex.getMessage());
		}
		System.out.println("Step 02 - End Explicit(WebElement):"+ new Date() );
		
		//Explicit (Not found - tham số là by)
		System.out.println("Step 03 - Start Explicit(By):"+ new Date() );

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='automation-testing']")));
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Step 03 - End Explicit(By):"+ new Date() );
		
	}
	@Test

	public void TC_03() {
		driver.get("");
	}
	

	public String getCurrentTime() {
		Date date=new Date();  
		return date.toString();
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}