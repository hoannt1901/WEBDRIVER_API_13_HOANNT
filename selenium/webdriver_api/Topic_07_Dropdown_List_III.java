package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_07_Dropdown_List_III {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	WebDriverWait waitExplicit;
	Select select;
	By numberAllItems = By.xpath("//ul[@id='number-menu']/li");

	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();
		
		waitExplicit =new WebDriverWait(driver,10);
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01_Jquery() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		driver.findElement(By.xpath("//span[@id='number-button']")).click();
		
		List<WebElement> allItems = driver.findElements(numberAllItems);
		
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(numberAllItems));
		
		for (WebElement item: allItems) {
			System.out.println(item.getText());
			
			if(item.getText().equals("5")) {
				item.click();
				break;
			}
		}
		
		//Thread.sleep(3000);
		
		boolean status =driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed();
		System.out.println("Status = "+status);
		Assert.assertTrue(status);
	}
	public boolean isElementDisplayed(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if(element.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void selectItemInCustomDropDown(String parentXpath,String allItemsXpath,String expectedText) {
		driver.findElement(By.xpath(parentXpath)).click();
		List<WebElement> allItems= driver.findElements(By.xpath(allItemsXpath));
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
		
		for (WebElement item: allItems ) {
			System.out.println("Text = "+ item.getText());
			
			if(item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}
	@Test
	public void TC_02() throws InterruptedException {	
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropDown("//span[@id='number-button']","//ul[@id='number-menu']/li","5");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));
		Thread.sleep(3000);
		
		selectItemInCustomDropDown("//span[@id='number-button']","//ul[@id='number-menu']/li","3");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='3']"));
		Thread.sleep(3000);
		
		selectItemInCustomDropDown("//span[@id='number-button']","//ul[@id='number-menu']/li","14");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='14']"));
		Thread.sleep(3000);
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