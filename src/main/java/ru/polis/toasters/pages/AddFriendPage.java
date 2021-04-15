package ru.polis.toasters.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.polis.toasters.elements.Toolbar;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

// PageObject для страницы "Добавить друга"
public class AddFriendPage {
    private final Toolbar toolbar = new Toolbar();

    public Toolbar getToolbars() {
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
        getToolbars().goToFriends();
        return new FriendsPage();
    }

    // Добавляет заданного пользователя в друзья
    public void addNewFriend(String user) {
        String addFriendField = ".//input[@type='text' and @placeholder='Введите имя или название']";
        SelenideElement addNewFriendField = getNewFriendField(addFriendField);
        executeJavaScript("arguments[0].click()", addNewFriendField);
        addNewFriendField.sendKeys(user);
        String findButton = ".//span[text()='Найти' and @class='content__0ej09']//..//..//span[contains(@class, 'button-core')]";
        $(byXpath(findButton)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        getFindButton(findButton).click();
        String waitResult = ".//div[contains(text(), 'Найден')]";
        $(byXpath(waitResult)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        String filteredPeopleList = ".//div[@class='row__px8cs skip-first-gap__m3nyy']";
        SelenideElement firstPerson = getFirstPerson(filteredPeopleList);
        firstPerson.click();
        String addButtonFriend = ".//span[@class='content__0ej09' and text()='Добавить в друзья']";
        firstPerson.$(byXpath(addButtonFriend)).click();
    }
}
