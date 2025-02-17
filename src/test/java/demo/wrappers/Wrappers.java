package demo.wrappers;

import demo.TestCases;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    /*
     * Write your selenium wrappers here
     */
    public static void openURL(WebDriver driver,String browserURL) {

		System.out.println("Maximize the window");
		driver.manage().window().maximize();

		System.out.println("Opening Website -->" + browserURL);
		driver.get(browserURL);
	}
    public static String GetCurrentURL(WebDriver driver)
    {   
        try {       
             String url=driver.getCurrentUrl();
             return url;
        
        } catch (Exception e) {
            e.printStackTrace();
        }
                return null;

    }
    public static void enter_Value(WebElement element,String attributeValue)
    {
        try 
        {
            element.clear();
            element.sendKeys(attributeValue);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public static void btn_click(WebElement element)
    {
        try 
        {
            element.click();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    public static WebElement FindElement(WebElement res,String xpath)
    {
        try 
        {
            WebElement element=res.findElement(By.xpath(xpath));
            return element;
        } catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }

    }
    public static String date_decrmenter(LocalDate todaysdate,Integer days )
    {
        
        LocalDate date7daysbefore=todaysdate.minusDays(7);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        String datetoenter = date7daysbefore.format(format);
        return datetoenter;
    }
    public static void SearchForProduct(WebDriver driver,String product)
    {
        WebElement txtboxSearch=driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
        Wrappers.enter_Value(txtboxSearch,product+Keys.ENTER);

    }

}
