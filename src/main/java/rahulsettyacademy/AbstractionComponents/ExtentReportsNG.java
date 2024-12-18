package rahulsettyacademy.AbstractionComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportsNG {
	
	
	public ExtentReports getExtentReportObject() {
		
	String filepath= System.getProperty("user.dir")+ "//reports//index.html";	
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filepath);
    sparkReporter.config().setReportName("Automation Results");
    sparkReporter.config().setDocumentTitle("Shivangi Results");
   
    ExtentReports extent = new ExtentReports();
    extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Tester", "Shivangi Agarwal");		
		return extent;	
			
	}
}
