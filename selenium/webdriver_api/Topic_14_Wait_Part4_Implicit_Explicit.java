package webdriver_api;

import static org.junit.Assert.assertTrue;

import java.util.Date;
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


public class Topic_14_Wait_Part4_Implicit_Explicit {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	WebDriverWait waitExplicit;
	By startButtonBy = By.xpath("//div[@id='start']/button");
	By loadingImageBy = By.xpath("//div[@id='loading']/img");
	By helloworldTextBy = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");



	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		// Rõ ràng: Chờ cho element theo từng trạng thái cụ thể
		
		waitExplicit = new WebDriverWait(driver,15);
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 3s
		// Ngầm định: Ko chờ cho element nào có trạng thái cụ thể ---> đi tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	//@Test
	public void TC_01_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Check cho element dc hiển thị (visiable)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		// Click Start button
		System.out.println("Start click"+ getCurrentTime());
		startButton.click();
		System.out.println("End click"+ getCurrentTime());
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		//Check cho Hello World đc hiển thị		
		System.out.println("Start hello world"+ getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End hello world"+ getCurrentTime());
		
		System.out.println("Start display"+ getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End display"+ getCurrentTime());
	}
	//@Test
	public void TC_02_Implicit_Override() {	
driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Check cho element dc hiển thị (visiable)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		// Click Start button
		System.out.println("Start click"+ getCurrentTime());
		startButton.click();
		System.out.println("End click"+ getCurrentTime());
		
		
		//Check cho Hello World đc hiển thị		
		System.out.println("Start hello world"+ getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End hello world"+ getCurrentTime());
		
		System.out.println("Start display"+ getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End display"+ getCurrentTime());
	}
	//@Test
	public void TC_03_Explicit_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// Đợi btn Start có thể click
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		// Click btn
		driver.findElement(startButtonBy).click();
		
		System.out.println("Start wait visible"+ getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
		System.out.println("End wait visible"+ getCurrentTime());
		
		System.out.println("Start display"+ getCurrentTime());
		Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
		System.out.println("End display"+ getCurrentTime());
		
	}
	//@Test
	public void TC_04_Explicit_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// Đợi btn Start có thể click
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		// Click btn
		driver.findElement(startButtonBy).click();
		
		// Loading icon hiển thị và biến mất sau 5s
		System.out.println("Start wait invisible"+ getCurrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
		System.out.println("End wait invisible"+ getCurrentTime());
		
		// wait helloworld đc visible trước khi check display
		System.out.println("Start wait visible"+ getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
		System.out.println("End wait visible"+ getCurrentTime());
		
		System.out.println("Start display"+ getCurrentTime());
		Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
		System.out.println("End display"+ getCurrentTime());
		
	}
	
	//@Test
	public void TC_05_Date_Implicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//in ra ngày được chọn: No Selected Dates to display
		WebElement dataSelectedText= driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dataSelectedText.getText(), "No Selected Dates to display.");		
		System.out.println("Date selected = "+ dataSelectedText.getText());
		
		// Click vào Current day
		driver.findElement(By.xpath("//a[text()='7']")).click();
		
		// Check current day = selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='7']")).isDisplayed());
		Assert.assertEquals(dataSelectedText.getText(), "Tuesday, January 07, 2020");		
		System.out.println("Date selected = "+ dataSelectedText.getText());
	}
	
	@Test
	public void TC_06_Date_Explicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//in ra ngày được chọn: No Selected Dates to display
		WebElement dataSelectedText= driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dataSelectedText.getText(), "No Selected Dates to display.");		
		System.out.println("Date selected = "+ dataSelectedText.getText());
		
		// Click vào Current day
		driver.findElement(By.xpath("//a[text()='7']")).click();
		
		// Chờ cho Loading icon biến mất
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position: absolute')]/div[@class='raDiv']")));
		
		dataSelectedText= driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		// Check current day = selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='7']")).isDisplayed());
		Assert.assertEquals(dataSelectedText.getText(), "Tuesday, January 07, 2020");		
		System.out.println("Date selected = "+ dataSelectedText.getText());
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}
	
	public String getCurrentTime() {
		Date date=new Date();  
		return date.toString();
	}
}