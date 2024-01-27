package CommonUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ListenerImplementation implements ITestListener{

	public ExtentReports report;
	public ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		Reporter.log("Testscript execution is started", true);
		test=report.createTest(methodname);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		//Reporter.log(methodname+"Testscript is Passed", true);
		test.log(Status.PASS, methodname+"TestScript is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String message = result.getThrowable().toString();
		String methodname = result.getMethod().getMethodName();
		//Reporter.log(methodname+"Testscript is failed"+message, true);
		test.log(Status.FAIL, methodname+"TestScript is failed");
		String screenshotname=methodname;
		test.log(Status.FAIL, methodname);
		test.log(Status.FAIL, result.getThrowable());
		WebDriverUtil wutil=new WebDriverUtil();
		try {
			String path = wutil.takesScreenShot(BaseClass.sdriver, screenshotname);
			//To add Screenshot to extent report
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		//Reporter.log(methodname+"Testscript is Skipped", true);
		test.log(Status.SKIP, methodname+"TestScript is skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		
		JavaUtils jutil=new JavaUtils();
		ExtentSparkReporter htmlreport=new ExtentSparkReporter("./ExtentReport/Report.html"+jutil.getRandomNumer());
		htmlreport.config().setDocumentTitle("VtigerCRM FrameWork");
		htmlreport.config().setReportName("VtigerCRM");
		htmlreport.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		
		report.attachReporter(htmlreport);
		report.setSystemInfo("Operating System", "Windows");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Chrome Version", "120.060");
		report.setSystemInfo("Programming Language", "Java");
		report.setSystemInfo("Author", "MaheshJ");
	
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

	
}
