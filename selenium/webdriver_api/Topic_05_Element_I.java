package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_05_Element_I {

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
	public void TC_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Thao tác vs 1 Element
		
		// Nếu chỉ tương tác lên element 1 lần thì ko cần khai báo biến
	//	driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automationfc.vn@gmail.com");
	//	Thread.sleep(3000);
		
		// Nếu element này đc thao tác/sử dụng nhiều lần thì nên khai báo biến
	//	WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
	//	emailTextbox.clear();
	//	Thread.sleep(3000);
	//	emailTextbox.sendKeys("automationfc.vn@gmail.com");
	//	Thread.sleep(3000);
	//	Assert.assertTrue(emailTextbox.isDisplayed());
		
		// Thao tác vs >=2 Element
		// Tương tác lên all links ở page hiện tại
	//	List<WebElement> links = driver.findElements(By.xpath("//a"));
		
		// Có bao nhiêu link ở page hiện tại
	//	System.out.println("Link size="+ links.size());
		
		// Get ra all text của link
	//	for (WebElement link : links) {
	//		System.out.println("Text = "+ link.getText());
	//	}		
		
		//WebElement element = driver.findElement(By.xpath(""));
		// WEb Element Method (API)
		// Xóa dữ liệu trong Textbox/ text area trước khi sendkeys (Đảm bảo dữ liệu toàn vẹn)
		//element.clear();
		//Nhập dữ liệu vào Textbox/ text area //dropdown list (editable)
		//element.sendKeys("");
		
		// Click vào link Textbox/ text area //dropdown list (editable)
		//element.click();
		
		WebElement passwordTextbox = driver.findElement(By.id("password"));
		String passwordTextboxHint = passwordTextbox.getAttribute("placeholder");
		System.out.println(passwordTextboxHint);
		
		// GUI: Graphic User Interface: font/size/ color/ location/ postion/...
		WebElement buttonDisabled = driver.findElement(By.id("button-disabled"));
		String buttonBackground = buttonDisabled.getCssValue("background-color");
		String buttonFontSize = buttonDisabled.getCssValue("font-size");
		System.out.println(buttonBackground);
		System.out.println(buttonFontSize);
		
		// Lấy ra vị trí của nó so vs độ phân giải màn hình
		//buttonDisabled.getLocation();
		
		//buttonDisabled.getSize();
		
	
		String buttonTagname = buttonDisabled.getTagName();
		System.out.println(buttonTagname);
		
		System.out.println(driver.findElement(By.tagName("h1")).getText());
		
		// Kiểm tra cho bất kì 1 element nào được hiển thị ở trên page hay ko
		// displayed/ visible (User: có thể nhìn thấy và thao tác được)
		
		WebElement userAvatar5 = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
		
		Assert.assertFalse(userAvatar5.isDisplayed());
		
		// Kiểm tra cho element có được phép thao tác hay ko (nó có bị disable)
		Assert.assertFalse(buttonDisabled.isEnabled());
			
		// Kiểm tra cho 1 element đã được chọn hay chưa (radio/ checkbox)
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		under18Radio.click();
		Assert.assertTrue(under18Radio.isSelected());
		
		// Work cho cái element là form
		userAvatar5.submit();
		
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