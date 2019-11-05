package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
	@BeforeMethod(description="Chạy trước cho mỗi các test bên dưới")
	public void beforMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}
	
	@Test
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
	}
	@Test
	public void TC_02_LoginWithEmailInvalid() {	
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12344234@12323.123123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-entry-email']")).getText();
		//Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");
	}
	@Test

	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test

	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345345");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		
		String ErrorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(ErrorMsg, "Invalid login or password.");
		
	}
	
	@Test

	public void TC_05_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String myDashboard= driver.findElement(By.xpath("//h1[text()='My Dashboard']")).getText();
		Assert.assertEquals(myDashboard, "MY DASHBOARD");
		
		String hello = driver.findElement(By.xpath("//p[@class='hello']//strong")).getText();
		Assert.assertEquals(hello, "Hello, Automation's Testing!");
		
		String automationTesting = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		Assert.assertEquals(automationTesting, "Automation's Testing");				
		
		
		
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}