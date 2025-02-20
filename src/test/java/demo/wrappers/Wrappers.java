package demo.wrappers;

import demo.TestCases;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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

    public static int GetCount4StarandBelow(RemoteWebDriver driver)
        {
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
            return count;
        }
        public static void PrintDiscountMoreThanXpercent(RemoteWebDriver driver,String Discount)
        {
            List<WebElement> SearchRes=driver.findElements(By.xpath("//div[@class='yKfJKb row']"));
            int count=0;
            for(WebElement res:SearchRes)
            {      
                WebElement discnt =res.findElement(By.xpath("./div[2]//span"))  ;    
                String txtDisc=discnt.getText().toString();
                String regex="[^0-9]";
                txtDisc=txtDisc.replaceAll(regex,"");
    
                Integer Discnt=Integer.parseInt(txtDisc);
                 if (Discnt>17) 
                 {
                    count++;
                    WebElement title=res.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                    System.out.println(title.getText().toString()+" : Discount is - "+Discnt+"%");
                 }
            }
            if(count==0)
            {
                System.out.println("No Iphone with more than 17% discount.");
            }
                     
        }
        public static void PrintImageAndURLWithHighestReviews(RemoteWebDriver driver,Integer Count)
        {
            List<WebElement> SearchRes=driver.findElements(By.xpath("//div[@class='slAVV4']//div/span[2]"));
            HashMap<Integer,String> map=new HashMap<>();
            for(WebElement res:SearchRes)
            {   
                String textreview=res.getText().toString();
                WebElement Url=Wrappers.FindElement(res, "./ancestor::div[@class='slAVV4']//img[@class='DByuf4']");
                WebElement title=Wrappers.FindElement(res, "./ancestor::div[@class='slAVV4']//a[2]");
                String URL=Url.getAttribute("src").toString();
                String Title=title.getAttribute("title").toString();
                String regex="[^0-9]";
                textreview=textreview.replaceAll(regex,"");
                Integer reviews=Integer.parseInt(textreview);
                map.put(reviews, "Title: "+Title+", Image URL: "+URL);

            }
            ArrayList<Integer> sortedKeys  = new ArrayList<Integer>(map.keySet());
            Collections.sort(sortedKeys,Collections.reverseOrder()); //  [^\\d]
            System.out.println("Title and Image URL for top 5 reviews are :");
            for(int l=0;l<Count;l++)
            {           
                System.out.println("Reviews:"+sortedKeys.get(l)+"  ,  "+map.get(sortedKeys.get(l)));
            }
                     
        }
}
