import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.ProductListPage;
import pages.ProductPage;
import org.testng.annotations.Test;

public class ChromeDriverTest extends TestBase{

    @Test
    public void verifyChangingLocation() {
        ProductListPage productList = PageFactory.initElements(driver, ProductListPage.class);
        productList.clickLocation();
        productList.setCity("Абакан");
        productList.selectCity("Абакан");
        Assert.assertEquals(productList.getLocation(),"Абакан", "Location is not changed");
    }

    @Test
    public void verifySearchingProductByName() {
        String product = "вафли";
        ProductListPage productList = PageFactory.initElements(driver, ProductListPage.class);
        productList.setSearchProduct(product);
        String resultOfSearching = productList.getFirstProduct(product);
        Assert.assertEquals(true, resultOfSearching.contains(product), "Product is not found");
    }

    @Test
    public void verifyOpeningProductViaCatalog() {
        String product = "Чай";
        ProductListPage productList = PageFactory.initElements(driver, ProductListPage.class);
        ProductPage productItem = PageFactory.initElements(driver, ProductPage.class);
        productList.openCatalog();
        productList.selectProductFromCatalog(product);
        productList.clickFirstProduct("чай");
        String selectedProduct = productItem.getProductTitle();
        Assert.assertEquals(true, selectedProduct.contains("чай"), "Wrong product is selected");
        productItem.goToProductList();
    }


    @Test
    public void verifyAddingProductInCart() {
        ProductListPage productList = PageFactory.initElements(driver, ProductListPage.class);
        String recommendationProduct = productList.getRecommendationProducts(0);
        productList.addFirstRecommendationToCart();
        productList.clickCart();
        Assert.assertEquals(productList.getCartItem(),recommendationProduct, "Recommendation product is not added in cart");
        productList.closeCart();
    }

    @Test
    public void verifySlidingRecommendations() {
        ProductListPage productList = PageFactory.initElements(driver, ProductListPage.class);
        String firstRecommendationProduct = productList.getRecommendationProducts(0);
        productList.slideRecommendation();
        Assert.assertNotEquals(firstRecommendationProduct, productList.getRecommendationProducts(5));
    }

}
