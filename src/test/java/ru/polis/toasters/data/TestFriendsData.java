package ru.polis.toasters.data;

public interface TestFriendsData {
    // Данные бота 1
    String user1 = "89995632015";
    String userName1 = "Карл Дёниц";
    String userID1 = "597561870126";
    String password1 = "Kirill11";

    // Данные бота 2
    String user2 = "89944056284";
    String userName2 = "Георгий Второй";
    String userID2 = "586820330668";
    String password2 = "Kirill2";


    // Локатор для поиска входящих заявок в друзья
    String friendRequests = ".//div[text()='Заявки в друзья ']//..";
    // Локатор для поиска кнопки "Добавить в друзья"
    String acceptButton = ".//span[text()='Принять' and contains(@class, 'accept')]";
    // Локатор для поиска списка друзей
    String allFriends = ".//a[@class='n-t bold']";


    // Локатор для вкладки "Подписки"
    String friendSubscriptions = ".//a[contains(@href, 'subscriptions')]";
}