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

public class DepartmentDimension extends Utilities {
	
	@Test

	public void department() throws IOException, InterruptedException {
		// Driver Initialization and navigating to url
		driver = initializeDriver();
		driver.get("https://app.powerbi.com/home?noSignUpCheck=1");

		// Login to Power BI account
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("360131@narayanahealth.org");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Password123");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.xpath("//input[@value='Yes']")).click();

		// Clicking on Left Bar Items after login to user account
		if (driver.findElement(By.xpath("//button[@class='paneExpanderButton expanderBtn']"))
				.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
			driver.findElement(By.xpath("//button[@class='paneExpanderButton expanderBtn']")).click();
		}
		driver.findElement(By.xpath("//span[text()='Apps']")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(text(),'A1 Revenue Cycle')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Revenue & Volume Analysis']")).click();
		Thread.sleep(5000);

		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]",
				"DEPARTMENT");
		
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
		
		Actions actn = new Actions(driver);
//		driver.findElements(By.xpath("((//div[@id='sandbox-host'])/descendant::li)");
		clickOnTabItemsWithActions("((//div[@id='sandbox-host'])/descendant::li)","Department L2");
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[2]")));


		List<WebElement> metricsList = driver.findElements(By.xpath(
				"(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));


		for (WebElement item : metricsList) {
			String metricEle = item.getText();
			if (metricEle.equals("Daycare Admissions")) {
				actn.moveToElement(item).click().build().perform();
				break;
			}
		}

		driver.switchTo().defaultContent();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")));

	
		String title = driver.findElement(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")).getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(title, "Daycare Admissions");

		
		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]",
				"SUMMARY");
		Thread.sleep(30000);
		// getting the revenue data of hospital related to the summary section
		System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Cardiac Sciences", "YTD"));
		System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Oncology", "3M Avg"));
		System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Renal Sciences", "MTD_Revenue\n "));
		// clearing all drivers opened
		//driver.quit();

		// printing all the assertions captured.
		softAssert.assertAll();

	}

}
