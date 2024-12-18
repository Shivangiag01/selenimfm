package rahulshettyacademy.AppLocators;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulsettyacademy.AbstractionComponents.AbstractComponents;

public class ProductPageHandler extends AbstractComponents {

	WebDriver driver;

	public ProductPageHandler(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By productlist = By.cssSelector(".col-lg-4");
	@FindBy(css = ".col-lg-4")
	List<WebElement> products;
	By toastermessage = By.cssSelector("div.toast-container");
	By spinner = By.className("ngx-spinner-overlay");

	public List<WebElement> getProductsList() {
		waitproductsToVisible(productlist);
		return products;
	}

	public void getProduct(String productname) {
		System.out.println("Number of products: " + getProductsList().size());
		for (WebElement product : getProductsList()) {
			String pd = product.findElement(By.xpath(".//h5/b")).getText();
			System.out.println(pd);
			System.out.println(productname);
			if (pd.equalsIgnoreCase(productname)) {
				WebElement button = product.findElement(By.cssSelector("button.btn.w-10.rounded"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
				break;
			}else  if (!pd.equalsIgnoreCase(productname)) {
			    throw new AssertionError("Product with name '" + productname + "' is not present.");
			}
		}

		waitproductsToVisible(toastermessage);
		waitproductsToInvisible(spinner);
	}
}
