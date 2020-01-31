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


public class Topic_14_Wait_Part2_FindElement {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;

List<WebElement> elements;

	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_Find_Element() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Case 01 - Không tìm thấy element nào hết
		// driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("123456");
		// Nếu như đang còn tìm element mà chưa hết timeout - element nó xuất hiện thì vẫn Pass
		// Luôn tìm element theo chu kỳ 0.5s tìm lại 1 lần cho đến hết timeout của implicit
		// Kết quả: Failed và throw ra exception : No such element
		
		// Case 02 - Tìm thấy duy nhất 1 element (node)
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("email@gmail.com");
		
		//Case 03 - Tìm thấy nhiều hơn 1 element (>=2) => Luôn thao tác với element đầu tiên
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	}
	@Test
	public void TC_02() {	
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Case 01 - Không tìm thấy element nào hết				
		
		elements =  driver.findElements(By.xpath("//input[@id='id_order']"));
		// Nếu như đang còn tìm element mà chưa hết timeout - element nó xuất hiện thì vẫn Pass
		// Luôn tìm element theo chu kỳ 0.5s tìm lại 1 lần cho đến hết timeout của implicit
		// kết quả: Không đánh fail testcase và trả về không có phần tử nào hết
		System.out.println("Size of list ="+ elements.size());
		Assert.assertTrue(elements.isEmpty());
		Assert.assertEquals(elements.size(),0);
		
		// Case 02 - Tìm thấy duy nhất 1 element (node)
		
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Size of list ="+ elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(),1);
		elements.get(0).sendKeys("email@gamil.com");
		
		//Case 03 - Tìm thấy nhiều hơn 1 element (>=2) => Luôn thao tác với element đầu tiên
		
		elements = driver.findElements(By.xpath("//button[@type='submit']"));
		System.out.println("Size of list ="+ elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(),4);
		
		
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