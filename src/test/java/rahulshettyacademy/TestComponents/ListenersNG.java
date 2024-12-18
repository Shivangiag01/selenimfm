package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulsettyacademy.AbstractionComponents.ExtentReportsNG;

public class ListenersNG extends BaseTest implements ITestListener {
	
	 ExtentTest test;
	    ExtentReportsNG extentng = new ExtentReportsNG();
	    ExtentReports extent = extentng.getExtentReportObject();
	    

	    @Override
	    public void onTestStart(ITestResult result) {
	        test = extent.createTest(result.getMethod().getMethodName());
	        System.out.println("Test Started: " + result.getMethod().getMethodName());
	        System.out.println("Driver instance: " + driver);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.fail(result.getThrowable());
	        System.out.println("Driver instance in Test Failure: " + driver);
	     
	        String screenshotPath = null;
	        try {
	            // Directly use 'driver' initialized in BaseTest
	            screenshotPath = getScreenshot(result.getMethod().getMethodName());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        if (screenshotPath != null) {
	            test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	        }
	        
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // Optionally handle skipped tests
	        ITestListener.super.onTestSkipped(result);
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Optionally handle tests that failed but within success percentage
	        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	    }

	    @Override
	    public void onTestFailedWithTimeout(ITestResult result) {
	        // Optionally handle tests that failed with a timeout
	        ITestListener.super.onTestFailedWithTimeout(result);
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // Optionally handle the start of the test suite
	        ITestListener.super.onStart(context);
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // Optionally handle the end of the test suite
	        extent.flush();
	    }
   
}
