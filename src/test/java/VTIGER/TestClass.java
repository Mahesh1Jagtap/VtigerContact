package VTIGER;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtils;
import CommonUtils.ListenerImplementation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

@Listeners(ListenerImplementation.class)
public class TestClass extends BaseClass {

	public WebDriver driver;
	@Test
	public void createOrganization() throws IOException
	{
		
		PropertyFileUtil putil=new PropertyFileUtil();
		JavaUtils jutil=new JavaUtils();
		WebDriverUtil wutil=new WebDriverUtil();

				//Click on Oraganization
				driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
				
				//Click on Create Organization...
				driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
				
				//Enter Organization name
				driver.findElement(By.name("accountname")).sendKeys(putil.getDataFromPropertyFile("organizationname")+jutil.getRandomNumer());
				
				//select industry dropdown
				WebElement dropdown1 = driver.findElement(By.name("industry"));
				wutil.handleDropDownValue(dropdown1, putil.getDataFromPropertyFile("industry"));
				
				//Select a Group radio button
				driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
				
				//Select a Group dropdown
				WebElement dropdown2 = driver.findElement(By.name("assigned_group_id"));
				wutil.handleDropDown(dropdown2, putil.getDataFromPropertyFile("group"));
				
				//click on save button
				driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
	}
	
	@Test
	public void creatContact() throws EncryptedDocumentException, IOException
	{
		ExcelUtil eutil=new ExcelUtil();
		WebDriverUtil wutil=new WebDriverUtil();
		
		//click on Contacts
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				
				//click on Create Contact...
				driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
				
				//Enter the firstname
				driver.findElement(By.name("firstname")).sendKeys(eutil.getDataFromExcel("Contact", 4, 1));
				
				//Enter the lastname
				driver.findElement(By.name("lastname")).sendKeys(eutil.getDataFromExcel("Contact", 5, 1));
				
				//Click on Organizationname plus icon
				driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
				
				//Switch to child window
				wutil.Switch(driver, "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
				
				//enter in the Serach textfield
				driver.findElement(By.name("search_text")).sendKeys(eutil.getDataFromExcel("Contact", 6, 1));
				
				//click on Search
				driver.findElement(By.name("search")).click();
				
				//click on pune
				driver.findElement(By.xpath("//a[text()='Pune']")).click();
				
				//Switch to Parent Window
				wutil.Switch(driver, "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
				
				//Enter the Birthdate
				driver.findElement(By.name("birthday")).sendKeys("2006-07-11");
				
				driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
				
				WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
				wutil.handleDropDown(dropdown, eutil.getDataFromExcel("Contact", 8, 1));
				
				//click on save button
				driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
				
	}
	
	@Test
	public void createlead() throws IOException
	{
		PropertyFileUtil putil=new PropertyFileUtil();
		WebDriverUtil wutil=new WebDriverUtil();
		
		//click on Leads
		driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		
		//click on plus icon
		driver.findElement(By.cssSelector("img[alt='Create Lead...']")).click();
		
		//Enter firstname
		driver.findElement(By.name("firstname")).sendKeys(putil.getDataFromPropertyFile("firstname"));
		
		//Enter Lastname
		driver.findElement(By.name("lastname")).sendKeys(putil.getDataFromPropertyFile("lastname"));
		
		//Enter Company Name
		driver.findElement(By.name("company")).sendKeys(putil.getDataFromPropertyFile("companyname"));
		
		//Select Radio Button Group
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dropdown1 = driver.findElement(By.name("assigned_group_id"));
		wutil.handleDropDown(dropdown1, putil.getDataFromPropertyFile("group"));
		
		//click on Save Button
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
	}
	
	
}
