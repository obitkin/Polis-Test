package ru.polis.toasters.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ToolbarRight {

    private final SelenideElement root = $("div.toolbar_dropdown_w");

    public SelenideElement getToolbarRight() {
        return root;
    }

    public void exit() {
        root.click();
        root.$(By.xpath(".//a[text()='Выйти']")).click();
    }

    public void exitWithCheck() {
        root.click();
        root.$(By.xpath(".//a[text()='Выйти']")).click();
        $(By.xpath("//input[@data-l=\"t,logout\"]")).click();
    }

}
