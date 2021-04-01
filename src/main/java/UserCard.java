import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserCard {

    WebElement root;

    public UserCard(WebElement root) {
        this.root = root;
    }

    public String getName() {
        return root.findElement(By.xpath(".//div[@class='ellip']")).getText();
    }

    public List<WebElement> getMessageButton() {
        return root.findElements(By.xpath(".//div[@class='tico']"));
    }
}