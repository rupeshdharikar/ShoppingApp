package Amazon.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy (css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy (css=".ta-results button:last-child")
	WebElement countrySelectFromList;
	
	@FindBy (css=".action__submit")
	WebElement placeOrder;
	@FindBy (css=".hero-primary")
	WebElement textMessage;
	
	public void selectCountry(String countryName) {
		selectCountry.sendKeys(countryName);
		countrySelectFromList.click();
	}
	
	public String verifyConfirmationMessage() {
		Actions a= new Actions(driver);
		a.click(placeOrder).build().perform();
		String confirmMessage=textMessage.getText();
		System.out.println(confirmMessage);
		return confirmMessage;		
	}
	
}
	