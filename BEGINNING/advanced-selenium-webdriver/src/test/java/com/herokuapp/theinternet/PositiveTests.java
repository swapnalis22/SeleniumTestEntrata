package com.herokuapp.theinternet;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.ApplyCareerPage;
import Pages.PropertyManagerlogin;
import Pages.ScheduleDemoPage;


public class PositiveTests {
	WebDriver driver;
	PropertyManagerlogin	objManagerLogin;
	ScheduleDemoPage	objScheduleDemo;
	ApplyCareerPage	objCareerPage;
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
	System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
	// Create driver
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	

	// open main page
	String url = "https://www.entrata.com/";
	driver.get(url);
	System.out.println("Main page is opened.");
	
	driver.findElement(By.xpath("//button[@id='rcc-decline-button']")).click();
	}
	
	@Test(priority=1)
	public void Homepage() {
		
		objManagerLogin = new PropertyManagerlogin(driver);
		objManagerLogin.VerifyLoginPage();
		System.out.println("Login Page verification done.");
	}
	
	@Test(priority=2)
	public void ScheduleDemoPage() throws InterruptedException
	{
		objScheduleDemo = new ScheduleDemoPage(driver);
		objScheduleDemo.ScheduleDemo();
		System.out.println("Schedule Demo Page verification done.");
	}
	
	@Test(priority=3)
	
	public void CareerPage() throws InterruptedException
	{
		objCareerPage = new ApplyCareerPage(driver);
		objCareerPage.ApplyForCareer();
		
		System.out.println("Apply Career Page verification done.");
	}
	
	
	@AfterMethod
	public void teardown()
	{
		// Close browser
		System.out.println("browser closed.");
				driver.quit();
	}
}
