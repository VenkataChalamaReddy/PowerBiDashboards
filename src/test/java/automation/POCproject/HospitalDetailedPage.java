package automation.POCproject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HospitalDetailedPage extends Utilities {

	
		@Test

		public void basePageNavigation() throws IOException, InterruptedException {
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
					"HOSPITAL");
			Thread.sleep(2000);
			driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
			Actions actn = new Actions(driver);
			List<WebElement> metricsList = driver.findElements(By.xpath(
					"(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));
			//int i=0;
			for (WebElement item : metricsList) {
				//System.out.println(item.getText());
				//i++;
			String metricEle = item.getText();
				if (metricEle.equals("Material Revenue")) {
					actn.moveToElement(item).click().build().perform();
					break;
				}
			}
			driver.switchTo().defaultContent();

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")));
			String title = driver.findElement(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']"))
					.getText();
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(title, "Material Revenue");
			clickOnTabItemsWithActions(
					"(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]",
					"DETAILED");
			Thread.sleep(30000);
			boolean text = driver.findElement(By.xpath("//h3[contains(text(),'Material Revenue')]")).isDisplayed();
			System.out.println(text);
			System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Ahmedabad","2020-01(Jan) "));
			System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Bangalore-CNH","2020-02(Feb)"));
			System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Bangalore-HSR","2020-03(Mar)"));
			System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Bangalore-International Wing","2020-04(Apr) "));
			System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Bangalore-MSMC","2020-05(May)"));
			System.out.println(getRevenueFromSummaryTableForHospitalBasedOnColumn("Bangalore-NICS","2020-06(Jun)"));
			
			softAssert.assertAll();
			driver.quit();
			
		}
	}

