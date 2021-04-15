package ru.polis.toasters.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.polis.toasters.data.TestData;
import ru.polis.toasters.elements.GuestCard;
import ru.polis.toasters.pages.GuestPage;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.UserPage;
import ru.polis.toasters.util.UserData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class Test implements TestData {

    LoginPage loginPage;

    static Stream<Arguments> data() {
        UserData userFirst = new UserData(user1, userName1, password1, url1);
        UserData userSecond = new UserData(user2, userName2, password2, url2);
        return Stream.of(
                arguments(userFirst, userSecond),
                arguments(userSecond, userFirst)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void MyTest(UserData guest, UserData master) {

        //Логинимся гостем
        loginPage = new LoginPage();
        loginPage.loginMe(guest.user, guest.password);
        //Посещяем страницу хозяина
        open(master.url);
        closeWindow();

        //Логинимся хозяином
        loginPage = new LoginPage();
        UserPage userPage = loginPage.loginMe(master.user, master.password);

        //Кликаем на "Гостей" в туллбаре
        GuestPage guestPage = userPage.getToolbars().goToGuest();

        //Получаем список гостей
        List<GuestCard> listOfGuests = guestPage.getGuestBlock().getGuestCard();
        //Получем имена гостей
        List<String> str = listOfGuests.stream().map(GuestCard::getName).collect(Collectors.toList());

        listOfGuests.get(0).removeFromGuests();
        str.forEach(System.out::println);

        //Проверяем что среди гостей есть постетитель
        Assertions.assertTrue(str.contains(guest.userName));

        //Выходим
        guestPage.getToolbarRight().exit();
        closeWindow();
    }
}