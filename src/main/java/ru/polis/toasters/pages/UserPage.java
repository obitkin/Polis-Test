package ru.polis.toasters.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byXpath;

public class UserPage {

    private final ElementsCollection toolbar = $$(byXpath("//ul[@class=\"toolbar_nav\"]/li"));

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
    public SelenideElement logOut(String xpath) {
        return $(byXpath(xpath));
    }
}