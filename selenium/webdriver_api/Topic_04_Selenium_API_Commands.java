package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_04_Selenium_API_Commands {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;



	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		//driver = new ChromeDriver();
		
		//driver = new InternetExplorerDriver();
		
		//driver = new EdgeDriver();
		
		//driver = new SafariDriver();
		
		//driver = new PhantomJSDriver();
		
		//driver = new OperaDriver();
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_Brower() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php");
		String homePageUrl = driver.getCurrentUrl();
		System.out.println(homePageUrl);
		Assert.assertEquals(homePageUrl,"http://live.demoguru99.com/index.php");
		// Trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(),"Home Page");
		//trả về Source của page hiện tại
		Assert.assertTrue(driver.getPageSource().contains("2015 Magento Demo Store."));
		
		// Trả về  cái Windows GUID (Handle Windows) của Windows/ tab hiện tại
		String homePageTabID = driver.getWindowHandle();
		System.out.println("Windows ID"+homePageTabID);
		
		
		// trả về Windows GUID của all tab / windows đang có
		//Set<String> allWindowsID= driver.getWindowHandles();
		//for (String id:allWindowsID) {
		//	System.out.println(id);
		//}
		
		// Khai báo 1 biến element là email textbox
		WebElement emailTextbox= driver.findElement(By.xpath(""));
		
		// Khai báo 1 biến để lấy ra tất cả các link  trên page hiện tại
		 List <WebElement> Links= driver.findElements(By.xpath("a"));
		 
		 // Get ra các log của  tab NetWork
		 driver.manage().logs().get(LogType.PERFORMANCE);
		 
		 // Chờ cho element dc stable để thao tác lên nó trong khoảng thời gian bao nhiêu -> WebDriverWait
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 
		 // Chờ cho Page đc load thành công trong khoảng time bao nhiêu
		 driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		 
		 // Dùng cho Javascript Executor (Asynch)-> Sync
		// driver.manage().timeouts().setScriptTimeout(12,TimeUnit.SECONDS );
		 
		 // F11
		// driver.manage().window().fullscreen();
		 // Get ra vị trí
		 System.out.println(driver.manage().window().getPosition());
		 
		 // Set ra vị trí
		 
		 Point point =new Point(1048,768);
		 driver.manage().window().setPosition(point);
		 
		 Thread.sleep(5000);
		 // Get ra chiều rộng , chiều cao
		 System.out.println(driver.manage().window().getSize());
		 
		 Dimension dimension = new Dimension(1920,1080);
		 driver.manage().window().setSize(dimension);
		 
		 // Giống User sử dụng
		 
		 driver.manage().window().maximize();
		 driver.navigate().back();
		 driver.navigate().forward();
		 driver.navigate().refresh();
		 
		 // Tracking history // GPS/location
		 driver.navigate().to("http://live.demoguru99.com/index.php/customer/account");
		 
		 driver.get("http://live.demoguru99.com/index.php/customer/account");
		 
		 // Alert// Windows/ Frame(IFrame)
		 // Alert
		 driver.switchTo().alert().accept();
		 driver.switchTo().alert().dismiss();
		 driver.switchTo().alert().getText();
		 driver.switchTo().alert().sendKeys("");
		 
		 // Windows
		 driver.switchTo().window("Windows GUID");
		 
		 // Frame
		 driver.switchTo().frame(driver.findElement(By.xpath("")));
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