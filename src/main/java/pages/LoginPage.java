
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginWithCredentials(String email, String password) {
        driver.findElement(By.id("exampleInputEmail")).sendKeys(email);
        driver.findElement(By.id("exampleInputPassword")).sendKeys(password);
    	driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    }

    public String getDisplayedWelcomeEmail() {
        String msg = driver.findElement(By.cssSelector("p.lead a")).getText();
        return msg;
    }
}
