package webdriver_api;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_13_Upload_Files {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver
	

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	// Lấy ra cái link bên dưới.
	// F:\eclipse-java-photon-R-x64\workspace\WEBDRIVER_API_13_HOANNT
	// 
	String AppiumPath = projectPath + "\\UploadFiles\\Appium.png";

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
	public void TC_01_Senkeys() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));	
		uploadFile.sendKeys(AppiumPath);
		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='Appium.png']")).isDisplayed());
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