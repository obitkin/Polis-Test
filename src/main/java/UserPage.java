import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {

    WebDriver driver;

    public UserPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
}