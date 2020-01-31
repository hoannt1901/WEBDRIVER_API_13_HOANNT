package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


public class Topic_09_User_Interaction {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	Actions action;


	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(profile);
		
		
		action = new Actions(driver);
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		// Phóng to trình duyệt
		driver.manage().window().maximize();	
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

//	@Test
	public void TC_01_Hover_Move_Mouse() {
		driver.get("https://www.myntra.com");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Men']"))).perform();
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Boxers']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("h1[@class='title-title' and text()='Boxers For Men']")).isDisplayed());
		
	}
	@Test
	public void TC_02_Click_And_Hold() {	
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
	 	List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	 	int numberSize = numbers.size();
	 	System.out.println("Size before click/ hold = "+ numberSize );
	 	action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();
	 	
	 	List<WebElement> SelectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
	 	System.out.println("Size affter click/ hold = "+ SelectedNumber.size() );
	 	
	 	for (WebElement number : SelectedNumber ) {
	 		System.out.println(number.getText());
	 	}
		
		Assert.assertEquals(SelectedNumber.size(),8);
	}
	@Test

	public void TC_03_Click_And_Hold_Random() {
driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
	 	List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	 	int numberSize = numbers.size();
	 	System.out.println("Size before click/ hold = "+ numberSize );
	 	
	 	action.keyDown(numbers.get(0),Keys.CONTROL).perform();
	 	
	 	action.click(numbers.get(0))
	 	.click(numbers.get(2))
	 	.click(numbers.get(4))
	 	.click(numbers.get(6))
	 	.click(numbers.get(9)).perform();
	 	
	 	List<WebElement> SelectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
	 	System.out.println("Size affter click/ hold = "+ SelectedNumber.size() );
	 	
	 	for (WebElement number : SelectedNumber ) {
	 		System.out.println(number.getText());
	 	}
		
		Assert.assertEquals(SelectedNumber.size(),5);
	}
	
	@Test

	public void TC_04_Double_Click() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Thread.sleep(3000);
		
		String demoText = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		
		Assert.assertEquals(demoText, "Hello Automation Guys!");
	}
	
	@Test

	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(findByXpath("//span[text()='right click me']")).perform();
		action.moveToElement(findByXpath("//span[text()='Quit']")).perform();
		
		Assert.assertTrue(isElementDisplayed("//li/span[text()='Quit']"));
		
		action.click(findByXpath("//span[text()='Quit']")).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
		
	}
	
	@Test

	public void TC_06_Drag_And_Drop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle =  findByXpath("//div[@id='draggable']");
		WebElement targetCircle =  findByXpath("//div[@id='droptarget']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		Assert.assertTrue(isElementDisplayed("//div[@id='droptarget' and text()='You did great!']"));
	}
	@Test

	public void TC_07_Drag_And_Drop_HTML5() throws InterruptedException {
	//	driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		
		//WebElement sourceCircle = findByXpath("//div[@id='column-a']");
		//WebElement targetCircle = findByXpath("//div[@id='column-b']");
		
		//action.dragAndDrop(sourceCircle, targetCircle).perform();
		//Thread.sleep(3000);
		
		
	}
	
	public WebElement findByXpath(String xpathLacator) {
		return driver.findElement(By.xpath(xpathLacator));		
	}
	
	public boolean isElementDisplayed(String xpathLacator ) {
		return findByXpath(xpathLacator).isDisplayed();
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}



}