package rahulshettyacademy.AppLocators;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsettyacademy.AbstractionComponents.AbstractComponents;

public class LandingPageHandler extends AbstractComponents {
	WebDriver driver;

	@FindBy(id = "userEmail")
	WebElement emailField;

	@FindBy(id = "userPassword")
	WebElement passwordField;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginButton;

	public LandingPageHandler(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Driver in LandingPageHandler constructor: " + this.driver);
	}

	public void login(String useremail, String password) {
		emailField.sendKeys(useremail);
		passwordField.sendKeys(password);
		loginButton.click();
	}
}
