package ru.polis.toasters.pages;

import com.codeborne.selenide.*;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;

public class GuestPage {

    private final ElementsCollection guestCards = $$(byXpath("//div[@class=\"portlet_b\"]/.//div[@class=\"user-grid-card\"]"));

    public List<UserCard> getGuestCard() {
        List<UserCard> res = new ArrayList<>();
        /*
         * Нижная строка пресдтавляет собой выход логики теста в класс POM
         * Эту архитектуру можно улучшить
         * Вроде предлагалось в ru.polis.toasters.pages.UserCard добавить статический метод получения листа карт гостей
         * И там сделать проверку на появление этих карт/страницы гостей
         */
        guestCards.shouldBe(CollectionCondition.sizeGreaterThan(1));
        for (SelenideElement s : guestCards) {
            res.add(new UserCard(s));
        }
        return res;
    }
}