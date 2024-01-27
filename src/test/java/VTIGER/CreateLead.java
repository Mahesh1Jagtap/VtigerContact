package VTIGER;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import CommonUtils.BaseClass;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class CreateLead  extends BaseClass{

	public static void main(String[] args) throws IOException {
		
		 WebDriver driver;
			
			PropertyFileUtil putil=new PropertyFileUtil();
			WebDriverUtil wutil=new WebDriverUtil();
			
			
			String BROWSER = putil.getDataFromPropertyFile("browser");
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("Edge"))
			{
				driver=new EdgeDriver();
			}
			else
			{
				driver=new FirefoxDriver();
				System.out.println("Default Browser");
			}
			
			
			String URL = putil.getDataFromPropertyFile("url");
			driver.get(URL);
			driver.manage().window().maximize();
			//read the data from Propertyfile
			String USERNAME = putil.getDataFromPropertyFile("username");
			String PASSWORD=putil.getDataFromPropertyFile("password");
			String FIRSTNAME = putil.getDataFromPropertyFile("firstname");
			String LASTNAME = putil.getDataFromPropertyFile("lastname");
			String COMPANYNAME = putil.getDataFromPropertyFile("companyname");
			String GROUP = putil.getDataFromPropertyFile("group");
			
			//Login
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			//click on Leads
			driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
			
			//click on plus icon
			driver.findElement(By.cssSelector("img[alt='Create Lead...']")).click();
			
			//Enter firstname
			driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
			
			//Enter Lastname
			driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
			
			//Enter Company Name
			driver.findElement(By.name("company")).sendKeys(COMPANYNAME);
			
			//Select Radio Button Group
			driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
			
			WebElement dropdown1 = driver.findElement(By.name("assigned_group_id"));
			wutil.handleDropDown(dropdown1, GROUP);
			
			//click on Save Button
			driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
			
			WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			wutil.mouseHover(driver, logo);
			
			//click on Signout button
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			

	}

}
