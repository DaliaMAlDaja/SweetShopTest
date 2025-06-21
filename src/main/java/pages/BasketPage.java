package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasketPage {

    WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openBasket() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='badge badge-success']")).click();
        Thread.sleep(1500);
    }

    public void emptyBasket() {
        driver.findElement(By.xpath("//a[@onclick='emptyBasket();']")).click();
    }

    public void confirmAlert() throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        Thread.sleep(1000);
        alert.accept();
    }

    public void removeItemById(int id) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("removeItem(" + id + ");");
    }

    public WebElement getBasketCountElement() {
        return driver.findElement(By.id("basketCount"));
    }
}
