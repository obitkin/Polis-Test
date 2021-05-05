package ru.polis.toasters.tests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import ru.polis.toasters.data.TestMessagesData;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.MessagePage;
import ru.polis.toasters.pages.FeedPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.closeWindow;

public class TestMessages implements TestMessagesData {
    static LoginPage login;

    @Step("Открытие страницы логина")
    @org.junit.jupiter.api.BeforeAll
    public static void Start() {
        Configuration.headless = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        login = new LoginPage();
    }

    // Тест на отправку и прием смайликов в беседе
    @Step("Пишем сообщение из смайлов")
    @org.junit.jupiter.api.Test
    public void TestMessagesSmiles() {
        // Логинимся первым ботом
        FeedPage p1 = login.loginMe(TestMessagesData.user1, TestMessagesData.password1);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg1 = p1.getToolbars().goToMessage();
        // Получаем нужного адресата
        msg1.openUserDialog(TestMessagesData.userName2);
        // Нажимаем на кнопку со смайликами
        msg1.smileButtonClick();
        // Выбираем отправку смайликов
        msg1.smileLableClick();
        // Находим нужный смайл и кликаем по нему (smileCnt) раз
        msg1.getSmileAndClick(TestMessagesData.smile, TestMessagesData.smileCnt);
        // Отправляем сообщение со смайликами
        msg1.sendMessageClick();
        // Выходим из профиля
        p1.getToolbarRight().exitWithCheck();


        // Логинимся вторым ботом
        FeedPage p2 = login.loginMe(TestMessagesData.user2, TestMessagesData.password2);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg2 = p2.getToolbars().goToMessage();
        // Получаем нужного адресата
        msg2.openUserDialog(TestMessagesData.userName1);
        // Получаем все элементы последнего сообщения в диалоге
        List<SelenideElement> lastMessageElements = msg2.getLastMessageElements();
        // Сравниваем кол-во элементов в полученном и отправленном сообщениях
        Assertions.assertEquals(lastMessageElements.size(), TestMessagesData.smileCnt);
        // Сравниваем полученное и отправленное сообщения поэлементно
        for (int i = 0; i < TestMessagesData.smileCnt; i++)
        {
            Assertions.assertEquals(lastMessageElements.get(i).getAttribute("alt"), TestMessagesData.smile);
        }
        // Выходим из профиля
        p2.getToolbarRight().exitWithCheck();
    }

    @Step("Закрываем браузер")
    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }
}