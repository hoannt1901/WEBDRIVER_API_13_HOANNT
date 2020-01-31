package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_05_Element_II {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;

	By emailTextboxBy = By.xpath("//input[@id='mail']");		
	By age18RadioBy = By.xpath("//input[@id='under_18']");
	By educationTextAreaBy = By.xpath("//textarea[@id='edu']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");

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
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextbox = driver.findElement(emailTextboxBy);	
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
		}
		
		WebElement age18Radio = driver.findElement(age18RadioBy);	
		if (age18Radio.isDisplayed()) {
			age18Radio.click();
		}
		
		WebElement educationTextArea = driver.findElement(educationTextAreaBy);	
		if (educationTextArea.isDisplayed()) {
			educationTextArea.sendKeys("Automation Testing");
		}
		
		
	}
	@Test
	public void TC_02_Enabled() {	
		
		Assert.assertTrue(isElementEnable(emailTextboxBy));
		Assert.assertTrue(isElementEnable(age18RadioBy));
		Assert.assertTrue(isElementEnable(educationTextAreaBy));
		Assert.assertFalse(isElementEnable(passwordTextboxBy));		
	}
	@Test

	public void TC_03_Selected() {
		// Chưa click chọn nên un-select
		driver.navigate().refresh();
		Assert.assertFalse(isElementSelected(developmentCheckbox));
		Assert.assertFalse(isElementSelected(age18RadioBy));
		
		// Click chọn
		clickToElement(age18RadioBy);
		clickToElement(developmentCheckbox);
		
		// Selected
		Assert.assertTrue(isElementSelected(age18RadioBy));
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		
		// Click lần nữa -> bỏ chọn
		clickToElement(developmentCheckbox);
		
		// Radio -> Selected
		Assert.assertTrue(isElementSelected(age18RadioBy));
		
		// Checkbox -> Un-Selected/ deselected
		Assert.assertFalse(isElementSelected(developmentCheckbox));
		
	}
	
	public boolean isElementDisplayed (By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isElementEnable (By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element ["+ by+"] is enabled");
			return true;
		} else {
			System.out.println("Element ["+ by+"] is displayed");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element ["+ by+"] is selected");
			return true;
		} else {
			System.out.println("Element ["+ by+"] is unselected");
			return false;
		}
	}
	
	public void sendkeyToElement(By by,String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}