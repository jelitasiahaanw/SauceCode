package StepDef;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static java.util.Collections.reverseOrder;

public class SortProductPricesDesc {
    @Test
    public void verifyPrice() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement p : beforeFilterPrice){
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("$","")));
        }
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText("Price (high to low)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        for (WebElement q :afterFilterPrice){
            afterFilterPriceList.add(Double.valueOf(q.getText().replace("$","")));
        }
        Collections.sort(beforeFilterPriceList,Collections.reverseOrder());

        Assert.assertEquals(beforeFilterPriceList,afterFilterPriceList);

        Thread.sleep(3000);
        driver.quit();

    }
}
