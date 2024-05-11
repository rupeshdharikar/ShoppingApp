package Amazon.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Amazon.TestComponents.BaseTest;
import Amazon.pageObjects.CheckOutPage;
import Amazon.pageObjects.OrderCart;
import Amazon.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName="ZARA COAT 3";
	@Test(dataProvider= "getdata",groups="Purchase")
public void submitOrder(HashMap <String,String> input) throws IOException {
	
	ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
	List<WebElement> products= productcatalogue.getProductList();
	OrderCart ordercart = productcatalogue.addToCart(input.get("product"));
	ordercart.goToCart();
	Boolean match = ordercart.verifyProductDisplay(input.get("product"));
	Assert.assertTrue(match);
	CheckOutPage checkoutpage = ordercart.checkOut();
	checkoutpage.selectCountry("India");
	String confirmMessage = checkoutpage.verifyConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}
	@Test(dependsOnMethods= "submitOrder")
	public void orderHistory() {
		ProductCatalogue productcatalogue = landingpage.loginApplication("dhrupesh@gmail.com", "Rupe@123");
		OrderCart ordercart = new OrderCart(driver);
		Boolean match = ordercart.orderPage(productName);
		Assert.assertTrue(match);
	}
	
	public String getScreensShot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir"+"//reports"+testCaseName + ".png"));
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir"+"//reports"+testCaseName + ".png");
	}
	@DataProvider
	public Object[][] getdata() throws IOException {
		
		List<HashMap<String, String>> data = getDataJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Amazon\\data\\PurchaseOrder.json");
		
		data.get(0);
		data.get(1);
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
//		HashMap<Object,Object> map = new HashMap<Object,Object>();
//		map.put("email", "dhrupesh@gmail.com");
//		map.put("password", "Rupe@123");
//		map.put("product", "ZARA COAT 3");
//		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//		map1.put("email", "anshdharikar1@gmail.com");
//		map1.put("password", "Ansh@937");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
	}
}
