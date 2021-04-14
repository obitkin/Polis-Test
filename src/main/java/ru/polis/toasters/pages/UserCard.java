package ru.polis.toasters.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;

public class UserCard {

    SelenideElement root;

    public UserCard(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.find(byClassName("ellip")).getText();
    }

    public SelenideElement getMessageButton() {
        return root.find(byClassName("tico"));
    }
}
