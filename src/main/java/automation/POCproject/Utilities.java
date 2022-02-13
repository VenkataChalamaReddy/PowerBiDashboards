package automation.POCproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Utilities extends base {
	
	public void clickOnTabItemsWithActions(String identifier, String tabText) {
		Actions actn = new Actions(driver);
		List<WebElement> elements= driver.findElements(By.xpath(identifier));
		for(WebElement tabEle : elements)
		{
			String tabNam = tabEle.getText();
			if(tabNam.equals(tabText)) {
				actn.moveToElement(tabEle).click().build().perform();
			}
		}
	}
	
	public int gettingColumnIndexLocation(String identifier, String expColumnHeader) {
		List<WebElement> columnHeaderList = driver.findElements(By.xpath(identifier));
//		for(WebElement Item : columnHeaderList) {
		int ColumnNum=0;
		for(int i=0;i<=columnHeaderList.size()-1;i++) {
			String ActColumnHeader = columnHeaderList.get(i).getText();
			if(ActColumnHeader.equals(expColumnHeader)) {
				ColumnNum = i;
				break;
			}
		}
		return ColumnNum;
	}

	public String getRevenueFromSummaryTableForHospitalBasedOnColumn(String dimensionName, String ColumnName){
		int columnNumber = gettingColumnIndexLocation("//div[@class='columnHeaders']/descendant::div[contains(@class,'pivotTableCellWrap')]",ColumnName);
		columnNumber++;
		int rowNumber = gettingColumnIndexLocation("//div[@class='rowHeaders']/descendant::div[contains(@class,'pivotTableCellWrap')]",dimensionName);
		rowNumber++;
		return driver.findElement(By.xpath("((//div[@class='bodyCells']/descendant::div[contains(@style,'overflow:hidden')]["+ columnNumber +"])/div)["+ rowNumber +"]")).getText();
	}
	
	

}
