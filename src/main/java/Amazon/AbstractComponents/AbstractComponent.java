package Amazon.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;	
	}
	
	public void waitUntilElementToAppear(By findEle) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findEle));
	}
	public void waitUntilWebElementToAppear(WebElement webEle) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webEle));
	}
	public void waitUntilElementToDisappear(WebElement findEle) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findEle));
	}
	
}
