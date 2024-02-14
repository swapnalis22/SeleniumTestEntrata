package Pages;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ApplyCareerPage {

	WebDriver driver;
	public ApplyCareerPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void ApplyForCareer()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Sign In'])[1]")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		//click on Careers link
		
		WebElement Careerslink = driver.findElement(By.xpath("//a[text()='Careers']"));
		
		wait.until(ExpectedConditions.elementToBeClickable(Careerslink));
		System.out.println("waited for Career Link");
	
		Actions a= new Actions(driver);
		a.moveToElement(Careerslink).perform();
		boolean isResidentpayNameHtmlElementStale = ExpectedConditions.stalenessOf(Careerslink).apply(driver);
		System.out.println("before if");
		// if the element is stale
		if (isResidentpayNameHtmlElementStale) {
		    // re-retrieving the desired input HTML element
			Careerslink = driver.findElement(By.xpath("(//a[@class='standard-footer-link'])[1]"));
		    System.out.println("inside if");
		}
		System.out.println("after if");
		
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", Careerslink);
		
		//click on Search Jobs Button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Search Jobs'])[1]")));
		driver.findElement(By.xpath("(//a[text()='Search Jobs'])[1]")).click();
		
		//Select Location as Pune
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Filter by Location: All']")));
		driver.findElement(By.xpath("//div[@aria-label='Filter by Location: All']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Pune, India']")));
		driver.findElement(By.xpath("//a[text()='Pune, India']")).click();
		
		//select work type as Full-Time
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Work type']")));
		driver.findElement(By.xpath("//div[text()='Work type']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Full-Time']")));
		driver.findElement(By.xpath("//a[text()='Full-Time']")).click();
		
		//click on first link to apply for a job
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class=\"posting-title\"])[1]//h5")));
		driver.findElement(By.xpath("(//a[@class=\"posting-title\"])[1]//h5")).click();
		
		//click on Apply For This Job button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href=\"https://jobs.lever.co/entrata/69768016-7a54-43b5-a991-38769661433d/apply\"])[2]")));
		driver.findElement(By.xpath("(//a[@href=\"https://jobs.lever.co/entrata/69768016-7a54-43b5-a991-38769661433d/apply\"])[2]")).click();
		System.out.println("before resume btn click done");
		WebElement addFile = driver.findElement(By.xpath("//input[@id='resume-upload-input']"));
		// Mention the own path of the file location
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", addFile);
		//addFile.click();
		// Add file method 
//		System.out.println("resume btn click done");
//		File filepath=new File("\ntwinelogin.jpg");
		
		//addFile.sendKeys("\ntwinelogin.jpg");
		//addFile.sendKeys("ResourceFiles//AishwaryaResume.pdf");// For setting a profile picture
		
		File file = new File("src/test/resources/resourcefiles/AishwaryaResume.pdf");
		addFile.sendKeys(file.getAbsolutePath());
		
		//addFile.sendKeys("C:\\Users\\sshinde07\\Downloads\\advanced-selenium-webdriver-master\\advanced-selenium-webdriver-master\\BEGINNING\\advanced-selenium-webdriver\\ResourceFiles\\Aishwarya Jadhav- Resume.pdf\"");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='resume-upload-label'])[last()]")));
		boolean ResumeUploadStatus=driver.findElement(By.xpath("(//div[@class='resume-upload-label'])[last()]")).getText().contains("Success!");
		Assert.assertTrue(ResumeUploadStatus);
	}
}
