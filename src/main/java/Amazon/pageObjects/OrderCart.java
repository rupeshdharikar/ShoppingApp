package Amazon.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AbstractComponents.AbstractComponent;

public class OrderCart extends AbstractComponent{

	WebDriver driver;
	public OrderCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy (css=".cartSection h3")
	List<WebElement> itemsToMyCart;
	@FindBy (css=".totalRow button")
	WebElement checkOutButton;
	@FindBy (css="button[routerlink*='myorders']")
	WebElement orderHeader;
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> listOfProducts;
	public void goToCart() {
		cartHeader.click();
		
	}
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = itemsToMyCart.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage checkOut() {
		checkOutButton.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
	}
	
public Boolean orderPage(String productName) {
	orderHeader.click();
	Boolean match = listOfProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	return match;
	}
}
	