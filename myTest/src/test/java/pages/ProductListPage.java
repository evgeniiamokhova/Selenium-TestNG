package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class ProductListPage {
    WebDriver driver;

    public ProductListPage(WebDriver driver) {
        this.driver=driver;
    }

    public void clickLocation() {
        WebElement locationButton = driver.findElement(By.xpath("//div[contains(@class, 'header-top__inner')]/button[contains(@class, 'location')]"));
        locationButton.click();
    }

    public void setCity(String city) {
        WebElement cityInput = driver.findElement(By.xpath("//input[@type='text']"));
        cityInput.click();
        cityInput.sendKeys(city);
    }

    public void selectCity(String city) {
        WebElement cityItem = driver.findElement(By.xpath("//div//li/div[text()='"+city +"']"));
        cityItem.click();
        WebElement applyButton = driver.findElement(By.xpath("//button[contains(@class, 'location-select')]"));
        applyButton.click();
        WebElement explicitWait = (new WebDriverWait(driver, 50))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-top__inner')]/button[contains(@class, 'location')]")));
    }

    public String getLocation() {
        WebElement locationButton = driver.findElement(By.xpath("//button[contains(@class, 'location')]"));
        return locationButton.getText();
    }

    public void setSearchProduct(String product) {
        WebElement inputSearch = driver.findElement(By.name("search"));
        inputSearch.click();
        inputSearch.sendKeys(product);
        WebElement searchButton = driver.findElement(By.xpath("//div[2]/form/button[contains(@class, 'search__btn')]"));
        searchButton.click();
    }

    public String getFirstProduct(String product) {
        try {
            List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'product-card__title')]/a[contains(text(), '"+product+"')]"));
            return products.get(0).getText();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'product-card__title')]/a[contains(text(), '"+product+"')]"));
            return products.get(0).getText();
        }
    }

    public void openCatalog() { //hover actions.moveToElement(videoItem).perform()
        WebElement catalogButton = driver.findElement(By.xpath("//button[contains(@class, 'catalog__button')]"));
        catalogButton.click();
    }

    public void selectProductFromCatalog(String product) {
        WebElement catalogItem = driver.findElement(By.xpath("//div[text()='"+product+"']/ancestor::a[contains(@class, 'catalog-item__inner')]"));
        catalogItem.click();
    }

    public void setFilter(String filter) {
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        jsx.executeScript("window.scrollBy(0,450)", "");
        WebElement explicitWait = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'catalog-filter')]//span[text()='"+filter+"']/preceding-sibling::input")));
        WebElement checkbox = driver.findElement(By.xpath("//div[contains(@class,'catalog-filter')]//span[text()='"+filter+"']/preceding-sibling::input"));
         checkbox.click();
    }

    public void clickFirstProduct(String product) {

        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'catalog-page__products')]//a[contains(text(),'"+product+"')]/ancestor::div[contains(@class, 'product-card__inner')]/a"));
        WebElement explicitWait = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'catalog-page__products')]//a[contains(text(),'"+product+"')]/ancestor::div[contains(@class, 'product-card__inner')]/a")));
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
            products.get(0).click();
        } catch (TimeoutException e) {
            System.out.println("WebDriver found that this element was not clickable.");
        }
    }

    public String getRecommendationProducts(int index) {
        List<WebElement> recommendationProducts = driver.findElements(By.xpath("//h2[text()='Рекомендуем вам']/following-sibling::div//a[contains(@class, 'product-card__title')]"));
        return recommendationProducts.get(index).getText();
    }

    public void addFirstRecommendationToCart() {
        List<WebElement> recommendationProducts = driver.findElements(By.xpath("//h2[text()='Рекомендуем вам']/following-sibling::div//button[contains(@class, 'cart-control__button')]"));
        WebElement explicitWait = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Рекомендуем вам']/following-sibling::div//button[contains(@class, 'cart-control__button')]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(recommendationProducts.get(0));
        actions.perform();
        WebElement cookieButton = driver.findElement(By.xpath("//button[contains(@class, 'cookie-button')]"));
        cookieButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(recommendationProducts.get(0)));
            recommendationProducts.get(0).click();
        } catch (TimeoutException e) {
            System.out.println("WebDriver found that this element was not clickable.");
        }

    }

    public void clickCart() {
        WebElement cartButton = driver.findElement(By.xpath("//button[contains(@class,'cart-button')]"));
        cartButton.click();
    }

    public void closeCart() {
        WebElement cartButton = driver.findElement(By.xpath("//button[contains(@class, 'mini-cart__button')]"));
        cartButton.click();
    }

    public String getCartItem() {
        WebElement cartItem = driver.findElement(By.xpath("//div[contains(@class,'mini-cart-product__body')]/a"));
        return cartItem.getText();
    }

    public void slideRecommendation(){
        WebElement slider = driver.findElement(By.xpath("//div[contains(@class,'recommendation-block_main')]/div[contains(@class,'slider')]"));
        List<WebElement> recommendationProducts = driver.findElements(By.xpath("//h2[text()='Рекомендуем вам']/following-sibling::div//a[contains(@class, 'product-card__title')]"));
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(recommendationProducts.get(3)).moveByOffset(-400,0).release().build();
        dragAndDrop.perform();
    }

}
