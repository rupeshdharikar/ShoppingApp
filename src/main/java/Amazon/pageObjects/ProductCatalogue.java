package Amazon.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".mb-3")
	List<WebElement> products;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	@FindBy (css=".ng-animating")
	WebElement spinner;
	public List<WebElement> getProductList() {
		waitUntilElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->product
		.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	public OrderCart addToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartButton).click();
		waitUntilElementToAppear(toastMessage);
		waitUntilElementToDisappear(spinner);
		OrderCart ordercart = new OrderCart(driver);
		return ordercart;
		
	}
	
}
	