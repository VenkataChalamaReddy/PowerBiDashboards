package automation.POCproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FlowOfImplantConsumption extends Utilities {
	@Test(priority = 1)

	public void Login() throws IOException, InterruptedException {
		// Driver Initialization and navigating to url
		driver = initializeDriver();
		driver.get("https://app.powerbi.com/home?noSignUpCheck=1");

		// Login to Power BI account
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("360131@narayanahealth.org");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Password123");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.xpath("//input[@value='Yes']")).click();
	}

	@Test(priority = 2)
	public void OpeningDashboard() throws IOException, InterruptedException {

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

	}

	@Test(priority = 3)
	public void getMetricsList() throws IOException, InterruptedException {

		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]",
				"HOSPITAL");
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
		Actions actn = new Actions(driver);
		List<WebElement> metricsList = driver.findElements(By.xpath(
				"(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));
		int i = 0;
		for (WebElement item : metricsList) {
			System.out.println(item.getText());
			i++;

//		 List<String> all_elements_text=new ArrayList<>();
//		 for(int i=0; i<metricsList.size(); i++){
//			 all_elements_text.add(metricsList.get(i).getText());
//			 System.out.println(metricsList.get(i).getText());
//			 
//		 }
//		ArrayList<String> compareMetrics = new ArrayList<String> (Arrays.asList("Material Revenue","Consumed Quantity", "Patient Consumption","Patient Consumption %"));
//		System.out.println(metricsList.equals(compareMetrics));
//		
//		

		}

	}

	@Test(priority = 4)
	public void clickOnMetricsList() throws IOException, InterruptedException {

		// Thread.sleep(2000);
		// driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
		Actions actn = new Actions(driver);
		List<WebElement> metricsList = driver.findElements(By.xpath(
				"(//title[contains(text(),'Static Visual Sandbox Host')]/ancestor::html)/descendant::div[@class=\"cell\"]"));
		for (WebElement item : metricsList) {
			String metricEle = item.getText();
			if (metricEle.equals("Consumed Quantity")) {
				actn.moveToElement(item).click().build().perform();

			} else {
				System.out.println("The Expected Metric is not Selected");
			}
			break;
		}
		driver.switchTo().defaultContent();

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")));
		String title = driver.findElement(By.xpath("//h3[@class='preTextWithEllipsis ng-star-inserted']")).getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(title, "Consumed Quantity");

		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void getDimensionsList() throws IOException, InterruptedException {

		Thread.sleep(5000);
		List<WebElement> dimensions = driver.findElements(By.xpath(
				"(//transform[@class='selectThrough'])[3]//descendant::div[contains(@class,'ui-role-button-text')]"));
		int i = 0;
		for (WebElement items : dimensions) {
			System.out.println(items.getText());
			i++;
		}
	}

	@Test(priority = 6)
	public void clickOnDepartmentLevels() throws IOException, InterruptedException {
		Thread.sleep(5000);
		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]",
				"DEPARTMENT");

		// clicking on department levels
		Thread.sleep(500);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[1]")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Department L3')]")).click();
		String title = driver.findElement(By.xpath("//span[contains(text(),'Department L3')]")).getText();
		System.out.println(title);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(title, "Department L3");
		driver.switchTo().defaultContent();
		softAssert.assertAll();

	}

}
