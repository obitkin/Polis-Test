package ru.polis.toasters.tests;

import com.codeborne.selenide.*;
import ru.polis.toasters.data.TestFriendsData;
import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.pages.AddFriendPage;
import ru.polis.toasters.pages.FriendsPage;
import ru.polis.toasters.pages.LoginPage;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFriends implements TestFriendsData {
    static LoginPage login;

    @org.junit.jupiter.api.BeforeAll
    public static void Start() {
        login = new LoginPage();
    }

    // Работает только в том случае, если два выбранных пользователя еще не дружат друг с другом
    // и не являются подписчиками друг у друга
    @org.junit.jupiter.api.Test
    public void TestFriendsAdd() {
        // Логинимся первым ботом
        login.loginMe(TestFriendsData.user1, TestFriendsData.password1);
        // Переходим в "Друзья" в Toolbar
        Toolbar toolbar = new Toolbar();
        FriendsPage friend1 = toolbar.goToFriends();
        // Заходим на страницу добавления нового друга
        AddFriendPage addNewFriend1 = friend1.goToFriendsAdd();
        // Переходим на поле поиска нового друга
        SelenideElement addNewFriendField = addNewFriend1.getNewFriendField(TestFriendsData.addFriendField);
        executeJavaScript("arguments[0].click()", addNewFriendField);
        // Вводим ID страницы второго бота
        addNewFriendField.sendKeys(TestFriendsData.userID2);
        // Ожидаем появления кнопки "Добавить"
        $(byXpath(TestFriendsData.findButton)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Получаем кнопку "Найти"
        SelenideElement findBut = addNewFriend1.getFindButton(TestFriendsData.findButton);
        findBut.click();
        // Ожидаем появления результатов поиска
        $(byXpath(TestFriendsData.waitResult)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Получаем первого человека, удовлетворяющего поиску
        SelenideElement firstPerson = addNewFriend1.getFirstPerson(TestFriendsData.filteredPeopleList);
        firstPerson.click();
        // Находим кнопку "Добавить в друзья"
        SelenideElement addFriendButton = firstPerson.$(byXpath(TestFriendsData.addButtonFriend));
        addFriendButton.click();
        // Нажимаем на кнопку выхода из системы
        SelenideElement logOutBut1 = toolbar.logOut(TestFriendsData.logOutButton);
        logOutBut1.click();
        // Выходим из системы
        SelenideElement logOut1 = toolbar.logOut(TestFriendsData.logOutClick);
        logOut1.click();
        // Подтверждаем выход из системы
        SelenideElement acceptLogOut1 = toolbar.logOut(TestFriendsData.acceptLogOutClick);
        acceptLogOut1.click();


        // Логинимся вторым ботом
        login.loginMe(TestFriendsData.user2, TestFriendsData.password2);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend2 = toolbar.goToFriends();
        // Переходим в раздел "Входящие заявки в друзья"
        SelenideElement friendRequest = friend2.goToFriendRequests(TestFriendsData.friendRequests);
        friendRequest.click();
        // Ожидаем появления заявок в друзья
        $(byXpath(TestFriendsData.waitAccept)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Нажимаем на кнопку "Принять заявку в друзья"
        SelenideElement accept = friend2.acceptClick(TestFriendsData.acceptButton);
        accept.click();
        // Переходим на страницу "Друзья"
        friend2 = toolbar.goToFriends();
        // Получаем список всех друзей
        ElementsCollection friends2 = friend2.findFriends(TestFriendsData.allFriends);
        friends2.should(CollectionCondition.sizeGreaterThan(0));
        // Создаем переменную для подсчета количества друзей, которые совпадают по имени с первым ботом
        int coincidenceCnt2 = 0;
        // Ищем все совпадения c именем первого бота
        for (SelenideElement friend : friends2) {
            if (friend.toString().contains(TestFriendsData.userName1)) {
                coincidenceCnt2++;
            }
        }
        // Количество совпадений должно быть ровно 1
        assertEquals(coincidenceCnt2, 1);
        // Нажимаем на кнопку выхода из системы
        SelenideElement logOutBut2 = toolbar.logOut(TestFriendsData.logOutButton);
        logOutBut2.click();
        // Выходим из системы
        SelenideElement logOut2 = toolbar.logOut(TestFriendsData.logOutClick);
        logOut2.click();
        // Подтверждаем выход из системы
        SelenideElement acceptLogOut2 = toolbar.logOut(TestFriendsData.acceptLogOutClick);
        acceptLogOut2.click();


        // Логинимся первым ботом
        login.loginMe(TestFriendsData.user1, TestFriendsData.password1);
        // Переходим в "Друзья" в Toolbar
        friend1 = toolbar.goToFriends();
        // Получаем список имен друзей
        ElementsCollection friends1 = friend1.findFriends(TestFriendsData.allFriends);
        friends1.should(CollectionCondition.sizeGreaterThan(0));
        // Создаем переменную для подсчета количества друзей, которые совпадают по имени со вторым ботом
        int coincidenceCnt1 = 0;
        // Ищем все совпадения c именем второго бота
        for (SelenideElement friend : friends1) {
            if (friend.text().equals(TestFriendsData.userName2)) {
                coincidenceCnt1++;
            }
        }
        // Количество совпадений должно быть ровно 1
        assertEquals(coincidenceCnt1, 1);
        // Нажимаем на кнопку выхода из системы
        logOutBut1 = toolbar.logOut(TestFriendsData.logOutButton);
        logOutBut1.click();
        // Выходим из системы
        logOut1 = toolbar.logOut(TestFriendsData.logOutClick);
        logOut1.click();
        // Подтверждаем выход из системы
        acceptLogOut1 = toolbar.logOut(TestFriendsData.acceptLogOutClick);
        acceptLogOut1.click();
    }

    // Работает только в том случае, если два выбранных пользователя дружат друг с другом
    @org.junit.jupiter.api.Test
    public void TestFriendsDelete() {
        // Логинимся первым ботом
        login.loginMe(TestFriendsData.user1, TestFriendsData.password1);
        // Переходим в "Друзья" в Toolbar
        Toolbar toolbar = new Toolbar();
        FriendsPage friend1 = toolbar.goToFriends();
        // Получаем список имен друзей
        ElementsCollection friends1 = friend1.findFriends(TestFriendsData.allFriends);
        friends1.should(CollectionCondition.sizeGreaterThan(0));
        // Находим второго бота и заходим к нему на страницу
        for (SelenideElement friend : friends1) {
            if (friend.text().equals(TestFriendsData.userName2)) {
                friend.click();
                break;
            }
        }
        // Находим кнопку с дополнительной информацией о друге
        SelenideElement additionalFriendInfo = $(byXpath(additionalInfoFriend));
        additionalFriendInfo.click();
        // Удаляем друга
        SelenideElement deleteMyFriend = $(byXpath(deleteFriend));
        deleteMyFriend.click();
        // Подтверждаем удаление
        SelenideElement acceptMyDelete = $(byXpath(acceptDelete));
        acceptMyDelete.click();
        // Закрываем уведомление об удалении друга
        SelenideElement acceptMyInfo = $(byXpath(acceptInfo));
        acceptMyInfo.click();
        // Переходим в "Друзья" в Toolbar
        friend1 = toolbar.goToFriends();
        // Получаем список имен друзей
        friends1 = friend1.findFriends(TestFriendsData.allFriends);
        // Проверяем, что друга с таким именем уже нет у бота в друзьях
        int secondFriendCnt = 0;
        for (SelenideElement friend : friends1) {
            if (friend.text().equals(TestFriendsData.userName2)) {
                secondFriendCnt++;
            }
        }
        // Количество совпадений должно быть ровно 0
        assertEquals(secondFriendCnt, 0);
        // Нажимаем на кнопку выхода из системы
        SelenideElement logOutBut1 = toolbar.logOut(TestFriendsData.logOutButton);
        logOutBut1.click();
        // Выходим из системы
        SelenideElement logOut1 = toolbar.logOut(TestFriendsData.logOutClick);
        logOut1.click();
        // Подтверждаем выход из системы
        SelenideElement acceptLogOut1 = toolbar.logOut(TestFriendsData.acceptLogOutClick);
        acceptLogOut1.click();


        // Логинимся вторым ботом
        login.loginMe(TestFriendsData.user2, TestFriendsData.password2);
        // Переходим в "Друзья" в Toolbar
        FriendsPage friend2 = toolbar.goToFriends();
        // Заходим в "Подписки"
        SelenideElement myFriendSubscriptions = friend2.goToFriendSubscriptions(friendSubscriptions);
        myFriendSubscriptions.click();
        // Получаем список имен подписок
        ElementsCollection mySubs = $$(byXpath(allSubs));
        mySubs.should(CollectionCondition.sizeGreaterThan(0));
        // Находим первого бота и заходим к нему на страницу
        for (SelenideElement sub : mySubs) {
            if (sub.text().equals(TestFriendsData.userName1)) {
                sub.click();
                break;
            }
        }
        // Находим кнопку с дополнительной информацией о друге
        additionalFriendInfo = $(byXpath(additionalInfoFriend));
        additionalFriendInfo.click();
        // Удаляем друга
        SelenideElement unsubs = $(byXpath(unsubscribe));
        unsubs.click();
        // Переходим в "Друзья" в Toolbar
        friend2 = toolbar.goToFriends();
        // Получаем список имен друзей
        ElementsCollection friends2 = friend2.findFriends(TestFriendsData.allFriends);
        // Проверяем, что у друга с таким именем уже нет у бота в друзьях
        int firstFriendCnt = 0;
        for (SelenideElement friend : friends2) {
            if (friend.text().equals(TestFriendsData.userName1)) {
                firstFriendCnt++;
            }
        }
        // Количество совпадений должно быть ровно 0
        assertEquals(firstFriendCnt, 0);
        // Заходим в "Подписки"
        myFriendSubscriptions = friend2.goToFriendSubscriptions(friendSubscriptions);
        myFriendSubscriptions.click();
        // Получаем список имен подписок
        mySubs = $$(byXpath(allSubs));
        int secondSubsCnt = 0;
        // Считаем количество людей с именем первого бота
        for (SelenideElement sub : mySubs) {
            if (sub.text().equals(TestFriendsData.userName1)) {
                secondSubsCnt++;
                break;
            }
        }
        // Количество совпадений должно быть ровно 0
        assertEquals(secondSubsCnt, 0);
        // Нажимаем на кнопку выхода из системы
        SelenideElement logOutBut2 = toolbar.logOut(TestFriendsData.logOutButton);
        logOutBut2.click();
        // Выходим из системы
        SelenideElement logOut2 = toolbar.logOut(TestFriendsData.logOutClick);
        logOut2.click();
        // Подтверждаем выход из системы
        SelenideElement acceptLogOut2 = toolbar.logOut(TestFriendsData.acceptLogOutClick);
        acceptLogOut2.click();
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}
