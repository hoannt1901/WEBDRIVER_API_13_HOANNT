package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_08_Button_Radio_CheckBox_Alert {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	JavascriptExecutor javascript;
	Select select;
	Actions action;
	Alert alert;
	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
	
		javascript = (JavascriptExecutor) driver;
		
		
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	//@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// button
		elementEnbbled("//button[@id='button-disabled']");
		elementSelected("//button[@id='button-disabled']");
		
		// Check Box
		elementEnbbled("//input[@id='development']");
		elementSelected("//input[@id='development']");
		
		//Radio Button
		elementEnbbled("//input[@id='under_18']");
		elementSelected("//input[@id='under_18']");
	}
	@Test
	public void TC_02_ClickByJS() {	
		driver.get("https://demo.nopcommerce.com");
		
	driver.findElement(By.xpath("//a[text()='Desktops ']")).click();
		
	//	javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Desktops ']")).click());
	
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Desktops']")).isDisplayed());
		
	}
	@Test
	public void TC_03_CheckBox() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		String checkboxInput ="//label[text()='Luggage compartment cover']/preceding-sibling::input";
		String checkboxLabel ="//label[text()='Luggage compartment cover']";
		
		
		driver.findElement(By.xpath(checkboxLabel)).click();
		Thread.sleep(3000);
		
		
		elementSelected(checkboxLabel);
		Assert.assertTrue(isElementSelected(checkboxLabel));
		
        driver.findElement(By.xpath(checkboxLabel)).click();
		
		elementSelected(checkboxLabel);
		Assert.assertFalse(isElementSelected(checkboxLabel));
		
	}
	
	
	@Test
	public void TC_04_CheckAndUncheck() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		String newsletterCheckbox = "//input[@id='Newsletter']";
	
		// Click vào checkbox --> Checked
		driver.findElement(By.xpath(newsletterCheckbox)).click();
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
				
		driver.findElement(By.xpath(newsletterCheckbox)).click();
		Assert.assertFalse(isElementSelected(newsletterCheckbox));
		//check
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
		
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
		
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
		
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
		
		//Unckeck
		uncheckToCheckbox(newsletterCheckbox);
		Assert.assertFalse(isElementSelected(newsletterCheckbox));	
	}
	
	@Test
	public void TC_05_Handle_alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']";
		String fullName= "AutomationTester";
		
		// ACcept Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert =driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully ");
		
		// Cancel Alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert =driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");
		
		//Senkeys to alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert =driver.switchTo().alert();
		alert.sendKeys(fullName);
		Assert.assertEquals(alert.getText(), "I am a JS Prompt");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: "+fullName);
		
	}
	@Test
	public void TC_06_Authentication_Alert() {
		String usernameAndPass = "admin";
		String url = "http://the-internet.herokuapp.com.basic_auth";
		url = "http://"+ usernameAndPass + usernameAndPass + "@the-internet.herokuapp.com.basic_auth";
		driver.get(url);
	}
	
	@Test
	public void TC_07_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://the-internet.herokuapp.com.basic_auth");
		
		WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));		
		String url = basicAuthenLink.getAttribute("href");
		
		
		driver.get(byPassAuthenticationAlert(url,username,password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!']")).isDisplayed());
	}
	
	
	private String byPassAuthenticationAlert(String url, String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("Old Url = "+ url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0]+"//" + username+ ":"+ password +"@"+ splitUrl[1];
		System.out.println("New Url = "+ url);
		return url;
	}

	public void elementEnbbled(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isEnabled()) {
			System.out.println("Element is Enabled");
		} else{
			System.out.println("Element is Disabled");
		}
	}
	
	public void elementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			System.out.println("Element is selected");
		} else{
			System.out.println("Element is deselected");
		}
	}
	
	public boolean isElementSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void checkToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			element.click();
		}
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}