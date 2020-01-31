package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Topic_12_Javascript_Executor {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver

	WebDriver driver;
	JavascriptExecutor jsExecutor;

	WebElement element;

	String username = "mngr243737";
    String password = "vugerAb";
    
	String customername = "Jason Staham";
	String gender = "male";
	String dateOfBirth = "1983-06-06";
	String address = "255 PA Hamlet";
	String city = "Hawaii";
	String state = "NewYork";
	String pin = "999777";
	String phone = "0988555777";
	String email = "jsstaham" + randomNumber() + "@hotmail.com";

	  // Locator for New/ edit customer form
    By nameTextbox = By.name("name");
    By genderRadio = By.xpath("//input[@value='m']");
    By genderTextbox = By.name("gender");
    By dobTextbox=By.name("dob");
    By addressTextArea = By.name("addr");
    By cityTextbox = By.name("city");
    By StateTextbox = By.name("state");
    By pinTextbox = By.name("pinno");
    By phoneTextbox = By.name("telephoneno");
    By emailTextbox = By.name("emailid");
    By passwordTextbox = By.name("password");
	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox
		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver",".//libraries//chromedriver.exe" );
		//driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("");
	}

	@Test
	public void TC_01() throws InterruptedException {
		navigateToUrlByJS("http://live.demoguru99.com/");

		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");

		String liveUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveUrl, "http://live.demoguru99.com/");

		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		System.out.println("denday chua");
		String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));

		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		String customerServiceTitle = (String) executeForBrowser("return document.title;");

		Assert.assertEquals(customerServiceTitle, "Customer Service");

		scrollToElement("//input[@id='newsletter']");
		Thread.sleep(3000);

		pageInnerText = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String demoDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomain, "demo.guru99.com");
	}
	@Test
	public void TC_02_RemoveAttribute() throws InterruptedException {
		
	driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		String homepageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homepageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : "+ username +"']")).isDisplayed());
		
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// input data to New Customer form
		driver.findElement(nameTextbox).sendKeys(customername);
		driver.findElement(genderRadio).click();
		
		//Remove Attribute type='date' (Date or Birth)
		removeAttributeInDOM("//input[@id='dob']","type");
		
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);
		
		
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(StateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3'and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		// verify output data = input data\
		Assert.assertEquals(customername, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
	}
	

	@Test

	public void TC_03_Create_Account() throws InterruptedException {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		
		clickToElementByJS("//span[text()='Create an Account']");
		
		sendkeyToElementByJS("//input[@id='firstname']", "Automation");
		sendkeyToElementByJS("//input[@id='lastname']", "FC");
		sendkeyToElementByJS("//input[@id='email_address']", "automationfc" + randomNumber() + "@gmail.com");
		sendkeyToElementByJS("//input[@id='password']", "123456");
		sendkeyToElementByJS("//input[@id='confirmation']", "123456");
		
		clickToElementByJS("//button[@title='Register']");
		
		Thread.sleep(10000);
		String innerText = (String)executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(innerText.contains("Thank you for"));
		
		clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
		
		
	}
	// Browser

	public Object executeForBrowser(String javaSript) {

		return jsExecutor.executeScript(javaSript);

	}

	public boolean verifyTextInInnerText(String textExpected) {

		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");

		System.out.println("Text actual = " + textActual);

		return textActual.equals(textExpected);

	}

	public void scrollToBottomPage() {

		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}

	public void navigateToUrlByJS(String url) {

		jsExecutor.executeScript("window.location = '" + url + "'");

	}

	// Element

	public void highlightElement(String locator) {

		element = driver.findElement(By.xpath(locator));

		String originalStyle = element.getAttribute("style");

		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");

		try {

			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);

	}

	public void clickToElementByJS(String webElement) {

		element = driver.findElement(By.xpath(webElement));

		jsExecutor.executeScript("arguments[0].click();", element);

	}

	public void scrollToElement(String locator) {

		element = driver.findElement(By.xpath(locator));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void sendkeyToElementByJS(String locator, String value) {

		element = driver.findElement(By.xpath(locator));

		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);

	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {

		element = driver.findElement(By.xpath(locator));

		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);

	}

	public int randomNumber() {
		Random rand= new Random();
		return rand.nextInt(100000);
	}
	// Post-Condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt đi
		driver.quit();
	}

}