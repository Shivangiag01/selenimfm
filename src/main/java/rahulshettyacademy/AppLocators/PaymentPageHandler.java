package rahulshettyacademy.AppLocators;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulsettyacademy.AbstractionComponents.AbstractComponents;

public class PaymentPageHandler extends AbstractComponents {

	WebDriver driver;

	public PaymentPageHandler(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By spinner = By.className("ngx-spinner-overlay");

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']/span")
	List<WebElement> dropdown;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryplaceholder;

	@FindBy(css = ".action__submit")
	WebElement payment;

	public void selectCountryAndMakePayment(String countrynameentered) {
		waitproductsToInvisible(spinner);
		countryplaceholder.sendKeys(countrynameentered);
		waitListproductsToVisible(dropdown);
		System.out.println("Fetching list Elements");
		for (WebElement country : dropdown) {
			String countrylist = country.getText();
			System.out.println(countrylist);
			if (countrylist.equalsIgnoreCase("India")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", country);
				System.out.println("country is selected");
				break;
			}
		}
		payment.click();
	}
}
