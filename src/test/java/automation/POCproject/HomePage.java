package automation.POCproject;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class HomePage extends Utilities {
	
	
	@Test
	
	public void homePage() throws IOException, InterruptedException
	{
		//Driver Initialization and navigating to url
		driver=initializeDriver();
		driver.get("https://app.powerbi.com/home?noSignUpCheck=1");
		
		// Login to Power BI account
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("360131@narayanahealth.org");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Password123");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.xpath("//input[@value='Yes']")).click();
		
		// Clicking on Left Bar Items after login to user account
		if(driver.findElement(By.xpath("//button[@class='paneExpanderButton expanderBtn']")).getAttribute("aria-expanded").equalsIgnoreCase("false")) {
			driver.findElement(By.xpath("//button[@class='paneExpanderButton expanderBtn']")).click();
		}
		driver.findElement(By.xpath("//span[text()='Apps']")).click();
		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(text(),'A1 Revenue Cycle')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Revenue & Volume Analysis']")).click();
		Thread.sleep(5000);
		
		
		// **** Below code for reference to click on tab items with actions class******
		
//		Actions actn = new Actions(driver);
////		driver.findElement(By.xpath("//span[text()='Revenue']")).click();
//		Thread.sleep(5000);
//		List<WebElement> lis = driver.findElements(By.xpath("(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]"));
//		for(WebElement tabText : lis) {
//			String tabName = tabText.getText();
//			if(tabName.equalsIgnoreCase("hospital")) {
//				actn.moveToElement(tabText).click().build().perform();
				
//				break;
//			}
//			
//		}
		
		//**************************
		
		// Clicking on Tab Items using re-usable method
		clickOnTabItemsWithActions("(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]", "HOSPITAL");
		
		
		//Switching to Frame and clicking on Metrics items
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[2]")));
//		driver.findElement(By.xpath("//span[text()='IP Admissions']")).click();
		Actions actn = new Actions(driver);
		List<WebElement> metricsList = driver.findElements(By.xpath("(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));
		//System.out.println(metricsList.size());   //get the count of metricsList
		
		
		for(WebElement item : metricsList) {
			String metricEle = item.getText();
			if(metricEle.equals("Daycare Admissions")) {
				actn.moveToElement(item).click().build().perform();
				break;
			}
		}
		
//		for(int i=1;i<=metricsList.size();i++) {
//			String metricEle = metricsList.get(i).getText();
//			if(metricEle.equals("Daycare Admissions")) {
//				metricsList.get(i).click();
//				break;
//			}
//		}
		
		// Switching back to main driver
		driver.switchTo().defaultContent();
		
		// Expected condition using webdriverwait class until the Metrics item text to be displayed under Trends section.
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")));
		
		//Getting and Comparing the Metrics text is displayed as expected with SoftAssersion.
		String title=driver.findElement(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")).getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(title, "Daycare Admissions");
		
		// Clicking on summary tab
		clickOnTabItemsWithActions("(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]", "SUMMARY");
		Thread.sleep(30000);
		//getting the revenue data of hospital related to the summary section
		System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Davangere-SSNH","YTD\n "));
		System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Dharwad-SDM","3M Avg"));
		System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Guwahati","MTD_Revenue"));
		//clearing all drivers opened
		//driver.quit();
		
		//printing all the assertions captured.
		softAssert.assertAll();
		
			
	}

	
}
