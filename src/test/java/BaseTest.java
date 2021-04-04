import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    WebDriver driver;

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void quit() {
        driver.quit();
    }
}
