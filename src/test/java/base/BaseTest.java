package base;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected Connection con;
    protected Statement stmt;

    @BeforeClass
    public void setUp() throws SQLException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sweetshop.vivrichards.co.uk/");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sweetshop", "root", "Gameover111");
    }

    @AfterClass
    public void tearDown() throws SQLException {
        if (stmt != null) stmt.close();
        if (con != null) con.close();       if (driver != null) driver.quit();
        
    }

    public String takeScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + name + ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
} 