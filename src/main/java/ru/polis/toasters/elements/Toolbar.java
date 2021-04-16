package ru.polis.toasters.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.polis.toasters.pages.FriendsPage;
import ru.polis.toasters.pages.GuestPage;
import ru.polis.toasters.pages.MessagePage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Toolbar {

    private final SelenideElement root = $(byXpath(".//ul[@class=\"toolbar_nav\"]"));

    private final SelenideElement guests = root.$(byXpath("./li[@data-l=\"t,guests\"]"));

    private final SelenideElement message = root.$(byXpath("./li[@data-l=\"t,messages\"]"));

    private final SelenideElement friends = root.$(byXpath("./li[@data-l=\"t,friends\"]"));

    public int getGuestCounter() {
        return guests.$$(By.className("toolbar_nav_notif")).size();
    }

    public SelenideElement getToolbar() {
        return root;
    }

    public GuestPage goToGuest() {
        guests.click();
        return new GuestPage();
    }

    // Функция поиска кнопки "Сообщения" в Toolbar-е
    public MessagePage goToMessage() {
        message.click();
        return new MessagePage();
    }

    // Функция поиска кнопки выхода из аккаунта
    public SelenideElement logOut(String xpath) {
        return $(byXpath(xpath));
    }

    // Функция поиска кнопки "Друзья" в Toolbar-е
    public FriendsPage goToFriends() {
        friends.click();
        return new FriendsPage();
    }
}
