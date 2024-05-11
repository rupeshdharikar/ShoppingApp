package Amazon.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.TestComponents.BaseTest;
import Amazon.pageObjects.OrderCart;
import Amazon.pageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{

	@Test
public void SubmitOrder() throws IOException {
	
	String productName="ZARA COAT 3";
	String userid = "dhrupesh@gmail.com";
	String password = "Rupe@123ansh";

	landingpage.loginApplication(userid, password);
	Assert.assertEquals(landingpage.erroValidation(), "Incorrect email or password.");
}
	@Test (groups="ErrorHandling" )
	public void productErrorValidation() {
		String productName="ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.loginApplication("anshdharikar1@gmail.com", "Ansh@937");
		List<WebElement> products= productcatalogue.getProductList();
		OrderCart ordercart = productcatalogue.addToCart(productName);
		ordercart.goToCart();
		Boolean match = ordercart.verifyProductDisplay("ZARA COAT 32");
		Assert.assertFalse(match);
	}
}
