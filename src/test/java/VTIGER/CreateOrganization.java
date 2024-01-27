package VTIGER;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import CommonUtils.BaseClass;
import CommonUtils.JavaUtils;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class CreateOrganization extends BaseClass{

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver;
		
		PropertyFileUtil putil=new PropertyFileUtil();
		WebDriverUtil wutil=new WebDriverUtil();
		JavaUtils jutil=new JavaUtils();
		
		String BROWSER = putil.getDataFromPropertyFile("browser");
		
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
		
		String URL = putil.getDataFromPropertyFile("url");
		
		//Launch the Browser
		driver.get(URL);
		wutil.maximizeWindow(driver);
		
		//To read the data from Property file
		String USERNAME = putil.getDataFromPropertyFile("username");
		String PASSWORD = putil.getDataFromPropertyFile("password");
		String ONAME = putil.getDataFromPropertyFile("organizationname");
		String INDUSTRY = putil.getDataFromPropertyFile("industry");
		String GROUP = putil.getDataFromPropertyFile("group");
		
		//Login to the Application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Click on Oraganization
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//Click on Create Organization...
		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		
		//Enter Organization name
		driver.findElement(By.name("accountname")).sendKeys(ONAME+jutil.getRandomNumer());
		
		//select industry dropdown
		WebElement dropdown1 = driver.findElement(By.name("industry"));
		wutil.handleDropDownValue(dropdown1, INDUSTRY);
		
		//Select a Group radio button
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		//Select a Group dropdown
		WebElement dropdown2 = driver.findElement(By.name("assigned_group_id"));
		wutil.handleDropDown(dropdown2, GROUP);
		
		//click on save button
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();

		Thread.sleep(2000);
		//Mousehover on Administrator logo
		WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, logo);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//Signout from the Application
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}

}
