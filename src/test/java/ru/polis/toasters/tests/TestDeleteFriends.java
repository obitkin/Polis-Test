package ru.polis.toasters.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import ru.polis.toasters.data.TestFriendsData;
import ru.polis.toasters.pages.AddFriendPage;
import ru.polis.toasters.pages.FriendsPage;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.FeedPage;
import ru.polis.toasters.util.UserData;

import java.util.List;

import static com.codeborne.selenide.Selenide.closeWindow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeleteFriends implements TestFriendsData {
    static LoginPage login;
    static UserData user1;
    static UserData user2;

    @Step("Открытие страницы логина и создание загрузка данных пользователей")
    @org.junit.jupiter.api.BeforeAll
    public static void Start() {
        Configuration.headless = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        login = new LoginPage();
        user1 = new UserData(TestFriendsData.user1, TestFriendsData.userID1, TestFriendsData.password1);
        user2 = new UserData(TestFriendsData.user2, TestFriendsData.userID2, TestFriendsData.password2);
    }

    @org.junit.jupiter.api.BeforeEach
    public void StartEach() {
        addIfNotFriends(user1, user2);
    }

    // Тест на удаления друга
    @Step("Удаление из друзей")
    @org.junit.jupiter.api.Test
    public void TestFriendsDelete() {
        // Логинимся первым ботом
        FeedPage p1 = login.loginMe(user1.user, user1.password);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends1 = friend1.findFriends();
        // Находим второго бота среди друзей
        friend1.findFriend(friends1, user2.userName);
        // Кликаем по кнопке с дополнительной информацией о друге
        friend1.clickAddInfo();
        // Удаляем друга
        friend1.clickDeleteFriend();
        // Переходим в "Друзья" в Toolbar
        friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        friends1 = friend1.findFriends();
        // Проверяем, что друга с таким именем уже нет у бота в друзьях
        // Количество совпадений должно быть ровно 0
        assertEquals(0, friend1.countFriend(friends1, user2.userName));
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();


        // Логинимся вторым ботом
        FeedPage p2 = login.loginMe(user2.user, user2.password);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend2 = p2.getToolbars().goToFriends();
        // Заходим в "Подписки"
        friend2.goToFriendSubscriptions();
        // Ожидаем загрузки вкладки "Подписки"
        friend2.waitSubs();
        // Получаем список имен подписок
        List<SelenideElement> mySubs = friend2.getSubs();
        // Заходим на страницу к выбранному подписчику
        friend2.getSub(mySubs, user1.userName);
        // Находим кнопку с дополнительной информацией о друге
        friend2.clickAddInfo();
        // Удаляем подписчика
        friend2.clickDeleteSub();
        // Переходим в "Друзья" в Toolbar
        friend2 = p2.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends2 = friend2.findFriends();
        // Проверяем, что у друга с таким именем уже нет у бота в друзьях
        // Количество совпадений должно быть ровно 0
        assertEquals(0, friend2.countFriend(friends2, user1.userName));
        // Заходим в "Подписки"
        friend2.goToFriendSubscriptions();
        // Ожидаем загрузки вкладки "Подписки"
        friend2.waitSubs();
        // Получаем список имен подписок
        mySubs = friend2.getSubs();
        // Считаем количество людей с именем первого бота
        // Количество совпадений должно быть ровно 0
        assertEquals(0, friend2.countFriend(mySubs, user1.userName));
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();
    }

    @Step("Закрываем браузер")
    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }

    // Добавляет людей в друзья, если они еще не в друзьях друг у друга
    @Step("Добавление {user1.userName} в друзья {user2.userName} если не друзья")
    public static void addIfNotFriends(UserData user1, UserData user2) {
        // Логинимся первым ботом
        FeedPage p1 = login.loginMe(user1.user, user1.password);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend1 = p1.getToolbars().goToFriends();
        // Заходим на страницу добавления нового друга
        AddFriendPage addNewFriend1 = friend1.goToFriendsAdd();
        // Добавляем нового друга
        addNewFriend1.addNewFriend(user2.userName);
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();
        // Логинимся вторым ботом
        FeedPage p2 = login.loginMe(user2.user, user2.password);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend2 = p2.getToolbars().goToFriends();
        // Переходим в раздел "Входящие заявки в друзья"
        friend2.goToFriendRequests();
        if (friend2.subsExist()) {
            // Ожидаем появления заявок в друзья
            friend2.waitAccept();
            // Нажимаем на кнопку "Принять заявку в друзья"
            friend2.acceptClick();
        }
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();
    }
}