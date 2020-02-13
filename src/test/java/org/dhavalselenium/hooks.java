package org.dhavalselenium;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public class hooks {

    public static WebDriver driver;
    private java.lang.String browser="chrome";

    public void doSearch(String searchTerm) {
        driver.findElement( By.id( "searchTerm" ) ).sendKeys(searchTerm);
        // driver.findElement(By.id("searchTerm")).sendKeys( searchTer );
        driver.findElement(By.id("searchTerm")).sendKeys(Keys.ENTER);
    }
    public void addtoBasket() {
        driver.findElement(By.cssSelector("button[data-test='component-att-button']")).click();
    }
    public void time() {
        driver.manage().timeouts().implicitlyWait(8, SECONDS);
    }

    public void gotoBasket() {
        driver.findElement(By.cssSelector("a[data-test='component-att-button-basket']")).click();
    }
    public void continueShopping(){
        driver.findElement(By.cssSelector("button[data-test='component-att-button-continue']")).click();
    }
    public java.lang.String basketedProduct() {
        return driver.findElement(By.cssSelector(".ProductCard__content__9U9b1.xsHidden.lgFlex")).getText();
    }
    public int selectedQty(String s) {
        WebElement qty = driver.findElement( By.cssSelector( ".ProductCard__quantitySelect__2y1R3" ) );
        qty.sendKeys(s);
        return 0;
    }
    public int pricePerqty(){
        WebElement price= driver.findElement(By.cssSelector("span[data-e2e='product-unit-price']"));
        price.getText();
        return 0;
    }

    @Before
    public void setUp(){
        openBrowser();
        openUrl("https://www.argos.co.uk/");
        maxSize();
    }

      @After
    public void tearDown(){
        driver.quit();
    }

    public void openBrowser(){
        switch(browser){
            case"chrome":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver=new InternetExplorerDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();

        }
    }
    public void openUrl(java.lang.String url){
        driver.get(url);
    }
    public void maxSize(){
        driver.manage().window().maximize();
    }

}

