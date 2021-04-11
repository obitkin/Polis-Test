import com.codeborne.selenide.*;
import org.openqa.selenium.WebElement;
import io.github.sukgu.*;
import java.util.List;

public class MessagePage {
    // Функция поиска одного элемента по заданному локатору в теневом DOM
    public WebElement getWebElementFromShadowDom(String findLocator) {
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        return shadow.findElementByXPath(findLocator);
    }

    // Функция поиска всех элементов по заданному локатору в теневом DOM
    public List<WebElement> getWebElementsFromShadowDom(String findLocator) {
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        return shadow.findElementsByXPath(findLocator);
    }
}
