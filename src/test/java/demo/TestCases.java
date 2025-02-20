package demo;
import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

//import com.beust.jcommander.Parameters;

import java.sql.Wrapper;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;

    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");


        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        //driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
    @Test
    @Parameters({ "ProductName1" })
    public void testCase01(@Optional("testUser") String ProductName1) throws InterruptedException 
     {
        System.out.println("Start Test case: testCase01");
        //Open the URL to Flipkart home page
        Wrappers.openURL(driver,"https://www.flipkart.com/");
        //String url=Wrappers.GetCurrentURL(driver);
        Thread.sleep(3000);
        //Search for product Washing Machine
        Wrappers.SearchForProduct(driver, ProductName1);
        Thread.sleep(3000);
        //Sort the search results by populartity
        Duration timeout=Duration.ofMillis(10000);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Popularity']")));
        WebElement btnSortByPopularity=driver.findElement(By.xpath("//div[text()='Popularity']"));
        Wrappers.btn_click(btnSortByPopularity);
        Thread.sleep(5000);

        //Get the search results with rating 4 or less
        int count=Wrappers.GetCount4StarandBelow(driver);

        System.out.println("Count of items with rating less than or equal to 4 stars is: "+count);
        // SearchRes=driver.findElements(By.xpath("//span[contains(@id,'productRating')]/div[text()<='4']"));
        // System.out.println("Count of items with rating less than or equal to 4 stars is: "+SearchRes.size());                
        System.out.println("end Test case: testCase01");
        
    }
   
    @Test
    @Parameters({ "ProductName2","Discount" })
    public void testCase02(@Optional("testUser") String ProductName2,String Discount) throws InterruptedException 
     {
        System.out.println("Start Test case: testCase02");
        //Open the URL to Flipkart home page
        Wrappers.openURL(driver,"https://www.flipkart.com/");
        //String url=Wrappers.GetCurrentURL(driver);
        Thread.sleep(3000);
        //Search for product IPhone
        Wrappers.SearchForProduct(driver, ProductName2);
        Thread.sleep(3000);

        //Get and prin the search results with discount% greater than 17
          Wrappers.PrintDiscountMoreThanXpercent(driver,Discount);
                     
        System.out.println("end Test case: testCase02");
        
    }
    @Test
    @Parameters({ "ProductName3" })
    public void testCase03(@Optional("testUser") String ProductName3) throws InterruptedException 
     {
        System.out.println("Start Test case: testCase03");
        //Open the URL to Flipkart home page
        Wrappers.openURL(driver,"https://www.flipkart.com/");
        //String url=Wrappers.GetCurrentURL(driver);
        Thread.sleep(3000);
        //Search for product Coffee Mug
        Wrappers.SearchForProduct(driver, ProductName3);
        Thread.sleep(3000);
        //Click on checkbox 4* and ab0ve
        String txt4starabove="4";
        
        WebElement checkbox4Star=driver.findElement(By.xpath("//div[contains(text(),'"+txt4starabove+"') and contains(text(),'above')]//preceding-sibling::div"));
        Wrappers.btn_click(checkbox4Star);
        Thread.sleep(9000);
        //Get and print the search results highest reviews
        Wrappers.PrintImageAndURLWithHighestReviews(driver,5);
                     
        System.out.println("end Test case: testCase03");
        
    }
}
