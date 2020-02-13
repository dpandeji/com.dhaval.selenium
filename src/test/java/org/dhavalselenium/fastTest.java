package org.dhavalselenium;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Fail.fail;

public class fastTest extends hooks {


          @Test
    public void searchTest() {
              doSearch("puma");
        String url = driver.getCurrentUrl();
        assertThat(url, Matchers.endsWith("puma"));
    }
       //  2nd assertion
    @Test
    public void searchProduct(){
        doSearch("puma");
        List<WebElement> productWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        for (WebElement indProduct : productWebElements) {
            String actual = indProduct.getText();
            assertThat(actual, Matchers.is(Matchers.containsString("Puma")));
        }
        String actualTitle = driver.findElement(By.className("search-title__term")).getText();
        assertThat(actualTitle, Matchers.is(Matchers.equalToIgnoringCase("puma")));

         driver.findElement(By.id("searchTerm")).sendKeys("mobile");
        driver.findElement(By.id("searchTerm")).sendKeys(Keys.ENTER);
        }

    @Test
    public void basketTest() {
        doSearch("nike");
        List<WebElement> productWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        if (productWebElements.size() == 0) {fail("No Products found with: " + "nike");}
        // TODO: 2020-02-08 this will be converted in future
        Random random = new Random();
        int randomNumber = random.nextInt(productWebElements.size() - 1);
        WebElement selectedElement = productWebElements.get(randomNumber);
        String selectedProductName = selectedElement.getText();
        selectedElement.click();
        addtoBasket();
        time();
        gotoBasket();
        basketedProduct();
        String actual =basketedProduct();
        assertThat(actual, Matchers.is(Matchers.equalToIgnoringCase(selectedProductName)));
    }


// Search , add to basket, continue shopping , add another product to basket and verify basket got 2 products
    @Test
    public void nextItem(){
        doSearch("puma");
        List<WebElement> searchList= driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
                Random random1=new Random();
                int randomSelect1= random1.nextInt(searchList.size()-1);
                WebElement listedProduct1= searchList.get(randomSelect1);
                String selectedProduct1=listedProduct1.getText();
                listedProduct1.click();
                time();
                addtoBasket();
                time();
                continueShopping();
                doSearch("watch");
                searchList= driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
                time();
                Random random2=new Random();
                int randomSelect2= random2.nextInt(searchList.size()-1);
                WebElement listedProduct2= searchList.get(randomSelect2);
                String selectedProduct2=listedProduct2.getText();
                listedProduct2.click();
                time();
                addtoBasket();
                time();
                gotoBasket();
                time();
               List<WebElement> trollyContent= driver.findElements(By.cssSelector(".xs-12--none.md-6--none.lg-7--none.undefined"));
               String productNumber= String.valueOf(trollyContent.size()+1);
               assertThat(productNumber, Matchers.is("2"));
    }
        // Search product, add product to basket, increase quantity to more than 1 and verify that price got updated according to quantity

        @Test
        public void qtyPrice() {
            doSearch("puma");
            List<WebElement> searchList = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
            Random random3 = new Random();
            int randomSelect3 = random3.nextInt(searchList.size() - 1);
            WebElement listedProduct3 = searchList.get(randomSelect3);
            String selectedProduct3 = listedProduct3.getText();
            listedProduct3.click();
            time();
            addtoBasket();
            gotoBasket();
            time();
            selectedQty("2"); //amend qty to verify
            int qty;
            qty = selectedQty("2"); //amend qty to verify
            pricePerqty();
            int price = pricePerqty();
            int actualPrice = price * qty;
            WebElement expectedPrice = driver.findElement(By.cssSelector("span[data-e2e='product-line-price']"));
            // change of qty reflect the price.to assert this refactor it.
            assertThat1(actualPrice, (equals(expectedPrice))); //


        }
         private void assertThat1(Integer actualPrice, boolean equals){

         }
         private void assertThat(String actualPrice, Matcher<String> equals) {
        }
    }

