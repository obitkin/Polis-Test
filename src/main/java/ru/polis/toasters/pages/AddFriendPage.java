package ru.polis.toasters.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byXpath;

// PageObject для страницы "Добавить друга"
public class AddFriendPage {
    // Toolbar для всех основных разделов сайта
    private final ElementsCollection toolbar = $$(byXpath("//ul[@class=\"toolbar_nav\"]/li"));

    // Получает объект toolbar
    private ElementsCollection getToolbars() {
        return toolbar;
    }

    // Получает поле ввода поиска нового друга
    public SelenideElement getNewFriendField(String addNewFriendField) {
        return $(byXpath(addNewFriendField));
    }

    // Получает кнопку "Найти друга"
    public SelenideElement getFindButton(String findButton) {
        return $(byXpath(findButton));
    }

    // Получает первого возможного друга из выдачи поиска
    public SelenideElement getFirstPerson(String filteredPeopleList) {
        return $$(byXpath(filteredPeopleList)).first();
    }

    // Переход на страницу "Друзья"
    public FriendsPage goToFriends() {
        getToolbars().get(3).click();
        return new FriendsPage();
    }
}
