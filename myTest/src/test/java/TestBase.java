import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver = null;

    @BeforeSuite
    public void initialize() throws IOException{
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://kdvonline.ru/");
    }

    @AfterSuite
    public void tearDownTest() {

            driver.quit();

    }

}
