package ru.polis.toasters.elements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;

import static com.codeborne.selenide.Selenide.$;

public class ToolbarRight {

    private final SelenideElement root = $(byXpath(".//div[@class='ucard-mini toolbar_ucard js-toolbar-menu']"));

    public SelenideElement getToolbarRight() {
        return root;
    }

    public void exit() {
        root.click();
        root.$(byXpath(".//a[text()='Выйти']")).click();
    }

    public void exitWithCheck() {
        root.click();
        $(byXpath(".//a[text()='Выйти']")).click();
        $(byXpath(".//input[@value='Выйти']")).click();
    }

}
