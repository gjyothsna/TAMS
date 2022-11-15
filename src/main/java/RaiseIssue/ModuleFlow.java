package RaiseIssue;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ModuleFlow {
	public WebDriver driver;
	reusable re;
	Properties p;
	JavascriptExecutor js;

	// Login Into the Application
	@Test(priority = 1)
	public void SignIn() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		p = new Properties();
		re = new reusable(driver);
		File file = new File("E:\\Selenium\\TAMS\\Config\\DataFile.properties");
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		driver.get(p.getProperty("url"));
		Thread.sleep(1000);
		re.Valid_Cred(p.getProperty("username"), p.getProperty("password"));
	}

	// Clicking the Raise a New Case and Entering all the information
	@Test(priority = 2)
	public void RaiseNewCase() throws InterruptedException {
		re = new reusable(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		re.NewCase_btn.click();
		Thread.sleep(1000);
		re.AppName_ddl.click();
		Thread.sleep(1000);
		re.AppName_ddl.sendKeys(p.getProperty("ApplicationName"), Keys.ENTER);
		Thread.sleep(1000);
		re.TitleName.sendKeys(p.getProperty("Title"));
		Thread.sleep(1000);
		re.Category.click();
		Thread.sleep(1000);
		re.Category.sendKeys(p.getProperty("Category"), Keys.ENTER);
		Thread.sleep(1000);
		re.Desc_txt.sendKeys(p.getProperty("Description"));
		Thread.sleep(1000);
		re.priority_ddl.click();
		Thread.sleep(1000);
		re.priority_ddl.sendKeys(p.getProperty("priority"), Keys.ENTER);
		Thread.sleep(1000);
	}

	// Entering Reference Data
	@Test(priority = 3)
	public void ReferenceData() throws InterruptedException, AWTException, IOException {
		js = (JavascriptExecutor) driver;
		re = new reusable(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		re.UploadFile();
		re.StepsReproduce_txt.sendKeys(p.getProperty("StepToReproduce"));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		re.RaisedFor_txt.sendKeys(p.getProperty("RaisedFor"));
		Thread.sleep(5000);
		re.RaisedFor_txt.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,350)", "");
		re.submit_btn.click();
		Thread.sleep(2000);
		re.HomePage_btn.click();
		Thread.sleep(2000);
	}

	// Searching Case number
	@Test(priority = 4)
	public void SearchingCase() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		re = new reusable(driver);
		String text = re.FirstRowData.getText();
		re.Searchtxt.sendKeys(text, Keys.ENTER);
		Thread.sleep(3000);
		re.Action_btn.click();
		Thread.sleep(6000);
	}

	// Assigning the Case
	@Test(priority = 5)
	public void Assign() throws InterruptedException {
		re = new reusable(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		re.Assignee_txt.sendKeys(p.getProperty("AssigneeName"));
		Thread.sleep(6000);
		re.Assignee_txt.sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		re.submit_btn.click();
		Thread.sleep(6000);
	}

	// Analysis
	@Test(priority = 6)
	public void Analysis() throws InterruptedException {
		re = new reusable(driver);
		Thread.sleep(2000);
		re.Status_btn.click();
		Thread.sleep(5000);
		re.yes_btn.click();
		Thread.sleep(5000);
		re.submit_btn.click();
		Thread.sleep(2000);
		re.Refresh_btn.click();
	}

	// Working On Case
	@Test(priority = 7)
	public void WorkingOnCase() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		re = new reusable(driver);
		Thread.sleep(3000);
		re.Status_btn.click();
		Thread.sleep(5000);
		// selecting KB document
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
		re.KbRow_btn.click();
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(1000);
		re.AddNewKbDoc.click();
		Thread.sleep(1000);
		re.submit_btn.click();
		Thread.sleep(6000);
		
	}

	// User Approval   
	@Test(priority = 8)
	public void UserApproval() throws InterruptedException {
		re = new reusable(driver);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		js.executeScript("window.scrollBy(0,-500)", "");
		re.Refresh_btn.click();
		Thread.sleep(2000);
		re.Status_btn.click();
		Thread.sleep(5000);
		re.yes_btn.click();
		Thread.sleep(1000);
		re.submit_btn.click();
	}
	
	

}
