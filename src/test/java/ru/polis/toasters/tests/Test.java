package ru.polis.toasters.tests;

import org.junit.jupiter.api.Assertions;
import ru.polis.toasters.data.TestData;
import ru.polis.toasters.elements.GuestCard;
import ru.polis.toasters.pages.GuestPage;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.UserPage;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test implements TestData {

    LoginPage loginPage;

    @org.junit.jupiter.api.Test
    public void MyTest() {

        //Логинимся
        loginPage = new LoginPage();
        loginPage.loginMe(user2, password2);
        open(url1);
        closeWindow();

        loginPage = new LoginPage();
        UserPage userPage = loginPage.loginMe(user1, password1);

        //Кликаем на "Гостей" в туллбаре
        GuestPage guestPage = userPage.getToolbars().goToGuest();

        //Получаем список гостей
        List<GuestCard> listOfGuests = guestPage.getGuestBlock().getGuestCard();

        List<String> str = listOfGuests.stream().map(GuestCard::getName).collect(Collectors.toList());
        listOfGuests.get(0).removeFromGuests();
        str.forEach(System.out::println);
        //Проверяем что среди гостей есть постетитель
        Assertions.assertTrue(str.contains(userName2));

        //Выходим
        guestPage.getToolbarRight().exit();
    }
}