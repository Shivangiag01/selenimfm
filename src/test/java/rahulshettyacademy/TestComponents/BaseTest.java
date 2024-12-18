package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;

	public WebDriver browserInitialization() throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\resources\\rahulshettyacademyresources\\GlobalData.properties");
		prop.load(file);
	
		
		//this is done to execute browser value from maven command
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browsername = prop.getProperty("browser")
		

		if (browsername.contains("chrome")) {
			ChromeOptions option= new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browsername.contains("headless")) {			
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,990));
			
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod
	public void launchApp() throws IOException {
		driver = browserInitialization();
		driver.get("https://rahulshettyacademy.com/client");

	}

	public List<HashMap<String, String>> getJsonData(String filename) throws IOException {
		// read json file and convert to string- use file inbuilt utility
		String jsonData = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);

		// convert String to HashMap- using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,new TypeReference<List<HashMap<String, String>>>() {
				});
		HashMap<String,String> d1= data.get(0);
		System.out.println(d1.get("username"));
		return data;
	}
	
	
	public  String getScreenshot(String testcasename) throws IOException {		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File dest= new File(System.getProperty("user.dir")+ "//reports//"+testcasename+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+ "//reports//"+testcasename+".png";		
	}

	
	 @AfterMethod 
	 public void driverClose()
	 { 
		 driver.quit(); 
	 }
	 
}
