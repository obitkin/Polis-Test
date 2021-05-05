package ru.polis.toasters.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalTime;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class GuestCard {

    private final SelenideElement guestNameBottom = $(".shortcutRoundedPanel .highlight");

    private final SelenideElement guestDeleteBottom = $(".shortcutRoundedPanel li.ic_delete");

    private final SelenideElement confirmDeleteGuest = $(byXpath("//input[@data-l=\"t,confirm\"]"));

    SelenideElement root;

    public GuestCard(SelenideElement root) {
        this.root = root;
    }

    public SelenideElement getSelenideCard() {
        return root;
    }

    @Step("Получаем имя гостя")
    public String getName() {
        return root.find(byClassName("ellip")).getText();
    }

    @Step("Получаем время посещения гостя")
    public LocalTime getTime() {
        String timeText = root.find(byClassName("timestamp")).getText();
        timeText = timeText.substring(timeText.indexOf(':') - 2, timeText.indexOf(':') + 3);
        return LocalTime.parse(timeText);
    }

    @Step("Получаем кнопку написать гостю")
    public SelenideElement getMessageButton() {
        return root.find(byClassName("tico"));
    }

    @Step("Удаляем гостя из списка гостей")
    public void removeFromGuests() {
        Selenide.actions().moveToElement(root).perform();
        //Ждем появления всплывающего окна при наведении на карту гостя
        guestNameBottom.shouldHave(text(getName()), Duration.ofSeconds(5));
        guestDeleteBottom.click();
        confirmDeleteGuest.click();
    }
}
