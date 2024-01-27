package CommonUtils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	
	PropertyFileUtil putil=new PropertyFileUtil();
	ExcelUtil eutil=new ExcelUtil();
	
	@BeforeSuite
	public void BS()
	{
		System.out.println("Connect to database");
	}
	
	@BeforeClass
	public void BC() throws IOException
	{
		String BROWSER = putil.getDataFromPropertyFile("browser");
		String URL = putil.getDataFromPropertyFile("url");
		if(BROWSER.equalsIgnoreCase("Chrome"))
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
		
		sdriver=driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		driver.get(URL);
		

	}
	
	
	@BeforeMethod
	public void BM() throws IOException
	{
		String USERNAME = putil.getDataFromPropertyFile("username");
		String PASSWORD = putil.getDataFromPropertyFile("password");
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();	
	}
	
	@AfterMethod
	public void AM()
	{
		WebElement logo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(logo).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}
	
	@AfterClass
	public void AC()
	{
		driver.quit();
	}


	@AfterSuite
	public void AS()
	{
		System.out.println("Disconnect from the Database");
	}
	

}
