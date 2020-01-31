package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_07_Dropdown_List_II {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	Select select;


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
	public void TC_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText() , "Mobile Testing");
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText() , "Manual Testing");
		
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText() , "Functional UI Testing");
		
		Assert.assertEquals(select.getOptions().size(), 10);
		
		select = new Select(driver.findElement(By.id("job2")));
		
		Assert.assertTrue(select.isMultiple());
		
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Security");
		select.selectByVisibleText("Intergration");
		
		List <WebElement> optionSelcted =  select.getAllSelectedOptions();
		Assert.assertEquals(optionSelcted.size(), 4);
		
		List <String> arraySelected = new ArrayList<String>();
		
		for(WebElement select: optionSelcted) {
			System.out.println(select.getText());
			arraySelected.add(select.getText());
		}
		
		Assert.assertTrue(arraySelected.contains("Manual"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Security"));
		Assert.assertTrue(arraySelected.contains("Intergration"));
		
		select.deselectAll();
		Thread.sleep(3000);
		
		List<WebElement> optionUnSelcted = select.getAllSelectedOptions();
		Assert.assertEquals(optionUnSelcted.size(), 0);
		
	}
	@Test
	public void TC_02() {	
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertFalse(select.isMultiple());
		
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getFirstSelectedOption().getText() , "1");
		
		select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertFalse(select.isMultiple());
		
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getFirstSelectedOption().getText() , "May");
		
		select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertFalse(select.isMultiple());
		
		Assert.assertEquals(select.getOptions().size(), 112);
		
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getFirstSelectedOption().getText() , "1980");
		
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Hoan");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Nguyen");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("hoan190191"+ randomNumber()+"@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		//List <WebElement> optionSelcted =  select.getAllSelectedOptions();
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