package rahulshettyacademy.SeleniumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import rahulshettyacademy.AppLocators.CartPageHandler;
import rahulshettyacademy.AppLocators.ConfirmationPageHandler;
import rahulshettyacademy.AppLocators.LandingPageHandler;
import rahulshettyacademy.AppLocators.PaymentPageHandler;
import rahulshettyacademy.AppLocators.ProductPageHandler;
import rahulshettyacademy.TestComponents.BaseTest;

public class AppUsingPOM extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	//public void OrderPlacement(String username, String password, String productname)
	public void OrderPlacement(HashMap<String,String> input)throws IOException {

		// login page
		 System.out.println("Driver instance in APPPOM: " + driver);
		LandingPageHandler landingpage = new LandingPageHandler(driver);
		landingpage.login(input.get("username"), input.get("password"));

		// product page
		ProductPageHandler productpage = new ProductPageHandler(driver);
		productpage.getProduct(input.get("productname"));

		CartPageHandler cartpage = new CartPageHandler(driver);
		cartpage.clickOnCart();
		cartpage.getDesriedProductInCart(input.get("productname"));

		PaymentPageHandler paymentpage = new PaymentPageHandler(driver);
		paymentpage.selectCountryAndMakePayment("Ind");
		

		ConfirmationPageHandler confirmationpage = new ConfirmationPageHandler(driver);
		Assert.assertEquals(confirmationpage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {		
		
		List<HashMap<String,String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\raulshettyacademy.testdata\\OrderTestData.json");
		return new Object[][] {
			{data.get(0)},
			{data.get(1)}
		};
		
		/*
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("username", "shivangiag004@gmail.com");
		map.put("password", "Shivi@0881");
		map.put("productname", "ZARA COAT 3");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map.put("username", "shivangiag004@gmail.com");
		map.put("password", "Shivi@0881");
		map.put("productname", "ADIDAS ORIGINAL");
		
		return new Object[][] {
			{map},{map1}
		 };
		 
		 */
	//return new Object[][] { { "shivangiag004@gmail.com", "Shivi@0881", "ZARA COAT 3" }, { "shivangiag004@gmail.com", "Shivi@0881", "ADIDAS ORIGINAL" }};
			
	}

}
