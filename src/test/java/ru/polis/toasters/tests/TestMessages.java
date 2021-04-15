package ru.polis.toasters.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import ru.polis.toasters.data.TestMessagesData;
import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.elements.ToolbarRight;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.MessagePage;
import java.util.List;

import static com.codeborne.selenide.Selenide.closeWindow;

public class TestMessages implements TestMessagesData {
    static LoginPage login;
    static Toolbar toolbar;
    static ToolbarRight exit;

    @org.junit.jupiter.api.BeforeAll
    public static void Start() {
        login = new LoginPage();
        toolbar = new Toolbar();
        exit = new ToolbarRight();
    }

    // Тест на отправку и прием смайликов в беседе
    @org.junit.jupiter.api.Test
    public void TestMessagesSmiles() {
        // Логинимся первым ботом
        login.loginMe(TestMessagesData.user1, TestMessagesData.password1);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg1 = toolbar.goToMessage();
        // Ожидаем загрузку вкладки "Сообщения" (теневой DOM)
        msg1.waitMessages();
        // Получаем нужного адресата
        msg1.openUserDialog(TestMessagesData.userName2);
        // Нажимаем на кнопку со смайликами
        msg1.clickWebElementFromShadowDom(TestMessagesData.smileMessageButton);
        // Выбираем отправку смайликов
        msg1.clickWebElementFromShadowDom(TestMessagesData.smileLableButton);
        // Находим нужный смайл и кликаем по нему (smileCnt) раз
        msg1.getSmileAndClick(TestMessagesData.smile, TestMessagesData.smileCnt);
        // Отправляем сообщение со смайликами
        msg1.clickWebElementFromShadowDom(TestMessagesData.sendSmilesButton);
        // Выходим из профиля
        exit.exitWithCheck();


        // Логинимся вторым ботом
        login.loginMe(TestMessagesData.user2, TestMessagesData.password2);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg2 = toolbar.goToMessage();
        // Ожидаем загрузку вкладки "Сообщения" (теневой DOM)
        msg2.waitMessages();
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
        exit.exitWithCheck();
        // Закрываем окно
        closeWindow();
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}