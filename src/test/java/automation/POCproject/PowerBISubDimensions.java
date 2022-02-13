package automation.POCproject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PowerBISubDimensions extends Utilities {
	

	@Test

	public void clickOnDepartmentLevels() throws IOException, InterruptedException {
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
		driver.findElement(By.xpath("//a[contains(text(),'C3 Material Consumption')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Implant Consumption']")).click();
		
		Thread.sleep(5000);
		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]",
				"DEPARTMENT");

		// clicking on department levels
		Thread.sleep(500);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
		String frame = driver.findElement(By.xpath("(//iframe)[1]")).getText();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Department L2')]")).click();
		String title = driver.findElement(By.xpath("//span[contains(text(),'Department L2')]")).getText();
		System.out.println(frame+title);
//		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(title, "Department L3");
//		
		//This is hard Coding
//		driver.findElement(By.xpath("(//div[@id='sandbox-host'])/descendant::li[3]")).click();
//		String title = driver.findElement(By.xpath("(//div[@id='sandbox-host'])/descendant::li[2]")).getText();
//		//System.out.println(title);
//		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(title, "Department L3");

		driver.switchTo().defaultContent();

//		Thread.sleep(2000);
//		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[2]")));
//		Actions actn = new Actions(driver);
//		List<WebElement> metricsList = driver.findElements(By.xpath(
//				"(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));
//		for (WebElement item : metricsList) {
//			String metricEle = item.getText();
//			if (metricEle.equals("Material Revenue")) {
//				actn.moveToElement(item).click().build().perform();
//				break;
//			}
//		}
//		driver.switchTo().defaultContent();
//
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")));
//		String title = driver.findElement(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")).getText();
//		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(title, "Material Revenue");
//
//		clickOnTabItemsWithActions(
//				"(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]",
//				"SUMMARY");
//		Thread.sleep(30000);
//
//		// Verifying the title of the Dashboard-2
//		String text = driver.findElement(By.xpath("//h3[contains(text(),'Material Revenue')]")).getText();
//		System.out.println(text);
//
//		// To check the background Color upon selection
//		WebElement bgColor=driver.findElement(By.xpath("(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')][1]"));
//		String backColor= bgColor.getCssValue("background-color");
//		System.out.println(backColor);
//		String hexBackColor=Color.fromString(backColor).asHex();
//		System.out.println(hexBackColor);
//
////		clickOnTabItemsWithActions("(//transform[@class='selectThrough'])[10]/descendant::div[contains(@class,'ui-role-button-text')]","M Q Y");
////		Thread.sleep(500);
////		driver.findElement(By.xpath("//span[text()='Q']")).click();
////		
//	}
		//softAssert.assertAll();
	}

}
