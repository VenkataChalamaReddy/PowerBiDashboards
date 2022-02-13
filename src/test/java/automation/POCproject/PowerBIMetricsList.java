package automation.POCproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PowerBIMetricsList extends Utilities {

	@Test

	public void getMetricsList() throws IOException, InterruptedException {
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
		
		
		
		
		
		
		

	}

	}
}

