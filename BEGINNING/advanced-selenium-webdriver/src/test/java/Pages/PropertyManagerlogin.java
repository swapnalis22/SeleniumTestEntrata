package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PropertyManagerlogin {
	
	WebDriver driver;
	public PropertyManagerlogin(WebDriver driver)
	{
		this.driver = driver;
	}

	public void VerifyLoginPage()
	{
		//click on Sign In button
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.findElement(By.xpath("(//a[text()='Sign In'])[1]")).click();
		System.out.println("sign is done");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Property Manager Login']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(162,543)");
		driver.findElement(By.xpath("//a[text()='Property Manager Login']")).click();
		System.out.println("property manager login is done");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='entrata-username']")));
		
		driver.findElement(By.xpath("//input[@id='entrata-username']")).sendKeys("abc@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='entrata-password']")).sendKeys("78687");
		
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		boolean	status=driver.findElement(By.xpath("//p[@id='statusMessage']")).getText().contains("The username and password provided are not valid. Please try again");
		Assert.assertTrue(status);
		
		
	}
}
