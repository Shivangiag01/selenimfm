package rahulshettyacademy.AppLocators;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import rahulsettyacademy.AbstractionComponents.AbstractComponents;

public class CartPageHandler extends AbstractComponents {

	WebDriver driver;

	public CartPageHandler(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartbutton;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartproducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutbutton;

	public void clickOnCart() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartbutton);
	}

	public void getDesriedProductInCart(String desiredproduct) {
		for (WebElement cartproduct : cartproducts) {
			String productname = cartproduct.getText();
			if (productname.equalsIgnoreCase(desiredproduct)) {
				break;
			}
		}

		waitWebproductsToVisible(checkoutbutton);
		checkoutbutton.click();
	}

}
