package ru.polis.toasters.tests;

import com.codeborne.selenide.WebDriverRunner;
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

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class TestGuest implements TestData {

    LoginPage loginPage;

    static Stream<Arguments> data() {
        UserData userFirst = new UserData(user1, userName1, password1, url1);
        UserData userSecond = new UserData(user2, userName2, password2, url2);
        return Stream.of(
                arguments(userFirst, userSecond),
                arguments(userSecond, userFirst)
        );
    }

    /**
     * Тест проверяет что при посещении страницы одним пользователем:
     * этот пользователь отображается как гость +
     * время посещения совпадает с измеренным +
     * присутствует уведомление о новых гостях в туллбаре
     */
    @ParameterizedTest
    @MethodSource("data")
    public void MyTest(UserData guest, UserData master) {

        //Логинимся гостем
        loginPage = new LoginPage();
        UserPage userPageGuest = loginPage.loginMe(guest.user, guest.password);
        //Посещяем страницу хозяина
        open(master.url);
        //Запоминаем время посещения
        LocalTime time = LocalTime.now();
        //Выходим из профиля
        userPageGuest.getToolbarRight().exit();
        //Закрываем браузер
        closeWindow();

        //Логинимся хозяином
        loginPage = new LoginPage();
        UserPage userPageMaster = loginPage.loginMe(master.user, master.password);

        //Проверяем что есть хотя бы 1 новый гость
        Assertions.assertTrue(userPageMaster.getToolbars().getGuestCounter() > 0);

        //Кликаем на "Гостей" в туллбаре
        GuestPage guestPage = userPageMaster.getToolbars().goToGuest();

        //Получаем список гостей
        List<GuestCard> listOfGuests = guestPage.getGuestBlock().getGuestCard();
        //Получаем карту гостя с нужным именем(если нету то тест падает)
        GuestCard guestCard;
        try {
            guestCard = listOfGuests
                    .stream()
                    .filter(guestInList -> guestInList.getName().equals(guest.userName))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException ex) {
            fail("Гость не найден на странице: " + guest.userName);
            return;
        }
        //Получаем время из карты
        LocalTime guestTime = guestCard.getTime();
        //Проверяем, что время отличается не сильно
        int guestMinute = guestTime.getHour() * 60 + guestTime.getMinute();
        int timeMinute = time.getHour() * 60 + time.getMinute();
        int minuteDiff = Math.abs(guestMinute - timeMinute);
        Assertions.assertTrue(minuteDiff <= 10);

        //Удаляем гостя из списка гостей
        guestCard.removeFromGuests();
        //Выходим из профиля
        guestPage.getToolbarRight().exit();
        //Закрываем браузер
        closeWindow();
    }

    @org.junit.jupiter.api.AfterAll
    public static void Stop() {
        WebDriverRunner.closeWebDriver();
    }
}