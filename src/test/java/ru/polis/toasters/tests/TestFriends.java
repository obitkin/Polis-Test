package ru.polis.toasters.tests;

import com.codeborne.selenide.*;
import ru.polis.toasters.data.TestFriendsData;
import ru.polis.toasters.pages.AddFriendPage;
import ru.polis.toasters.pages.FriendsPage;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.UserPage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.closeWindow;

public class TestFriends implements TestFriendsData {
    static LoginPage login;

    @org.junit.jupiter.api.BeforeAll
    public static void Start() {
        login = new LoginPage();
    }

    // Тест на добавление друга. Работает только в том случае, если два выбранных пользователя еще не дружат друг с другом
    // и не являются подписчиками друг у друга
    @org.junit.jupiter.api.Test
    public void TestFriendsAdd() {
        // Логинимся первым ботом
        UserPage p1 = login.loginMe(TestFriendsData.user1, TestFriendsData.password1);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend1 = p1.getToolbars().goToFriends();
        // Заходим на страницу добавления нового друга
        AddFriendPage addNewFriend1 = friend1.goToFriendsAdd();
        // Добавляем нового друга
        addNewFriend1.addNewFriend(TestFriendsData.userID2);
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();


        // Логинимся вторым ботом
        UserPage p2 = login.loginMe(TestFriendsData.user2, TestFriendsData.password2);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend2 = p2.getToolbars().goToFriends();
        // Переходим в раздел "Входящие заявки в друзья"
        friend2.goToFriendRequests(TestFriendsData.friendRequests);
        // Ожидаем появления заявок в друзья
        friend2.waitAccept();
        // Нажимаем на кнопку "Принять заявку в друзья"
        friend2.acceptClick(TestFriendsData.acceptButton);
        // Переходим на страницу "Друзья"
        friend2 = p2.getToolbars().goToFriends();
        // Получаем список всех друзей
        List<SelenideElement> friends2 = friend2.findFriends(TestFriendsData.allFriends);
        // Подсчитаем друзей, которые совпадают по имени с первым ботом
        // Количество совпадений должно быть ровно 1
        assertEquals(1, friend2.countFriend(friends2, TestFriendsData.userName1));
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();


        // Логинимся первым ботом
        p1 = login.loginMe(TestFriendsData.user1, TestFriendsData.password1);
        // Переходим в "Друзья" в Toolbar
        friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends1 = friend1.findFriends(TestFriendsData.allFriends);
        // Подсчитаем друзей, которые совпадают по имени со вторым ботом
        // Количество совпадений должно быть ровно 1
        assertEquals(1, friend1.countFriend(friends1, TestFriendsData.userName2));
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();
        // Закрываем окно
        closeWindow();
    }

    // Тест на удаления друга. Работает только в том случае, если два выбранных пользователя дружат друг с другом
    @org.junit.jupiter.api.Test
    public void TestFriendsDelete() {
        // Логинимся первым ботом
        UserPage p1 = login.loginMe(TestFriendsData.user1, TestFriendsData.password1);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends1 = friend1.findFriends(TestFriendsData.allFriends);
        // Находим второго бота среди друзей
        friend1.findFriend(friends1, TestFriendsData.userName2);
        // Кликаем по кнопке с дополнительной информацией о друге
        friend1.clickAddInfo();
        // Удаляем друга
        friend1.clickDeleteFriend();
        // Переходим в "Друзья" в Toolbar
        friend1 = p1.getToolbars().goToFriends();
        // Получаем список имен друзей
        friends1 = friend1.findFriends(TestFriendsData.allFriends);
        // Проверяем, что друга с таким именем уже нет у бота в друзьях
        // Количество совпадений должно быть ровно 0
        assertEquals(0, friend1.countFriend(friends1, TestFriendsData.userName2));
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();


        // Логинимся вторым ботом
        UserPage p2 = login.loginMe(TestFriendsData.user2, TestFriendsData.password2);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend2 = p2.getToolbars().goToFriends();
        // Заходим в "Подписки"
        friend2.goToFriendSubscriptions(friendSubscriptions);
        // Ожидаем загрузки вкладки "Подписки"
        friend2.waitSubs();
        // Получаем список имен подписок
        List<SelenideElement> mySubs = friend2.getSubs();
        // Заходим на страницу к выбранному подписчику
        friend2.getSub(mySubs, TestFriendsData.userName1);
        // Находим кнопку с дополнительной информацией о друге
        friend2.clickAddInfo();
        // Удаляем подписчика
        friend2.clickDeleteSub();
        // Переходим в "Друзья" в Toolbar
        friend2 = p2.getToolbars().goToFriends();
        // Получаем список имен друзей
        List<SelenideElement> friends2 = friend2.findFriends(TestFriendsData.allFriends);
        // Проверяем, что у друга с таким именем уже нет у бота в друзьях
        // Количество совпадений должно быть ровно 0
        assertEquals(0, friend2.countFriend(friends2, TestFriendsData.userName1));
        // Заходим в "Подписки"
        friend2.goToFriendSubscriptions(friendSubscriptions);
        // Ожидаем загрузки вкладки "Подписки"
        friend2.waitSubs();
        // Получаем список имен подписок
        mySubs = friend2.getSubs();
        // Считаем количество людей с именем первого бота
        // Количество совпадений должно быть ровно 0
        assertEquals(0, friend2.countFriend(mySubs, TestFriendsData.userName1));
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();
        // Закрываем окно
        closeWindow();
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}