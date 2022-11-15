package RaiseIssue;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class reusable 
{
	public WebDriver driver;
	Properties p;
	
	//username
	@FindBy(xpath="//input[@id='un']")
	WebElement username_txt;
	
	//password
	@FindBy(xpath="//input[@id='pw']")
	WebElement password_txt;
	
	//SignIn
	@FindBy(xpath="//input[@value='Sign In']")
	WebElement SignIn_btn;
	
	//Raise a New Case
	@FindBy(xpath="//strong[normalize-space()='Raise a Case']")
	WebElement NewCase_btn;
	
	//Application Name
	@FindBy(xpath="//div[@id='f9c8932aae4bcbf16199cde76c9c38bc_value']")
	WebElement AppName_ddl;
	
	//Ticket Title
	@FindBy(xpath="//input[@placeholder='Enter the title for ticket ']")
	WebElement TitleName;
	
	//Category
	@FindBy(xpath="//div[@id='6b5c29463dd11df57efac4ee89c11520_value']")
	WebElement Category;
	
	//Description
	@FindBy(xpath="//textarea[@id='d43ec901083db4050c21a755aa83f6a8']")
	WebElement Desc_txt;
	
	//Priority
	@FindBy(xpath="//div[@id='399763c161a199c3b8290416b1ba7f46_value']")
	WebElement priority_ddl;
	
	//Upload
	@FindBy(xpath="//button[contains(text(),'Upload')]")
	WebElement upload_file;
	
	//Steps to Reproduce
	@FindBy(xpath="//textarea[@id='206b37862c3e012c60426e15ad78313a']")
	WebElement StepsReproduce_txt;
	
	//Raised For
	@FindBy(xpath="//input[@id='80988a1a952f3eb49032d5e92a09ebda_input']")
	WebElement RaisedFor_txt;
	
	//Submit Button
	@FindBy(xpath="//button[normalize-space()='Submit']")
	WebElement submit_btn;
	
	//Go to Home
	@FindBy(xpath="//button[contains(.,'Go to Home')]")
	WebElement HomePage_btn;
	
	//FirstRowData
	@FindBy(xpath="//tr[@aria-label='Row 1 not selected. Press space to select.']//p[@class='LinkGroup---link_group LinkGroup---center elements---global_p']/a")
	WebElement FirstRowData;
	
	//SearchText
	@FindBy(xpath="//input[@placeholder='Search Requests']")
	WebElement Searchtxt;
	
	//Clicking Action
	@FindBy(xpath="//a[@class='LinkedItem---standalone_richtext_link LinkedItem---inRecordActionWidget elements---global_a']")
	WebElement Action_btn;
	
	//Assigning
	@FindBy(xpath="//input[@placeholder='please select the assignee']")
	WebElement Assignee_txt;
	
	//Analysis-Status
	@FindBy(xpath="//p[@class='ParagraphText---richtext_paragraph ParagraphText---default_direction ParagraphText---center ParagraphText---nowrap elements---global_p']/a")
	WebElement Status_btn;
	
	//Yes Button
	@FindBy(xpath="//div[@class='RadioSelect---choice_pair'][1]/label")
	WebElement yes_btn;
	
	//No Button
	@FindBy(xpath="//div[@class='RadioSelect---choice_pair'][2]/label")
	WebElement no_btn;
	
	//KB document Row
	@FindBy(xpath="//th[@role='cell']//div[@role='group']")
	WebElement KbRow_btn;
	
	//Add New KB doc
	@FindBy(xpath="//body[1]/div[1]/div[4]/div[1]/div[1]/appian-related-action-dialog[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]")
	WebElement AddNewKbDoc;
	
	//KB doc button
	@FindBy(xpath="//span[contains(text(),'Add New KB Document')]")
	WebElement KbDocbtn;
	
	//Symptoms
	@FindBy(xpath="//div//div[1]/div[2]/div/input")
	WebElement Symptoms_txt;
	
	//Cause
	@FindBy(xpath="//div[2]//div[1]/div[2]/div[2]/div/textarea")
	WebElement Cause_txt;
	
	//Solution
	@FindBy(xpath="//div[2]/div/div/div[2]/div[2]/div[2]/div/textarea")
	WebElement Solution_txt;
	
	//Discard
	@FindBy(xpath="//button[normalize-space()='DISCARD']")
	WebElement discard_btn;
	
	//Re-assigned
	@FindBy(xpath="//a[contains(.,' Re-Assigned')]")
	WebElement reassigned_link;
	
	//Refresh
	@FindBy(xpath="//div[contains(@title,'Refresh')]")
	WebElement Refresh_btn;
	
	public reusable(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Login into the Application
	public void Valid_Cred(String username,String password) throws InterruptedException
	{
		username_txt.sendKeys(username);
		password_txt.sendKeys(password);
		SignIn_btn.click();
		Thread.sleep(4000);
	}
	
	//Upload File
	public void UploadFile() throws InterruptedException, AWTException, IOException
	{
		p=new Properties();
		File file=new File("E:\\Selenium\\TAMS\\Config\\DataFile.properties");
		FileInputStream fis=new FileInputStream(file); 
		p.load(fis);
		upload_file.click();
		StringSelection stringSelection = new StringSelection(p.getProperty("FilePath"));
		Thread.sleep(5000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(1000);   
	}
	
	
	
	
	

	
}
