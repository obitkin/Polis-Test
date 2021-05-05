package ru.polis.toasters.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;

public class LoginPage {

    String baseUrl = "https://ok.ru/";

    private SelenideElement login = $(byId("field_email"));
    private SelenideElement password = $(byId("field_password"));
    private SelenideElement enter = $(byXpath("//input[@value='Войти в Одноклассники']"));

    public LoginPage() {
        open(baseUrl);
    }

    public SelenideElement getLogin() {
        return login;
    }

    public SelenideElement getPassword() {
        return password;
    }

    public SelenideElement getEnter() {
        return enter;
    }

    @Step("Логинимся Логин: {login} Пароль {password}")
    public FeedPage loginMe(String login, String password) {
        getLogin().setValue(login);
        getPassword().setValue(password);
        getEnter().click();
        return new FeedPage();
    }
}