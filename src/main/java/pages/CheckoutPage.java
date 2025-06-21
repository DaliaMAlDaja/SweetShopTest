package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {

    WebDriver driver;
    Random rand = new Random();

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCustomerInfo(String fname, String lname, String address, String email) throws InterruptedException {
        driver.findElement(By.xpath("(//input[@id='name'])[1]")).sendKeys(fname);
        Thread.sleep(300);
        driver.findElement(By.xpath("(//input[@id='name'])[2]")).sendKeys(lname);
        Thread.sleep(300);
        driver.findElement(By.id("email")).sendKeys(email);
        Thread.sleep(300);
        driver.findElement(By.id("address")).sendKeys(address);
        Thread.sleep(300);
    }

    public void selectCountryAndCity() throws InterruptedException {
        new Select(driver.findElement(By.id("country"))).selectByVisibleText("United Kingdom");
        Thread.sleep(300);

        Select citySelect = new Select(driver.findElement(By.id("city")));
        int index = rand.nextInt(citySelect.getOptions().size() - 1) + 1;
        citySelect.selectByIndex(index);
        Thread.sleep(300);
    }

    public void enterRandomZIP(String[] zipCodes) throws InterruptedException {
        String randZIP = zipCodes[rand.nextInt(zipCodes.length)];
        driver.findElement(By.id("zip")).sendKeys(randZIP);
        Thread.sleep(300);
    }

    public void enterPaymentDetails(String name, String cardNumber, String exp, String cvv) throws InterruptedException {
        driver.findElement(By.id("cc-name")).sendKeys(name);
        Thread.sleep(300);
        driver.findElement(By.id("cc-number")).sendKeys(cardNumber);
        Thread.sleep(300);
        driver.findElement(By.id("cc-expiration")).sendKeys(exp);
        Thread.sleep(300);
        driver.findElement(By.id("cc-cvv")).sendKeys(cvv);
        Thread.sleep(300);
    }

    public void clickCheckoutButton() {
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")).click();
    }

    public List<WebElement> getValidationMessages() {
        return driver.findElements(By.cssSelector(".invalid-feedback"));
    }
}
