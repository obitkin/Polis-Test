import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class UserPage {

    private ElementsCollection toolbar = $$(By.xpath("//ul[@class=\"toolbar_nav\"]/li"));

    private ElementsCollection getToolbars() {
        return toolbar;
    }

    public GuestPage goToGuest() {
        //Надо исправить клик с 4 на String
        getToolbars().get(4).click();
        return new GuestPage();
    }

    // Функция поиска кнопки "Сообщения" в Toolbar-е
    public MessagePage goToMessage() {
        getToolbars().get(0).click();
        return new MessagePage();
    }

    // Функция поиска кнопки выхода из аккаунта
    public WebElement logOut(String xpath) {
        return $(By.xpath(xpath));
    }
}