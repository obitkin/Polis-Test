import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
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
        UserPage userPage1 = login.loginMe(user1, password1);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg1 = userPage1.goToMessage();
        // Ожидаем прогрузку вкладки "Сообщения" (теневой DOM)
        $(byXpath(messageLayer)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Получаем нужного адресата
        SelenideElement msg1To = msg1.getWebElementFromShadowDom(user2Dialog);
        msg1To.click();
        // Нажимаем на кнопку со смайликами
        SelenideElement smileBut = msg1.getWebElementFromShadowDom(smileMessageButton);
        smileBut.click();
        // Выбираем отправку смайликов
        SelenideElement smileBar = msg1.getWebElementFromShadowDom(smileLableButton);
        smileBar.click();
        // Кликаем по выбранному смайлику (smileCnt) раз
        SelenideElement smileIcon = msg1.getWebElementFromShadowDom(smileIconButton);
        for (int i = 0; i < smileCnt; i++)
        {
            smileIcon.click();
        }
        // Отправляем сообщение со смайликами
        SelenideElement send = msg1.getWebElementFromShadowDom(sendSmilesButton);
        send.click();
        // Нажимаем на кнопку выхода из системы
        SelenideElement logOutBut = userPage1.logOut(logOutButton);
        logOutBut.click();
        // Выходим из системы
        SelenideElement logOut = userPage1.logOut(logOutClick);
        logOut.click();
        // Подтверждаем выход из системы
        SelenideElement acceptLogOut = userPage1.logOut(acceptLogOutClick);
        acceptLogOut.click();


        // Логинимся вторым ботом
        UserPage userPage2 = login.loginMe(user2, password2);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg2 = userPage2.goToMessage();
        // Ожидаем прогрузку вкладки "Сообщения" (теневой DOM)
        $(byXpath(messageLayer)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        // Получаем нужного адресата
        SelenideElement msg2To = msg2.getWebElementFromShadowDom(user1Dialog);
        msg2To.click();
        // Получаем список сообщений в диалоге
        List<SelenideElement> messages = msg2.getWebElementsFromShadowDom(getMessages);
        // Выбираем последнее сообщение
        SelenideElement lastMessage = messages.get(messages.size() - 1);
        // Получаем список элементов внутри последнего сообщения
        List<SelenideElement> lastMessageElements = lastMessage.$$(byXpath(elementsMessages));
        // Сравниваем кол-во элементов в полученном и отправленном сообщениях
        assertEquals(lastMessageElements.size(), smileCnt);
        // Сравниваем полученное и отправленное сообщения поэлементно
        for (int i = 0; i < smileCnt; i++)
        {
            assertEquals(lastMessageElements.get(i).getAttribute("alt"), smile);
        }
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}
