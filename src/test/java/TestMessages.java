import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMessages implements TestMessagesData {
    static LoginPage login;
    WebDriverWait wait;

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
        wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(messageLayer), 0));
        // Получаем нужного адресата
        WebElement msg1To = msg1.getWebElementFromShadowDom(user2Dialog);
        msg1To.click();
        // Нажимаем на кнопку со смайликами
        WebElement smileBut = msg1.getWebElementFromShadowDom(smileMessageButton);
        smileBut.click();
        // Выбираем отправку смайликов
        WebElement smileBar = msg1.getWebElementFromShadowDom(smileLableButton);
        smileBar.click();
        // Кликаем по выбранному смайлику (smileCnt) раз
        WebElement smileIcon = msg1.getWebElementFromShadowDom(smileIconButton);
        for (int i = 0; i < smileCnt; i++)
        {
            smileIcon.click();
        }
        smileIcon.getText();
        // Отправляем сообщение со смайликами
        WebElement send = msg1.getWebElementFromShadowDom(sendSmilesButton);
        send.click();
        // Нажимаем на кнопку выхода из системы
        WebElement logOutBut = userPage1.logOut(logOutButton);
        logOutBut.click();
        // Выходим из системы
        WebElement logOut = userPage1.logOut(logOutClick);
        logOut.click();
        // Подтверждаем выход из системы
        WebElement acceptLogOut = userPage1.logOut(acceptLogOutClick);
        acceptLogOut.click();


        // Логинимся вторым ботом
        UserPage userPage2 = login.loginMe(user2, password2);
        // Переходим в "Сообщения" в Toolbar
        MessagePage msg2 = userPage2.goToMessage();
        // Ожидаем прогрузку вкладки "Сообщения" (теневой DOM)
        wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(messageLayer), 0));
        // Получаем нужного адресата
        WebElement msg2To = msg2.getWebElementFromShadowDom(user1Dialog);
        msg2To.click();
        // Получаем список сообщений в диалоге
        List<WebElement> messages = msg2.getWebElementsFromShadowDom(getMessages);
        // Выбираем последнее сообщение
        WebElement lastMessage = messages.get(messages.size() - 1);
        // Получаем список элементов внутри последнего сообщения
        List<WebElement> lastMessageElements = lastMessage.findElements(By.xpath(elementsMessages));
        // Сравниваем кол-во элементов в полученном и отправленном сообщениях
        assertEquals(lastMessageElements.size(), smileCnt);
        // Сравниваем полученное и отправленное сообщения поэлементно
        for (int i = 0; i < smileCnt; i++)
        {
            assertEquals(lastMessageElements.get(i).getAttribute("alt"), smile);
        }

        //WebElement writeMsg = msg1.sendMessage(".//msg-input[@data-placeholder='Напишите сообщение...']");
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}
