import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

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
}