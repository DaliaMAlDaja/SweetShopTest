package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickBrowseSweets() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement browseBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.btn-lg.sweets")));
		browseBtn.click();
	}

	public List<WebElement> getAllItems() {
		return driver.findElements(By.cssSelector(".addItem"));
	}

	public void addAllItemsToBasket() throws InterruptedException {
		for (WebElement item : getAllItems()) {
			item.click();
			Thread.sleep(300);
		}
	}

	public void returntoitempage() {
		driver.get("https://sweetshop.vivrichards.co.uk/sweets");
		
	}

	public List<WebElement> getAllCards() {
		return driver.findElements(By.cssSelector(".card"));
	}
	public void goToLoginPage() {
	    driver.findElement(By.cssSelector("a[href='/login']")).click();
	}

}
