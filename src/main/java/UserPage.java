import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserPage {

    WebDriver driver;

    public UserPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    private List<WebElement> getToolbars() {
        return toolbars;
    }

    public GuestPage goToGuest() {
        getToolbars().get(4).click();
        return new GuestPage(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @FindBy(xpath = "//ul[@class=\"toolbar_nav\"]/li")
    List<WebElement> toolbars;
}