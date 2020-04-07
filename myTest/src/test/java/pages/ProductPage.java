package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver=driver;
    }

    public String getProductTitle() {
        WebElement productTitle = driver.findElement(By.xpath("//h1"));
        return productTitle.getText();
    }

    public void goToProductList() {
        WebElement logo = driver.findElement(By.xpath("//div[contains(@class, 'header-desktop__logo')]/a"));
        logo.click();
    }

}
