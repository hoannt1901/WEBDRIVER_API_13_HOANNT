package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_04_Brower_II {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;



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

	//@Test
	public void TC_01_Url() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php");
		
		System.out.println("Step 02 - Click 'My Account' Link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 03 - Verify 'Login Page'Url");
		String LoginPageUrl=driver.getCurrentUrl();
		Assert.assertEquals(LoginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login");
		
		System.out.println("Step 04 - Click 'Creat an account' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Verify 'Register Page' Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");						
	}
	
	//@Test
	public void TC_02_Title() {	
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php");
		
		System.out.println("Step 02 - Click 'My Account' Link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 03 - Verify 'Login Page' Title");
		String LoginPageTitle =driver.getTitle();
		Assert.assertEquals(LoginPageTitle, "Customer Login");
		
		System.out.println("Step 04 - Click 'Creat an account' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Verify 'Register Page' Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	@Test

	public void TC_03_Navigate_Function() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php");
		
		System.out.println("Step 02 - Click 'My Account' Link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 03 - Click 'Creat an account' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 04 - Verify 'Register Page' Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		System.out.println("Step 05 - Back lại Login Page");
		driver.navigate().back();
		
		System.out.println("Step 06 - Verify 'Login Page'Url");
		String LoginPageUrl=driver.getCurrentUrl();
		Assert.assertEquals(LoginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 07 - Forward Register Page");
		driver.navigate().forward();
		
		System.out.println("Step 08 - Verify 'Register Page' Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");

	}
	
	@Test

	public void TC_04_Source() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php");
		
		System.out.println("Step 02 - Click 'My Account' Link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}