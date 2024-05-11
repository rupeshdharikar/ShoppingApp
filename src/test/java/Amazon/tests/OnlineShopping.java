package Amazon.tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OnlineShopping {

public static void main(String args[]) throws InterruptedException {
	
	String productName="ZARA COAT 3";
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://rahulshettyacademy.com/client");
	driver.manage().window().maximize();
	driver.findElement(By.id("userEmail")).sendKeys("dhrupesh@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Rupe@123");
	driver.findElement(By.id("login")).click();
	
	String loginSuccess= driver.findElement(By.id("toast-container")).getText();
	System.out.println(loginSuccess);
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod = products.stream().filter(product->product
					.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	System.out.println(driver.findElement(By.xpath("//div[@aria-label='Product Added To Cart']")).getText());
	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
	wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".ta-results"))));
	driver.findElement(By.cssSelector(".ta-results button:last-child")).click();
	
	Actions a= new Actions(driver);
	a.click(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
	String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	System.out.println(confirmMessage);
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
}
}
