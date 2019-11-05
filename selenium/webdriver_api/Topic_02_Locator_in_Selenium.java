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


public class Topic_02_Locator_in_Selenium {

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
	public void TC_01_locator() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		// <input
		// type= "email"
		// class="input-text requied -entry validate-email"
		// id = "email"
		// value=""
		// name="login[username]"
		// spellcheck="false"
		// autocorrect="off"
		// autocapitalize="off">
		
		// tagname[attribute='value']
		// input[id='email']
		
		// thẻ của nó là gì?
		// thuộc tính của nó có dc selenium support hay không?
		// cái giá trị của thuộc tính đó có duy nhất trong page hay ko ?
		
		
		// ID | Classname | name | Linktext |partial linktext | Tagname |Xpath | CSS selector
		
		// Thao tác với lại field email address
		
		driver.findElement(By.id("email") );
		driver.findElement(By.id("email") ).clear();
		driver.findElement(By.id("email") ).sendKeys("autotest@gmail.com");
		
		driver.findElement(By.name("login[username]")).sendKeys("autotest@gmail.com");
		
		driver.findElement(By.className("input-text requied -entry validate-email")).sendKeys("");
		
		// ID
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		// Name
		driver.findElement(By.id("send")).click();
		
		// Class (Newsletter)
		driver.findElement(By.className("validate-email")).sendKeys("classname@email.com");
		
		// Tagname ( tim xem có bao nhiêu đường link ở page và in ra)
		// Đếm (count) bao nhiêu element ở trên page
		List <WebElement> links= driver.findElements(By.tagName("a"));	
		
		int linkNumber =links.size();
		System.out.println("Tong so link="+linkNumber);
		for (WebElement link:links) {
			System.out.println("Value="+link.getAttribute("hrft"));
			
		// Link Text (link)
			driver.findElement(By.linkText("Orders and Returns")).click();
			
		// Partial Link text (link)
			driver.findElement(By.partialLinkText("Orders and Returns")).click();
			
			driver.findElement(By.partialLinkText("Orders and")).click();
			
			driver.findElement(By.partialLinkText("and Returns")).click();
			
			driver.findElement(By.partialLinkText("Orders")).click();
			
		// CSS
		driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("");
		driver.findElement(By.cssSelector(".validate-email")).sendKeys("");
		driver.findElement(By.cssSelector("input[name=[login[username]']")).sendKeys("");
		System.out.println("The a dung css="+ driver.findElements(By.cssSelector("a")).size());	
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/customer/sales/guest/form/'")).click();
		// XPATH
		driver.findElement(By.xpath("//input[id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@name=login[username]']")).sendKeys("");
		driver.findElement(By.xpath("//input[@class='input-text requied -entry validate-email']")).sendKeys("");
		System.out.println("The a dung css="+ driver.findElements(By.xpath("a")).size());	
		driver.findElement(By.xpath("a[text()='Advanced Search'")).click();	
			
			
			
		}
		
		
	}
	@Test
	public void TC_02_DuplicateValua() throws InterruptedException{	
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id]")).sendKeys("duplicate@gmail.com");
		Thread.sleep(5000);
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