package Amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (id="userEmail")
	WebElement userid;
	@FindBy (id="userPassword")
	WebElement pass;
	@FindBy (id="login")
	WebElement login;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public ProductCatalogue loginApplication(String email, String password) {
		userid.sendKeys(email);
		pass.sendKeys(password);
		login.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	public String erroValidation() {
		waitUntilWebElementToAppear(errorMessage);
		String errorText = errorMessage.getText();
		return errorText;
	}
}
	