package ru.polis.toasters.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byXpath;

// PageObject для страницы "Друзья"
public class FriendsPage {

    // Выполняет переход на страницу "Добавить друга"
    public AddFriendPage goToFriendsAdd() {
        String friendAddLocator = ".//a[@title='Найти по имени и фамилии']";
        $(byXpath(friendAddLocator)).click();
        return new AddFriendPage();
    }

    // Ищет вкладку "Входящие запросы в друзья"
    public SelenideElement goToFriendRequests(String friendRequests) {
        return $(byXpath(friendRequests));
    }

    // Ищет кнопку "Принять заявку в друзья"
    public SelenideElement acceptClick(String acceptButton) {
        return $(byXpath(acceptButton));
    }

    // Ищет всех друзей данного пользователя
    public ElementsCollection findFriends(String allFriends) {
        return $$(byXpath(allFriends));
    }

    // Ищет вкладку "Подписки"
    public SelenideElement goToFriendSubscriptions(String friendSubscriptions) { return $(byXpath(friendSubscriptions));}
}
