import com.codeborne.selenide.*;
import io.github.sukgu.*;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage {
    // Функция поиска одного элемента по заданному локатору в теневом DOM
    public SelenideElement getWebElementFromShadowDom(String findLocator) {
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        return $(shadow.findElementByXPath(findLocator));
    }

    // Функция поиска всех элементов по заданному локатору в теневом DOM
    public List<SelenideElement> getWebElementsFromShadowDom(String findLocator) {
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        return $$(shadow.findElementsByXPath(findLocator));
    }
}
