package RaiseIssue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddKBDocFlow {
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
		Thread.sleep(5000);
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
	}

	// Add new KB document
	@Test(priority = 7)
	public void AddKBDoc() throws InterruptedException, AWTException, IOException {
		Thread.sleep(5000);
		re = new reusable(driver);
		re.Status_btn.click();
		Thread.sleep(5000);
		re.KbDocbtn.click();

		// Switching to another window
		String Mw1 = driver.getWindowHandle();
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Set<String> allwin = driver.getWindowHandles();
		int count = allwin.size();
		System.out.println(count);
		Iterator<String> i2 = allwin.iterator();
		while (i2.hasNext()) {
			String childwindow2 = i2.next();
			if (!Mw1.equalsIgnoreCase(childwindow2)) {
				driver.switchTo().window(childwindow2);
				re.Symptoms_txt.sendKeys(p.getProperty("Symptoms"));
				Thread.sleep(3000);
				re.Cause_txt.sendKeys(p.getProperty("Cause"));
				Thread.sleep(1000);
				re.Solution_txt.sendKeys(p.getProperty("Solution"));
				Thread.sleep(1000);
				re.UploadFile();
				Thread.sleep(5000);
				re.submit_btn.click();
				Thread.sleep(2000);
			}
		}
		driver.switchTo().window(Mw1);
		Thread.sleep(1000);
		re.discard_btn.click();
		Thread.sleep(2000);
		re.Action_btn.click();
		Thread.sleep(2000);
		re.yes_btn.click();
		Thread.sleep(1000);
		re.submit_btn.click();
		Thread.sleep(2000);
	}
	
		// KB approval
		@Test(priority = 8)
		public void KBApproval() throws InterruptedException {
			re = new reusable(driver);
			re.Status_btn.click();
			Thread.sleep(5000);
			re.yes_btn.click();
			Thread.sleep(1000);
			re.submit_btn.click();
			Thread.sleep(2000);
		}
		
		// User Approval
		@Test(priority = 9)
		public void UserApproval() throws InterruptedException {
			re = new reusable(driver);
			re.Status_btn.click();
			Thread.sleep(5000);
			re.yes_btn.click();
			Thread.sleep(1000);
			re.submit_btn.click();
		}

}
