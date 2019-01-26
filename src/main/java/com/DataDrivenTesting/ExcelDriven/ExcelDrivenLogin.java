package com.DataDrivenTesting.ExcelDriven;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.poi.*;
import com.DataDrivenTesting.ExcelDriven.ReadExcelFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDrivenLogin {
	String url="http://newtours.demoaut.com/";
	public WebDriver driver;

	@BeforeMethod
	    public static void setupClass() {
	        WebDriverManager.chromedriver().setup();
	    }

	@BeforeMethod
	    public void setupTest() {
	        driver = new ChromeDriver();
	    }

	
	@Test(dataProvider="testdata")
	 public void Project(String username, String password) throws InterruptedException
	 {
	  driver.get(url);
	  driver.findElement(By.name("userName")).sendKeys(username);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.name("login")).click();
	  
	  Thread.sleep(5000);
	  
	  Assert.assertTrue(driver.getTitle().matches("Find a Flight: Mercury Tours:"), "Invalid credentials");
	  System.out.println("Login successful");
	  
	 } 
	
	@AfterMethod
	 void ProgramTermination()
	 {
	 driver.quit();
	 }
	
	@DataProvider(name="testdata")
	 public Object[][] TestDataFeed()
	 {
	 ReadExcelFile config = new ReadExcelFile("C:/Users/aria/Desktop/testinpuy.xlsx");
	  
	 int rows = config.getRowCount(0);
	  
	 Object[][] credentials = new Object[rows][2];
	 
	for(int i=0;i<rows;i++)
	 {
	 credentials[i][0] = config.getData(0, i, 0);
	 credentials[i][1] = config.getData(0, i, 1);
	 }
	  
	 return credentials;
	 }
	 
	
}
