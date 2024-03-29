package ru.polis.toasters.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;

import static com.codeborne.selenide.Selenide.$;

public class ToolbarRight {

    private final SelenideElement root = $("div.toolbar_dropdown_w");

    private final SelenideElement exitButton = root.$(byXpath(".//a[text()='Выйти']"));

    private final SelenideElement exitConfirmButton = $(byXpath(".//input[@value='Выйти']"));

    public SelenideElement getToolbarRight() {
        return root;
    }

    @Step("Выходим из профиля")
    public void exit() {
        root.click();
        exitButton.click();
    }

    @Step("Выходим из профиля с доп проверкой")
    public void exitWithCheck() {
        root.click();
        exitButton.click();
        exitConfirmButton.click();
    }

}
