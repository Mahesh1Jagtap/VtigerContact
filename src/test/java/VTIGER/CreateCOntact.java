package VTIGER;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import CommonUtils.BaseClass;
import CommonUtils.ExcelUtil;
import CommonUtils.WebDriverUtil;

public class CreateCOntact extends BaseClass {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
		WebDriver driver;
		
		ExcelUtil eutil=new ExcelUtil();
		WebDriverUtil wutil=new WebDriverUtil();
		
		String BROWSER = eutil.getDataFromExcel("Contact", 0, 1);
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		String URL = eutil.getDataFromExcel("Contact", 1, 1);
		driver.get(URL);
		wutil.maximizeWindow(driver);
		
		//To Read the data from Excel file
		String USERNAME = eutil.getDataFromExcel("Contact", 2, 1);
		String PASSWORD = eutil.getDataFromExcel("Contact", 3, 1);
		String FIRSTNAME = eutil.getDataFromExcel("Contact", 4, 1);
		String LASTNAME = eutil.getDataFromExcel("Contact", 5, 1);
		String SEARCH = eutil.getDataFromExcel("Contact", 6, 1);
	
		String GROUP = eutil.getDataFromExcel("Contact", 8, 1);
		
		//Login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//click on Contacts
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//click on Create Contact...
		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		
		//Enter the firstname
		driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
		
		//Enter the lastname
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Click on Organizationname plus icon
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		
		//Switch to child window
		wutil.Switch(driver, "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
		
		//enter in the Serach textfield
		driver.findElement(By.name("search_text")).sendKeys(SEARCH);
		
		//click on Search
		driver.findElement(By.name("search")).click();
		
		//fail the testScript
		Assert.assertEquals("Pune", "Deccan");
		
		//click on pune
		driver.findElement(By.xpath("//a[text()='Pune']")).click();
		
		//Switch to Parent Window
		wutil.Switch(driver, "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		
		//Enter the Birthdate
		driver.findElement(By.name("birthday")).sendKeys("2006-07-11");
		
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		wutil.handleDropDown(dropdown, GROUP);
		
		//click on save button
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		
		Thread.sleep(2000);
		WebElement logo = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, logo);
		
		//click on Signout button
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
	}

}
