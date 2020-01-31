package webdriver_api;

import java.util.List;
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


public class Topic_10_Popup_Iframe {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	WebDriverWait waitExplicit;


	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		waitExplicit = new WebDriverWait(driver, 10);
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_Popup() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		Thread.sleep(3000);
		
		System.out.println("Step 02 - Count popup number");
		
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy popup  number = " + fancyPopup.size());
		
		// Step 3
		if(fancyPopup.size()>0) {
			System.out.println("Step 03 - Check popup displayed and close");
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
 		
		//boolean fancyPopup = driver.findElement(By.xpath("//div[@class='fancybox-inner']")).isDisplayed();
		//System.out.println("fancy popup displayed = "+fancyPopup );
		//Assert.assertTrue(fancyPopup);
		//driver.findElement(By.cssSelector("*.fancybox-close")).click();
		
		//driver.switchTo().frame(1);
		
		System.out.println("Step 04 - Switch vao facebook iframe");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("facebook iframe displayed = "+ facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		String facebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("Facebook likes number = " + facebookLikes);
		
		Assert.assertEquals(facebookLikes, "170K likes");
		
		driver.switchTo().defaultContent();
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".fancybox-overlay-fixed"))); 
		driver.findElement(By.className("button-login")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit-login")));
		driver.findElement(By.id("btn-submit-login")).click();
		
		WebElement userLogin = driver.findElement(By.xpath("//li[@class='account dropdown wrap']//span[@class='user']"));
		Assert.assertTrue(userLogin.isDisplayed());
		
		Assert.assertEquals(userLogin.getText(), "Automation Fc");
		
		
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