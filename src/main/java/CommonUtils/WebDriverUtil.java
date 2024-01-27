package CommonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtil {

	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	public void fullScreenWindow(WebDriver driver)
	{
		driver.manage().window().fullscreen();
	}
	
	public void implicitlyWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void handleDropDown(WebElement target, String text)
	{
		Select s=new Select(target);
		s.selectByVisibleText(text);
	}
	
	public void handleDropDownValue(WebElement target, String text)
	{
		Select s=new Select(target);
		s.selectByValue(text);
	}
	
	public void handleDropDown(WebElement target, int index)
	{
		Select s=new Select(target);
		s.selectByIndex(index);
	}
	
	public void Switch(WebDriver driver, String expectedurl)
	{
		Set<String> ids = driver.getWindowHandles();
		System.out.println(ids);
		
		for(String e: ids)
		{
			String actualurl = driver.switchTo().window(e).getCurrentUrl();
			System.out.println(actualurl);
			
			if(actualurl.contains(expectedurl))
			{
				break;
			}
		}
	}
	
	public String takesScreenShot(WebDriver driver, String Screenshotname) throws IOException
	{
		LocalDateTime ldt=LocalDateTime.now();
		String timestramp=ldt.toString().replace(':', '-');
		TakesScreenshot ts=(TakesScreenshot) driver;
		File tempfile = ts.getScreenshotAs(OutputType.FILE);
		File destinationfile=new File("./ScreenShots/"+"Screenshotname"+"timestramp"+".png");
		FileUtils.copyFile(tempfile, destinationfile);
		return destinationfile.getAbsolutePath();
	}
	
	public void okPopup(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void cancelPopup(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void textPopup(WebDriver driver)
	{
		driver.switchTo().alert().getText();
	}
	
	public void valuePopup(WebDriver driver, String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	public void frames(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void frames(WebDriver driver, String name)
	{
		driver.switchTo().frame(name);
	}
	
	public void frames(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void pageLoad(WebDriver driver)
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}
	
	public void waitWebElementtoDisplay(WebDriver driver, String expectedurl)
	{
		WebDriverWait waits=new WebDriverWait(driver, Duration.ofSeconds(10));
		waits.until(ExpectedConditions.urlToBe(expectedurl));
	}
	
	public void mouseHover(WebDriver driver, WebElement target)
	{
		Actions a=new Actions(driver);
		a.moveToElement(target).perform();
	}
	
	
	
	
}
