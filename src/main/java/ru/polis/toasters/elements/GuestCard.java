package ru.polis.toasters.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalTime;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class GuestCard {

    SelenideElement root;

    public SelenideElement getSelenideCard() {
        return root;
    }

    private final SelenideElement deleteGuest = $("li.ic_delete");

    private final SelenideElement confirmDeleteGuest = $(byXpath("//input[@data-l=\"t,confirm\"]"));

    public GuestCard(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.find(byClassName("ellip")).getText();
    }

    public String getTime() {
        return root.$("div.timestamp __highlight").getText();
    }

    public SelenideElement getMessageButton() {
        return root.find(byClassName("tico"));
    }

    public void removeFromGuests() {
        Selenide.actions().moveToElement(root).perform();
        Selenide.sleep(1000);
        deleteGuest.click();
        confirmDeleteGuest.click();
    }
}
