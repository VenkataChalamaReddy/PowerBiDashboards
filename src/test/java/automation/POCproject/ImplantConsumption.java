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

public class ImplantConsumption extends Utilities {
	@Test

	public void OpeningDashboard() throws IOException, InterruptedException {
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
				"OVERVIEW");
		
		WebElement bgColor=driver.findElement(By.xpath("(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')][1]"));
		String backColor= bgColor.getCssValue("color");
		System.out.println(backColor);
		String hexBackColor=Color.fromString(backColor).asHex();
		System.out.println(hexBackColor);
//
//		Thread.sleep(2000);
//		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
//		Actions actn = new Actions(driver);
//		List<WebElement> metricsList = driver.findElements(By.xpath(
//				"(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));
//		for (WebElement item : metricsList) {
//			String metricEle = item.getText();
//			if (metricEle.equals("Consumed Quantity")) {
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
//		softAssert.assertEquals(title, "Consumed Quantity");
//
//		clickOnTabItemsWithActions(
//				"(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]",
//				"SUMMARY");
//		Thread.sleep(3000);
//		String text = driver.findElement(By.xpath("//h3[contains(text(),'Material Revenue')]")).getText();
//		System.out.println(text);
//		
//		List<WebElement> hospitalNames=driver.findElements(By.xpath("//*[local-name()='svg']//*[name()='g']//*[name()='g' and @class='y axis hideLinesOnAxis setFocusRing']//*[name()='g']"));
//		System.out.println("the Count of hospitals:" + hospitalNames.size());
//
//		String verticalXpath = "//*[local-name()='svg']//*[name()='g' and @class='axisGraphicsContext columnChart']//*[name()='rect']";
//		List<WebElement> barsList = driver.findElements(By.xpath(verticalXpath));
//		System.out.println("total bars:" + barsList.size());
//	
//		//Perform mouse hover action on the bar graphs
//		Actions act = new Actions(driver);
//		for (WebElement e : barsList) {
//			act.moveToElement(e).perform();
//			Thread.sleep(500);
//			
//		}
//		
//		//Thread.sleep(2000);
//		//clickOnTabItemsWithActions(	"(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]","DETAILED");
//		
//		softAssert.assertAll();
//		driver.quit();
	}
	
}
