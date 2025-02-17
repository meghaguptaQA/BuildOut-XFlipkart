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
        List<WebElement> SearchRes=driver.findElements(By.xpath("//span[contains(@id,'productRating')]/div"));
        int count=0;
        for(WebElement res:SearchRes)
        {   Float i=Float.parseFloat(res.getText().toString());
            System.out.println(i);
             if (i<=4.0) 
             {
                count++;
             }
        }
        System.out.println("Count of items with rating less than or equal to 4 stars is: "+count);
        SearchRes=driver.findElements(By.xpath("//span[contains(@id,'productRating')]/div[text()<='4']"));
        System.out.println("Count of items with rating less than or equal to 4 stars is: "+SearchRes.size());                
        System.out.println("end Test case: testCase01");
        
    }
   
    @Test
    @Parameters({ "ProductName2" })
    public void testCase02(@Optional("testUser") String ProductName2) throws InterruptedException 
     {
        System.out.println("Start Test case: testCase02");
        //Open the URL to Flipkart home page
        Wrappers.openURL(driver,"https://www.flipkart.com/");
        //String url=Wrappers.GetCurrentURL(driver);
        Thread.sleep(3000);
        //Search for product IPhone
        Wrappers.SearchForProduct(driver, ProductName2);
        Thread.sleep(3000);

        //Get the search results with discount% greater than 17
        List<WebElement> SearchRes=driver.findElements(By.xpath("//div[@class='yKfJKb row']"));
        int count=0;
        for(WebElement res:SearchRes)
        {   //div[@class='UkUFwK']//span     
            WebElement discnt =res.findElement(By.xpath("./div[2]//span"))  ;    
            String i=discnt.getText().toString();
           // System.out.println("I is:"+i);
            String[] arrStr=i.split("%");
            Integer discount=Integer.parseInt(arrStr[0]);
            //System.out.println("Discount is: "+discount);
             if (discount>17) 
             {

                count++;
                WebElement title=res.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                System.out.println(title.getText().toString()+" : Discount is - "+discount+"%");
             }
        }
        if(count==0)
        {
            System.out.println("No Iphone with more than 17% discount.");
        }
                     
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
        //div[text()='4★ & above']//preceding-sibling::div
        
        WebElement checkbox4Star=driver.findElement(By.xpath("//div[contains(text(),'"+txt4starabove+"') and contains(text(),'above')]//preceding-sibling::div"));
        Wrappers.btn_click(checkbox4Star);
        Thread.sleep(9000);
        //Get the search results with discount% greater than 17
        List<WebElement> SearchRes=driver.findElements(By.xpath("//div[@class='slAVV4']//div/span[2]"));
       // int count=SearchRes.size();
       // System.out.println("");
        //System.out.println("Count is :"+count);
        //System.out.println("");
        HashMap<Integer,String> map=new HashMap<>();
        //count=0;
        for(WebElement res:SearchRes)
        {   
            //Thread.sleep(2000);
            String i=res.getText().toString();
            WebElement Url=Wrappers.FindElement(res, "./ancestor::div[@class='slAVV4']//img[@class='DByuf4']");
            WebElement title=Wrappers.FindElement(res, "./ancestor::div[@class='slAVV4']//a[2]");
            String URL=Url.getAttribute("src").toString();
            String Title=title.getAttribute("title").toString();
             //System.out.println("I is:"+i);
             //i=i.replaceAll([^0-9],"");
             i=i.replace("(", "");
             i=i.replace(")", "");
             i=i.replace(",", "");
             Integer reviews=Integer.parseInt(i);
             //System.out.println("no of reviews are "+reviews);
             
             //Thread.sleep(2000);
             
             //System.out.println("URL is "+URL);
             
             //System.out.println("Title is "+Title);
             map.put(reviews, "Title: "+Title+", Image URL: "+URL);
             //count++;
             //System.out.println("Count increment :"+count);
        }
        ArrayList<Integer> sortedKeys  = new ArrayList<Integer>(map.keySet());
            //      Collections.sort(userReviewCountList,Collections.reverseOrder());   [^\\d]
        //Collections.sort(sortedKeys,Collections.reverseOrder());
        //Collections.reverseOrder(sortedKeys);
        Collections.sort(sortedKeys);
        int size=sortedKeys.size();
        System.out.println("Title and Image URL for top 5 reviews are :");
        for(int l=size-1;l>(size-6);l--)
        {
            //System.out.println("Key is: "+sortedKeys.get(l));
            System.out.println("Reviews:"+sortedKeys.get(l)+"  ,  "+map.get(sortedKeys.get(l)));
        }
        // for(Map.Entry<Integer,String> entry : map.entrySet())
        // {
        //         System.out.println("KEY :"+entry.getKey()+"  Title and URL :"+entry.getValue());
                
        // }
                     
        System.out.println("end Test case: testCase03");
        
    }
}