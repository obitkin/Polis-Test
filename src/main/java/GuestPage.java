import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GuestPage {

    WebDriver driver;

    public GuestPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public List<UserCard> getGuestCard() {
        return portrets.stream().map(UserCard::new).collect(Collectors.toList());
    }

    @FindBy(xpath = "//ul[@class=\"toolbar_nav\"]/li")
    List<WebElement> toolbars;


    @FindBy(xpath = "//div[@class=\"portlet_b\"]/.//div[@class=\"user-grid-card\"]")
    List<WebElement> portrets;
}
