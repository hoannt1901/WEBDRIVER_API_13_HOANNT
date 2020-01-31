package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_03_Xpath_Part_1EX {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	String fisrtName="Nguyen";
	String lastName="Hoan";
	
	String validEmail="hoan1901"+randomNumber()+"@gmail.com";
	String validPassword="123456";

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
	public void TC_05_CreateNewAccount() {
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		System.out.println("RandomEmail="+validEmail);
		
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(fisrtName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']/span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());	
		
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),"MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("strong[test()='Hello,"+fisrtName+""+lastName+"!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),"+fisrtName+""+lastName+"!']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+validEmail+"']")).isDisplayed());
		
       
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
	}
	public void TC_06_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),"MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("strong[test()='Hello,"+fisrtName+""+lastName+"!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),"+fisrtName+""+lastName+"!']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'"+validEmail+"']")).isDisplayed());			
						
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}

public int randomNumber() {
	Random rand= new Random();
	return rand.nextInt(100000);
}

}