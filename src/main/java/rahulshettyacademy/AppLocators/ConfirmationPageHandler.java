package rahulshettyacademy.AppLocators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsettyacademy.AbstractionComponents.AbstractComponents;

public class ConfirmationPageHandler extends AbstractComponents{

	WebDriver driver;
	
	public ConfirmationPageHandler(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(css=".hero-primary")
	WebElement confirmationmessage;
	
	
	public String getConfirmationMessage() {		
		String message=confirmationmessage.getText();
		return message;
	}
}
