package ru.polis.toasters.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;

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
}
