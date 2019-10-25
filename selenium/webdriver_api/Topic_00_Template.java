package webdriver_api;

import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;



public class Topic_00_Template {

	

	WebDriver driver;



	// Pre-Condition

	@BeforeClass

	public void beforeClass() {
		
		driver = new FirefoxDriver();
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
	
		driver.manage().window().maximize();	
		
		driver.get("");
	}

	@Test
	public void TC_01() {
		driver.get("");
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

		driver.quit();
	}



}