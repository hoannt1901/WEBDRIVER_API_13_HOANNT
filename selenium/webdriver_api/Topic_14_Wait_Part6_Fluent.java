package webdriver_api;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_14_Wait_Part6_Fluent {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 5);
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

//	@Test
	public void TC_01_Fluent_WebDriver() {
		driver.get("http://demo.guru99.com/");
		
		// Tổng thời gian cần check
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
		// Tần số mỗi 1s check 1 lần
		.pollingEvery(1,TimeUnit.SECONDS)
		// Nếu gặp exception là find ko thấy element sẽ bỏ qua
		.ignoring(NoSuchElementException.class);
		
		WebElement submitButton = (WebElement) fluentDriver.until(new Function<WebDriver,WebElement>(){
							
			
			@Override
			public  WebElement apply(WebDriver driver) {
				
			System.out.println(new Date());
				return driver.findElement(By.xpath("//input[@name='btnLogin']"));
			}			
		});
		
		submitButton.click();
	}
	private FluentWait<WebDriver> pollingEvery(int i, TimeUnit seconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void TC_02_Fluent_WebElement() {	
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		fluentElement=new FluentWait<WebElement>(countdount);
		
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);
		boolean status = (Boolean) fluentElement.until(new Function<WebElement,Boolean>(){
			@Override
			public Boolean apply(WebElement element) {
				boolean flag = element.getText().endsWith("02");
				System.out.println("Time="+ element.getText());
				return flag;
			}
		});
		System.out.println("status"+ status);
		Assert.assertTrue(status);
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