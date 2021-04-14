package ru.polis.toasters.elements;

import com.codeborne.selenide.ElementsCollection;
import ru.polis.toasters.pages.GuestPage;
import ru.polis.toasters.pages.MessagePage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;

public class Toolbar {

    private final ElementsCollection root = $$(byXpath(".//ul[@class=\"toolbar_nav\"]/li"));

    public ElementsCollection getToolbar() {
        return root;
    }

    public GuestPage goToGuest() {
        //Надо исправить клик с 4 на String
        getToolbar().get(4).click();
        return new GuestPage();
    }

    // Функция поиска кнопки "Сообщения" в Toolbar-е
    public MessagePage goToMessage() {
        getToolbar().get(0).click();
        return new MessagePage();
    }
}
