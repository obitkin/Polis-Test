package ru.polis.toasters.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import ru.polis.toasters.data.TestMessagesData;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.MessagePage;
import ru.polis.toasters.pages.UserPage;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.closeWindow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.$;

public class TestMessages implements TestMessagesData {
    static LoginPage login;

    @org.junit.jupiter.api.BeforeAll
    public static void Start() {
        login = new LoginPage();
    }

    @org.junit.jupiter.api.Test
    public void TestMessagesSmiles() {
        // Логинимся первым ботом
        UserPage userPage1 = login.loginMe(TestMessagesData.user1, TestMessagesData.password1);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg1 = userPage1.getToolbars().goToMessage();
        // Ожидаем прогрузку вкладки "Сообщения" (теневой DOM)
        $(byXpath(TestMessagesData.messageLayer)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Получаем нужного адресата
        SelenideElement msg1To = msg1.getWebElementFromShadowDom(TestMessagesData.user2Dialog);
        msg1To.click();
        // Нажимаем на кнопку со смайликами
        SelenideElement smileBut = msg1.getWebElementFromShadowDom(TestMessagesData.smileMessageButton);
        smileBut.click();
        // Выбираем отправку смайликов
        SelenideElement smileBar = msg1.getWebElementFromShadowDom(TestMessagesData.smileLableButton);
        smileBar.click();
        // Кликаем по выбранному смайлику (smileCnt) раз
        SelenideElement smileIcon = msg1.getWebElementFromShadowDom(TestMessagesData.smileIconButton);
        for (int i = 0; i < TestMessagesData.smileCnt; i++)
        {
            smileIcon.click();
        }
        // Отправляем сообщение со смайликами
        SelenideElement send = msg1.getWebElementFromShadowDom(TestMessagesData.sendSmilesButton);
        send.click();
        // Нажимаем на кнопку выхода из системы
        // Но тут бы в страницу MessagePage добавить метод exitWithCheck из ToolbarRight
        closeWindow();


        // Логинимся вторым ботом
        // Мб стоит создавать новый браузер
        // юзеры не с одного браузера же заходят(вдруг там куки будут, я хз)
        login = new LoginPage();
        UserPage userPage2 = login.loginMe(TestMessagesData.user2, TestMessagesData.password2);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg2 = userPage2.getToolbars().goToMessage();
        // Ожидаем прогрузку вкладки "Сообщения" (теневой DOM)
        $(byXpath(TestMessagesData.messageLayer)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Получаем нужного адресата
        SelenideElement msg2To = msg2.getWebElementFromShadowDom(TestMessagesData.user1Dialog);
        msg2To.click();
        // Получаем список сообщений в диалоге
        List<SelenideElement> messages = msg2.getWebElementsFromShadowDom(TestMessagesData.getMessages);
        // Выбираем последнее сообщение
        SelenideElement lastMessage = messages.get(messages.size() - 1);
        // Получаем список элементов внутри последнего сообщения
        List<SelenideElement> lastMessageElements = lastMessage.$$(byXpath(TestMessagesData.elementsMessages));
        // Сравниваем кол-во элементов в полученном и отправленном сообщениях
        Assertions.assertEquals(lastMessageElements.size(), TestMessagesData.smileCnt);
        // Сравниваем полученное и отправленное сообщения поэлементно
        for (int i = 0; i < TestMessagesData.smileCnt; i++)
        {
            Assertions.assertEquals(lastMessageElements.get(i).getAttribute("alt"), TestMessagesData.smile);
        }
        // Нажимаем на кнопку выхода из системы
        closeWindow();
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}
