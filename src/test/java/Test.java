import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test implements TestData {

    LoginPage loginPage;

    @org.junit.jupiter.api.Test
    public void MyTest() {
        loginPage = new LoginPage();

        //Логинимся
        UserPage userPage = loginPage.loginMe(login, password);

        //Кликаем на "Гостей" в туллбаре
        GuestPage guestPage = userPage.goToGuest();

        //Получаем список гостей
        List<UserCard> listOfUsers = guestPage.getGuestCard();

        //Проверяем что размер одинаков
        assertEquals(namesFromTest.size(), listOfUsers.size());

        //Просто вывод имен для отладки
        //listOfUsers.forEach(x -> System.out.println(x.getName()));

        //Проверяем что каждое имя из тестовых данных содержится на странице
        //Тут бы пригодился SoftAssert из TestNG, чтобы не падать на первом отсутствующем имени
        //Но если делать через AssertAll то прикольно
        List<Executable> assertionsForNames = new ArrayList<>();
        final List<String> namesFromPage = listOfUsers.stream()
                            .map(UserCard::getName)
                            .collect(Collectors.toList());

        for (String nameTest : namesFromTest) {
            assertionsForNames.add(
                    () -> assertTrue(
                            namesFromPage.contains(nameTest),
                            "Имя " + nameTest + " не найдено на странице"));
        }
        Assertions.assertAll(assertionsForNames);

        //Просто клик на шару
        listOfUsers.get(0).getMessageButton().click();
    }
}
