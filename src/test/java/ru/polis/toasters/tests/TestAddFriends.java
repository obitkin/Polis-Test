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

public class TestAddFriends implements TestFriendsData {
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
        deleteIfFriends(user1, user2);
    }

    // Тест на добавление друга
    @Step("Добавление в друзья")
    @org.junit.jupiter.api.Test
    public void TestFriendsAdd() {
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
        // Ожидаем появления заявок в друзья
        friend2.waitAccept();
        // Нажимаем на кнопку "Принять заявку в друзья"
        friend2.acceptClick();
        // Переходим на страницу "Друзья"
        friend2 = p2.getToolbars().goToFriends();
        // Получаем список всех друзей
        List<SelenideElement> friends2 = friend2.findFriends();
        // Подсчитаем друзей, которые совпадают по имени с первым ботом
        // Количество совпадений должно быть ровно 1
        assertEquals(1, friend2.countFriend(friends2, user1.userName));
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();


        // Логинимся первым ботом
        p1 = login.loginMe(user1.user, user1.password);
        // Переходим в "Друзья" в Toolbar
        friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends1 = friend1.findFriends();
        // Подсчитаем друзей, которые совпадают по имени со вторым ботом
        // Количество совпадений должно быть ровно 1
        assertEquals(1, friend1.countFriend(friends1, user2.userName));
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();
    }

    @org.junit.jupiter.api.AfterEach
    public void StopEach() {
        deleteIfFriends(user1, user2);
    }

    @Step("Закрываем браузер")
    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }

    // Проверяет, что заданные люди не в друзьях друг у друга
    @Step("Удаление {user1.userName} из друзей {user2.userName} если друзья")
    public static void deleteIfFriends(UserData user1, UserData user2) {
        // Логинимся первым ботом
        FeedPage p1 = login.loginMe(user1.user, user1.password);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends1 = friend1.findFriends();
        if (friends1.size() != 0)
        {
            // Находим второго бота среди друзей
            if (friend1.findFriend(friends1, user2.userName) != 0)
            {
                // Кликаем по кнопке с дополнительной информацией о друге
                friend1.clickAddInfo();
                // Удаляем друга
                friend1.clickDeleteFriend();
            }
        }
        else {
            // Переходим в раздел "Входящие заявки в друзья"
            friend1.goToFriendRequests();
            if (friend1.subsExist()) {
                // Ожидаем появления заявок в друзья
                friend1.waitAccept();
                // Нажимаем на кнопку "Отменить заявку в друзья"
                friend1.declineClick();
            }
            else {
                // Заходим в "Исходящие заявки в друзья"
                friend1.goToFriendSubscriptions();
                // Ожидаем загрузки вкладки "Подписки"
                friend1.waitSubs();
                // Получаем список имен подписок
                List<SelenideElement> mySubs1 = friend1.getSubs();
                if (mySubs1.size() != 0)
                {
                    // Заходим на страницу к выбранному подписчику
                    if (friend1.getSub(mySubs1, user2.userName) != 0)
                    {
                        // Находим кнопку с дополнительной информацией о друге
                        friend1.clickAddInfo();
                        // Удаляем подписчика
                        friend1.clickDeleteSub();
                    }
                }
            }
        }
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
        List<SelenideElement> mySubs2 = friend2.getSubs();
        if (mySubs2.size() != 0)
        {
            // Заходим на страницу к выбранному подписчику
            if (friend2.getSub(mySubs2, user1.userName) != 0)
            {
                // Находим кнопку с дополнительной информацией о друге
                friend2.clickAddInfo();
                // Удаляем подписчика
                friend2.clickDeleteSub();
            }
        }
        else {
            // Переходим в раздел "Входящие заявки в друзья"
            friend2.goToFriendRequests();
            // Ожидаем появления заявок в друзья
            friend2.waitAccept();
            // Нажимаем на кнопку "Отменить заявку в друзья"
            friend2.declineClick();
        }
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();
    }
}