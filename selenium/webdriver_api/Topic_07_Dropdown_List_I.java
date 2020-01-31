package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_07_Dropdown_List_I {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	Select select;
	
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
		driver.get("https://egov.danang.gov.vn/reg");
		
		// Thao tác vs City dropdown
		select = new Select(driver.findElement(By.name("tinhThuongTru")));
		
		// Kiểm tra dropdown nafy ko được phép chọn nhiều
		boolean cityDropdownStatus = select.isMultiple();
		System.out.println("City status = " + cityDropdownStatus);
		Assert.assertFalse(cityDropdownStatus);
		
		// Chọn TP Hồ Chí Minh
		select.selectByIndex(4);
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");
		
		// Chọn Cà Mau
		select.selectByValue("11803");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Cà Mau");
		
		// Chọn Lạng Sơn
		select.selectByVisibleText("tỉnh Lạng Sơn");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Lạng Sơn");
		
		// Làm sao để biết trong dropdown này có bao nhiêu item
		int cityNumber = select.getOptions().size();
		System.out.println("All tỉnh thành = " + cityNumber);
		Assert.assertEquals(cityNumber, 65);
		
		// In ra tất cả giá trị nằm trong dropdown - Xem tất cả các giá trị trong Dropdown này có Sort đúng hay ko
		// Sort ASC/ DESC
		List<WebElement> allOptions = select.getOptions();
		List<String> arrayList = new ArrayList<String>();
		
		for (WebElement option:allOptions ) {
			arrayList.add(option.getText());
		}
		
		for (String text:arrayList ) {
			System.out.println(text);
		}
		
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