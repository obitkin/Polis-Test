package ru.polis.toasters.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class GuestCard {

    SelenideElement root;

    public SelenideElement getSelenideCard() {
        return root;
    }

    public GuestCard(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.find(byClassName("ellip")).getText();
    }

    public SelenideElement getMessageButton() {
        return root.find(byClassName("tico"));
    }

    public void removeFromGuests() {
        Selenide.actions().moveToElement(root).perform();
        Selenide.sleep(1000);
        $("li.ic_delete").click();
        $(byXpath("//input[@data-l=\"t,confirm\"]")).click();
    }
}
