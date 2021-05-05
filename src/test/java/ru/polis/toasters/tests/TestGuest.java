package ru.polis.toasters.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.polis.toasters.data.TestData;
import ru.polis.toasters.elements.GuestCard;
import ru.polis.toasters.pages.GuestPage;
import ru.polis.toasters.pages.LoginPage;
import ru.polis.toasters.pages.FeedPage;
import ru.polis.toasters.util.UserData;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class TestGuest implements TestData {

    LoginPage loginPage;

    static UserData userFirst;
    static UserData userSecond;

    static Stream<Arguments> guestAndMasterData() {
        return Stream.of(
                arguments(userFirst, userSecond),
                arguments(userSecond, userFirst)
        );
    }

    @Step("Загрузка данных пользователей и настройка")
    @BeforeAll
    static void setUP() {
        Configuration.headless = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        userFirst = new UserData(user1, userName1, password1, url1);
        userSecond = new UserData(user2, userName2, password2, url2);
    }

    @Step("Подготовка данных, очитска гостя из списка гостей хозяина. Гость: {guest.userName} Хозяин: {master.userName}")
    public void clearData(UserData guest, UserData master) {
        loginPage = new LoginPage();
        FeedPage feedPageMaster = loginPage.loginMe(master.user, master.password);
        //Кликаем на "Гостей" в туллбаре
        //При клике очищаются нотификации гостей
        GuestPage guestPage = feedPageMaster.getToolbars().goToGuest();
        GuestCard guestCard;
        if ((guestCard = guestPage.getGuestBlock().getGuestCard(guest.userName)) != null) {
            //Удаляем гостя из списка гостей
            guestCard.removeFromGuests();
        }
        //Выходим из профиля
        guestPage.getToolbarRight().exit();
        closeWindow();
    }

    /**
     * Тест проверяет что при посещении страницы одним пользователем:
     * этот пользователь отображается как гость +
     * время посещения совпадает с измеренным
     */
    @Step("Проверка вывода госта в списке гостей при посещении и проверка наличия корректного времени посещения Гость: {guest.userName} Хозяин: {master.userName}")
    @ParameterizedTest
    @MethodSource("guestAndMasterData")
    public void GuestDisplayedInGuestListAndHaveProperTimeTest(UserData guest, UserData master) {
        //Вызов через BeforeEach невозможен, так как в методе BeforeEach
        //невозможно получить текущий guest и master из data
        //а переносить очистку данных после теста не очень хорошо
        clearData(guest, master);

        //Логинимся гостем
        loginPage = new LoginPage();
        FeedPage feedPageGuest = loginPage.loginMe(guest.user, guest.password);
        //Посещяем страницу хозяина
        open(master.url);
        //Запоминаем время посещения
        LocalTime time = LocalTime.now();
        //Выходим из профиля
        feedPageGuest.getToolbarRight().exit();
        //Закрываем браузер
        closeWindow();

        //Логинимся хозяином
        loginPage = new LoginPage();
        FeedPage feedPageMaster = loginPage.loginMe(master.user, master.password);

        //Кликаем на "Гостей" в туллбаре
        GuestPage guestPage = feedPageMaster.getToolbars().goToGuest();

        //Получаем карту гостя с нужным именем(если нету то тест падает)
        GuestCard guestCard;
        Assertions.assertNotNull(guestCard = guestPage.getGuestBlock().getGuestCard(guest.userName));

        //Получаем время из карты
        LocalTime guestTime = guestCard.getTime();
        //Проверяем, что время отличается не сильно
        int guestMinute = guestTime.getHour() * 60 + guestTime.getMinute();
        int timeMinute = time.getHour() * 60 + time.getMinute();
        int minuteDiff = Math.abs(guestMinute - timeMinute);
        int diff = 10;
        Assertions.assertTrue(minuteDiff <= diff || minuteDiff >= 24 * 60 - diff);
    }

    @Step("Проверка появления нотификации при посещении Гость: {guest.userName} Хозяин: {master.userName}")
    @ParameterizedTest
    @MethodSource("guestAndMasterData")
    public void GuestNotificationAtLeastOneAfterVisitTest(UserData guest, UserData master) {
        clearData(guest, master);

        //Логинимся гостем
        loginPage = new LoginPage();
        FeedPage feedPageGuest = loginPage.loginMe(guest.user, guest.password);
        //Посещяем страницу хозяина
        open(master.url);
        //Выходим из профиля
        feedPageGuest.getToolbarRight().exit();
        //Закрываем браузер
        closeWindow();

        //Логинимся хозяином
        loginPage = new LoginPage();
        FeedPage feedPageMaster = loginPage.loginMe(master.user, master.password);

        //Проверяем что есть хотя бы 1 новый гость
        Assertions.assertTrue(feedPageMaster.getToolbars().getGuestCounter() > 0);
    }

    @Step("Закрываем окно")
    @AfterEach
    void close() {
        closeWindow();
    }

    @Step("Закрываем браузер")
    @AfterAll
    public static void stop() {
        WebDriverRunner.closeWebDriver();
    }
}