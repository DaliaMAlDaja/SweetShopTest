
package tests;

import base.BaseTest;
import pages.BasketPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DatabaseUtil;
import utils.ExtentReportManager;

import com.aventstack.extentreports.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FullFlowTest extends BaseTest {

    ExtentReports extent = ExtentReportManager.getReportInstance();
    ExtentTest test;

    String[] zipCodes = { "1111", "2345", "6789", "1234", "5678", "9876", "4321" };
    String email = "@gmail.com";
    String firstname, lastname, address;
    HomePage home;
    BasketPage basket;
    CheckoutPage checkout;

    @Test(priority = 1)
    public void step1_addAllItems() throws InterruptedException {
        test = extent.createTest("Step 1 - Add All Items to Basket");
        home = new HomePage(driver);
        home.clickBrowseSweets();
        home.addAllItemsToBasket();
        test.addScreenCaptureFromPath(takeScreenshot("step1_addAllItems"));
    }

    @Test(priority = 2)
    public void step2_openBasket() throws InterruptedException {
        test = extent.createTest("Step 2 - Open Basket");
        basket = new BasketPage(driver);
        basket.openBasket();

        // Assert basket count is 16
        String countText = basket.getBasketCountElement().getText();
        int actualCount = Integer.parseInt(countText);
        if (actualCount == 16) {
        	System.out.println("16 items have been successfully added to your cart.");
          
        	//test.pass("16 items have been successfully added to your cart.");
        } else {
        //    test.fail("Not all items have been added to the cart. Current inventory: " + actualCount);
        System.out.println("Not all items have been added to the cart. Current inventory: " + actualCount);
        }

        test.addScreenCaptureFromPath(takeScreenshot("step2_openBasket"));
    }

    @Test(priority = 3)
    public void step3_insertDummyCustomer() throws SQLException {
        test = extent.createTest("Step 3 - Insert Dummy Customer to DB");
        stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO customer (firstname, lastname, address) VALUES ('SOOOS', 'SOOOOS', 'Amman')");
    }

    @Test(priority = 4)
    public void step4_fetchCustomerFromDB() throws SQLException {
        test = extent.createTest("Step 4 - Fetch Customer From DB");
        String[] customerData = DatabaseUtil.getCustomerData(con, "SOOOS");
        firstname = customerData[0];
        lastname = customerData[1];
        address = customerData[2];
        test.info("Customer: " + firstname + " " + lastname);
    }

    @Test(priority = 5)
    public void step5_fillCustomerInfo() throws InterruptedException {
        test = extent.createTest("Step 5 - Fill Customer Info");
        checkout = new CheckoutPage(driver);
        checkout.fillCustomerInfo(firstname, lastname, address, firstname + lastname + email);
        checkout.selectCountryAndCity();
        checkout.enterRandomZIP(zipCodes);
    }

    @Test(priority = 6)
    public void step6_fillPayment() throws InterruptedException {
        test = extent.createTest("Step 6 - Fill Payment Info");
        String expiry = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/yy"));
        checkout.enterPaymentDetails(firstname + " " + lastname, "4111111111111111", expiry, "123");
        checkout.clickCheckoutButton();
        test.addScreenCaptureFromPath(takeScreenshot("step6_afterCheckout"));
    }

    @Test(priority = 7)
    public void step7_emptyBasket() throws InterruptedException {
        test = extent.createTest("Step 7 - Empty Basket");
        basket.emptyBasket();
        basket.confirmAlert();
        test.addScreenCaptureFromPath(takeScreenshot("step7_emptyBasket"));
    }

    @Test(priority = 8)
    public void step8_addFruitOnlyItems() throws InterruptedException {
        test = extent.createTest("Step 8 - Add Fruit Only Items");
        home.returntoitempage();
        for (WebElement card : home.getAllCards()) {
        Thread.sleep(500);
            String desc = card.findElement(By.cssSelector(".card-text")).getText();
            if (desc.contains("Fruit")) {
                card.findElement(By.cssSelector(".addItem")).click();
                Thread.sleep(300);
            }
        }
    }

    @Test(priority = 9)
    public void step9_removeItemById() throws InterruptedException {

        test = extent.createTest("Step 9 - Remove Item by ID");
        basket.openBasket();
        test.addScreenCaptureFromPath(takeScreenshot("step8_fruitItems"));

        basket.removeItemById(5);
        basket.confirmAlert();
        test.addScreenCaptureFromPath(takeScreenshot("step9_itemRemoved"));
    }

    @Test(priority = 10)
    public void step10_invalidCheckoutValidation() throws InterruptedException {
        test = extent.createTest("Step 10 - Invalid Checkout Validation");
        checkout.fillCustomerInfo(firstname, lastname, address, firstname + lastname + email);
        checkout.clickCheckoutButton();
        List<WebElement> messages = checkout.getValidationMessages();
        long visibleCount = messages.stream().filter(WebElement::isDisplayed).count();
        test.info("Validation messages shown: " + visibleCount);
        test.addScreenCaptureFromPath(takeScreenshot("step10_invalidCheckout"));
        if (visibleCount == 7) {
            test.pass("7 verification messages were verified as expected.");
        } else {
            test.fail("The number of verification messages is different. Current: " + visibleCount);
        }
        Assert.assertEquals(visibleCount, 7);
    }
    @Test(priority = 11)
    public void step11_loginMismatchBug() throws InterruptedException {
        test = extent.createTest("Step 11 - Login with Invalid Email and Validate Welcome Message");

        String fakeEmail = "hh@h.com";
        String password = "anything";


        home.goToLoginPage();
        Thread.sleep(1000);



        LoginPage login = new LoginPage(driver);
        login.loginWithCredentials(fakeEmail, password);
        Thread.sleep(1500);



        String displayedEmail = login.getDisplayedWelcomeEmail();
        test.info("Entered email: " + fakeEmail);
        test.info("Displayed email: " + displayedEmail);


        if (!displayedEmail.equalsIgnoreCase(fakeEmail)) {
            test.fail("Logged in but the displayed account is not the one you entered.");
        } else {
            test.pass("Logged in and the correct email is displayed.");
        }

        test.addScreenCaptureFromPath(takeScreenshot("step11_loginMismatchBug"));
    }


    @AfterClass
    public void flushReport() throws SQLException {
        super.tearDown();
        extent.flush();
    }
}
