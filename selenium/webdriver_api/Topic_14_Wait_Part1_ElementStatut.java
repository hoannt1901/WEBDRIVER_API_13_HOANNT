package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_14_Wait_Part1_ElementStatut {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
    WebDriverWait  waitExplicit;
	boolean status;

	
	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		waitExplicit = new WebDriverWait(driver,5);

		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_ElementDisplayedOrVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Đk 1 - Element có hiển thị trên UI + có trong DOM		
		 waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));  // Kiểm tra element displayed/ visible
		 status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed();  // Kiểm tra Element displayed
		
		 System.out.println("Element có hiển thị trên UI + có trong DOM ="+ status);
		
		// Đk 2 - Element không hiển thị trên UI + có trong DOM
		 waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));  // Chờ cho Element undisplayed/ invisible
		
		 status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		 System.out.println("Element không hiển thị trên UI + có trong DOM ="+ status);
		 
		// Đk 3 - Element không hiển thị trên UI +  không có trong DOM
		// waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		 
		// status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		// System.out.println("Element không hiển thị trên UI +  không có trong DOM ="+ status);
	}
	@Test
	public void TC_02_ElementUnDisplayOrInvisible() {	
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Đk 1 - Element có hiển thị trên UI + có trong DOM
		// waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));				
		
		// Đk 2 - Element không hiển thị trên UI + có trong DOM		 
		 waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		// Đk 3 - Element không hiển thị trên UI + có trong DOM		 
		 waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		 
		 
	}
	@Test

	public void TC_03ElementPresence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Đk 1 - Element có hiển thị trên UI + có trong DOM
		 waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));				
		
		// Đk 2 - Element không hiển thị trên UI + có trong DOM		 
		 waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		// Đk 3 - Element không hiển thị trên UI + có trong DOM		 
		// waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));
		 
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}