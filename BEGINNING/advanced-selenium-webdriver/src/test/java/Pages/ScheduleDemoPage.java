package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ScheduleDemoPage {
	
	WebDriver driver;
	public ScheduleDemoPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void ScheduleDemo() throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Sign In'])[1]")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//js.executeScript("window.scrollBy(0,250)");
		System.out.println("scrolled until bottom");
		
		//click on Resident Pay option
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='ResidentPay'])[3]")));
		WebElement ResidentPay = driver.findElement(By.xpath("(//a[text()='ResidentPay'])[3]"));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='ResidentPay'])[3]")));
		wait.until(ExpectedConditions.elementToBeClickable(ResidentPay));
		System.out.println("waited for resident pay");
	
		Actions a= new Actions(driver);
		a.moveToElement(ResidentPay).perform();
		boolean isResidentpayNameHtmlElementStale = ExpectedConditions.stalenessOf(ResidentPay).apply(driver);
		System.out.println("before if");
		// if the element is stale
		if (isResidentpayNameHtmlElementStale) {
		    // re-retrieving the desired input HTML element
		    ResidentPay = driver.findElement(By.xpath("(//a[@class='standard-footer-link'])[1]"));
		    System.out.println("inside if");
		}
		System.out.println("after if");
		
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ResidentPay);
		//ResidentPay.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Schedule Demo'])[1]")));
		driver.findElement(By.xpath("(//a[text()='Schedule Demo'])[1]")).click();
		
		//fill up Schedule Demo Form
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Aishwarya");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("aishwaryajadhav7382@gmail.com");
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Company1");
		driver.findElement(By.xpath("//input[@id='Phone']")).sendKeys("1234567890");
		WebElement UnitCountDropdown = driver.findElement(By.xpath("//select[@id=\"Unit_Count__c\"]"));
		Select s =new Select(UnitCountDropdown);
		s.selectByValue("1 - 10");
		driver.findElement(By.xpath("//input[@id='Title']")).sendKeys("QA");
		driver.findElement(By.xpath("//button[text()='Schedule Demo']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ValidMsgLastName']")));
		boolean errorstatus = driver.findElement(By.xpath("//div[@id='ValidMsgLastName']")).getText().contains("This field is required.");
		Assert.assertTrue(errorstatus);
		
	}
	
}
