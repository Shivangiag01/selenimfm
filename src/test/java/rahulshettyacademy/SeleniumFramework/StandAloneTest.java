package rahulshettyacademy.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("shivangiag004@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shivi@0881");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofMillis(700));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		for (WebElement product : products) {
			String pd = product.findElement(By.xpath("//div/div/h5/b")).getText();
			if (pd.equalsIgnoreCase("ZARA COAT 3")) {
				WebElement button = product.findElement(By.cssSelector("button.btn.w-10.rounded"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));

		WebElement cartbutton = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartbutton);

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		for (WebElement cartproduct : cartproducts) {
			String productname = cartproduct.getText();
			if (productname.equalsIgnoreCase("ZARA COAT 3")) {
				Assert.assertTrue(true);
				System.out.println("added to place order page");
			}
		}

		driver.findElement(By.cssSelector(".totalRow button")).click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));
		// placing order page

		Actions act = new Actions(driver);
		WebElement countryplaceholder = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		wait.until(ExpectedConditions.visibilityOf(countryplaceholder));
		act.sendKeys(countryplaceholder, "Ind").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".ta-results"))));
		WebElement result = driver.findElement(By.cssSelector(".ta-results"));

		List<WebElement> countrylist = result.findElements(By.tagName("button"));
		for (WebElement country : countrylist) {
			String countryname = country.getText();
			System.out.println(countryname);
			if (countryname.equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}
		driver.findElement(By.cssSelector(".action__submit")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");

	}

}
