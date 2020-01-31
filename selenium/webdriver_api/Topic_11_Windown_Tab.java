package webdriver_api;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_11_Windown_Tab {

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

	@Test
	public void TC_01_Windows() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = "+ parentID);
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	
		switchToWindowByID(parentID);
		
		Assert.assertEquals(driver.getTitle(),"Google");
		Thread.sleep(2000);
		
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FROM DEMO");
		Thread.sleep(2000);
		
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		closeAllWindowsWithoutParent(parentID);	
		
	}
	@Test
	public void TC_02() throws InterruptedException {	
		driver.get("http://live.demoguru99.com/index.php/mobile.html");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Thread.sleep(2000);
		
		driver.switchTo().alert().accept();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		
	}
	@Test

	public void TC_03() {
		driver.get("");
	}
	
	public void switchToWindowByID(String parentID) {
		// Lấy ra tất cả các ID đang có
		Set<String> allWindows = driver.getWindowHandles();
		
	//	for(int i=0;i< allWindows.size();i++) {
	//		System.out.println("ID = " + i);
	//	}
		
	//	for(String id: allWindows ) {
	//		System.out.println("ID = "+ id);
	//	}
			
			
	// Dùng vòng lặp duyệt qua từng ID (for-each)		
		for (String termID :allWindows ) {
			// Kiểm tra cái ID nào mà khác vs parent ID thì Switch qua
			if(!termID.equals(parentID)) {
				
				// Switch qua tab/ window đó
				driver.switchTo().window(termID);
				break;
				
			}
		}
	}
	
	public void switchToWindowByTitle(String title) {
		// Lấy ra tất cả các Title đang có
		Set<String> allWindows = driver.getWindowHandles();		
		System.out.println("All Windows size = "+ allWindows.size());	
	// Dùng vòng lặp duyệt qua từng ID (for-each)		
		for (String termID :allWindows ) {
			
			// Switch vafo tuwngf ID truoc
			driver.switchTo().window(termID);
			
			// Get ra title dang co
			String currentWin= driver.getTitle();
			
			// Title nao ma bang title minh dang expected thi break khoi vong
			if(currentWin.equals(title)) {		
				break;
				
			}
		}
	}
	
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();		
		for (String runWindows :allWindows ) {
			if(!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
		
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}