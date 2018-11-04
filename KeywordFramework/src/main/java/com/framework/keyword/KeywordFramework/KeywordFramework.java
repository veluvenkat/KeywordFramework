package com.framework.keyword.KeywordFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class KeywordFramework {
	
	public static WebDriver driver;
	
	public static WebDriverWait wdWait;
	
	
	public By identifyLocator(String locatorType, String locatorValue) {
		
		By locator = null;
		
		switch(locatorType) {
		
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		
		case "id":
			locator = By.id(locatorValue);
			break;
			
		case "className":
			locator = By.className(locatorValue);
			break;
		
		case "name":
			locator = By.name(locatorValue);
			break;
			
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
			
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);	
			break;
		
		}
		
		return locator;
		
	}
	
	public WebElement identifyElement(String locatorType, String locatorValue) {
		
		WebElement webElement = null;
		
		switch(locatorType) {
		
		case "xpath":
			webElement = driver.findElement(By.xpath(locatorValue));
			break;
		
		case "id":
			webElement = driver.findElement(By.id(locatorValue));
			break;
			
		case "className":
			webElement = driver.findElement(By.className(locatorValue));
			break;
		
		case "name":
			webElement = driver.findElement(By.name(locatorValue));
			break;
			
		case "tagName":
			webElement = driver.findElement(By.tagName(locatorValue));
			break;
		
		case "cssSelector":
			webElement = driver.findElement(By.cssSelector(locatorValue));
			break;
			
		case "linkText":
			webElement = driver.findElement(By.linkText(locatorValue));
			break;
		
		case "partialLinkText":
			webElement = driver.findElement(By.partialLinkText(locatorValue));
			break;
		
		}
		
		return webElement;
		
	}	
	
	
	public void openURL(String url) {
		
		driver.get(url);
		
	}
	
	
	public void sendKeys(String locatorType, String locatorValue, String data) {
		
		WebElement webElement = identifyElement(locatorType, locatorValue);
		
		webElement.sendKeys(data);
		
	}
	
	public void click(String locatorType, String locatorValue) {
		
		WebElement webElement = identifyElement(locatorType, locatorValue);
		
		webElement.click();
		
	}	
	
	public void wdWait(String locatorType, String locatorValue) {
		
		By locator = identifyLocator(locatorType, locatorValue);
		
		wdWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}		
	
	
	public void sleep(String data) throws NumberFormatException, InterruptedException {
	
		Thread.sleep(Integer.parseInt(data)*1000);
		
	}	
	
	
	public void selectByIndex(String locatorType, String locatorValue, String data) {
		
		WebElement webElement = identifyElement(locatorType, locatorValue);
		
		Select selectObj = new Select(webElement);
		
		selectObj.selectByIndex(Integer.parseInt(locatorValue));
		
	}	
	
	public void selectByVisibleText(String locatorType, String locatorValue, String data) {
		
		WebElement webElement = identifyElement(locatorType, locatorValue);
		
		Select selectObj = new Select(webElement);
		
		selectObj.selectByVisibleText(locatorValue);
		
	}		
	

	public static void main(String[] args) throws IOException, NumberFormatException, InterruptedException {
		
		
		
		//reading the excel for keywords and data required for web page navigation
		FileInputStream inputFile = new FileInputStream(new File("C:/Users/vv297e/Desktop/Selenium/Workspace/KeywordFramework.xlsx"));
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
		
		XSSFSheet sheet = workbook.getSheet("Facebook");
		
		Integer noOfRows = sheet.getLastRowNum();	
		
		
		
		//create the browser instance
		System.setProperty("webdriver.chrome.driver", "C:/Users/vv297e/Desktop/Selenium/Webdrivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		wdWait = new WebDriverWait(driver, 20);
		
		
		//iterating through all excel rows
		for(int i=1; i<=noOfRows; i++) {
			
			String keyWord = null;
			
			String locatorType = null;
			
			String locatorValue = null;
			
			String data = null;
			
			
			XSSFCell keyWordCell = sheet.getRow(i).getCell(0, Row.RETURN_BLANK_AS_NULL);
			
			if(keyWordCell != null) {
				
				keyWord = sheet.getRow(i).getCell(0).toString();
		
			}
			
			XSSFCell locatorTypeCell = sheet.getRow(i).getCell(1, Row.RETURN_BLANK_AS_NULL);
			
			if(locatorTypeCell != null) {
				
				locatorType = sheet.getRow(i).getCell(1).toString();
		
			}
			
			XSSFCell locatorValueCell = sheet.getRow(i).getCell(2, Row.RETURN_BLANK_AS_NULL);
			
			if(locatorValueCell != null) {
				
				locatorValue = sheet.getRow(i).getCell(2).toString();
		
			}
			
			XSSFCell dataCell = sheet.getRow(i).getCell(3, Row.RETURN_BLANK_AS_NULL);
			
			if(dataCell != null) {
				
				data = sheet.getRow(i).getCell(3).toString();
		
			}		
			
			KeywordFramework keywordFramework = new KeywordFramework();
			
			switch(keyWord) {
			
			case "openURL":
				keywordFramework.openURL(data);
				break;
				
			case "sendKeys":
				keywordFramework.sendKeys(locatorType, locatorValue, data);
				break;
				
			case "click":
				keywordFramework.click(locatorType, locatorValue);
				break;
				
			case "sleep":
				keywordFramework.sleep(data);
				break;
				
			case "wdWait":
				keywordFramework.wdWait(locatorType, locatorValue);		
				break;
				
			case "selectByIndex":
				keywordFramework.selectByIndex(locatorType, locatorValue, data);				
				break;
				
			case "selectByVisibleText":
				keywordFramework.selectByVisibleText(locatorType, locatorValue, data);		
				break;
			
				
			
			}
			
		}

	}

}
