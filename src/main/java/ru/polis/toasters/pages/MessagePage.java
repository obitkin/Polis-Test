package ru.polis.toasters.pages;

import com.codeborne.selenide.*;
import io.github.sukgu.*;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage {

    public MessagePage() {
        waitMessages();
    }

    // Функция ожидания загрузки вкладки "Сообщения"
    @Step("Ожидаем загрузки вкладки \"Сообщения\"")
    public void waitMessages() {
        String messageLayer = ".//div[@id='msg_layer']";
        $(byXpath(messageLayer)).shouldBe(Condition.appear, Duration.ofSeconds(10));
    }

    // Функция поиска кнопки отправки смайликов
    @Step("Отправляем смайлики")
    public void smileButtonClick() {
        String smileMessageButton = ".//msg-button[@title='Смайлики']";
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        $(shadow.findElementByXPath(smileMessageButton)).click();
    }

    // Функция поиска вкладки "Смайлики"
    @Step("Ищем вкладку смайлики")
    public void smileLableClick() {
        String smileLableButton = "//msg-l10n[text()='Смайлики']";
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        $(shadow.findElementByXPath(smileLableButton)).click();
    }

    // Функция поиска кнопки "Отправить сообщение"
    @Step("Ищем кнопки \"Отправить сообщение\"")
    public void sendMessageClick() {
        String sendSmilesButton = "//msg-button[@title='Отправить']";
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        $(shadow.findElementByXPath(sendSmilesButton)).click();
    }

    // Функция поиска всех элементов по заданному локатору в теневом DOM
    public List<SelenideElement> getWebElementsFromShadowDom(String findLocator) {
        Shadow shadow = new Shadow(WebDriverRunner.getWebDriver());
        return $$(shadow.findElementsByXPath(findLocator));
    }

    // Функция открытия заданного диалога по адресату
    @Step("Открываем диалог с {name}")
    public void openUserDialog(String name) {
        String dialogs = ".//msg-chats-list-item";
        List<SelenideElement> myDialogs1 = getWebElementsFromShadowDom(dialogs);
        SelenideElement newDialog = myDialogs1.get(0);
        for (SelenideElement dialog : myDialogs1) {
            if (dialog.toString().contains(name)) {
                newDialog = dialog;
                break;
            }
        }
        newDialog.click();
    }

    // Функция ввода заданного смайлика заданное количество раз
    @Step("Вводим смайлик {smile} - {cnt} раз")
    public void getSmileAndClick(String smile, int cnt) {
        String emojiIcons = ".//section[@class='first']//img[@data-tsid='emoji']";
        List<SelenideElement> allEmojiIcons = getWebElementsFromShadowDom(emojiIcons);
        SelenideElement emojiIcon = allEmojiIcons.get(0);
        for (SelenideElement icon : allEmojiIcons) {
            if (icon.toString().contains(smile)) {
                emojiIcon = icon;
                break;
            }
        }
        for (int i = 0; i < cnt; i++) {
            emojiIcon.click();
        }
    }

    // Функция получения всех элементов последнего сообщения в диалоге
    @Step("Получаем последнее сообщение диалога")
    public List<SelenideElement> getLastMessageElements() {
        String getMessages = ".//msg-parsed-text[@class='text']";
        List<SelenideElement> messages = getWebElementsFromShadowDom(getMessages);
        SelenideElement lastMessage = messages.get(messages.size() - 1);
        String allElements = ".//*";
        return lastMessage.$$(byXpath(allElements));
    }
}