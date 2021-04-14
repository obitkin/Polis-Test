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

    // Локатор для поля ввода имени нового друга
    String addFriendField = ".//input[@type='text' and @placeholder='Введите имя или название']";
    // Локатор для поиска списка людей по введенному тексту в поле поиска нового друга
    String filteredPeopleList = ".//div[@class='row__px8cs skip-first-gap__m3nyy']";
    // Локатор кнопки "Найти"
    String findButton = ".//span[text()='Найти' and @class='content__0ej09']//..//..//span[contains(@class, 'button-core')]";
    // Локатор результатов ожидания поиска друга
    String waitResult = ".//div[contains(text(), 'Найден')]";
    // Локатор для кнопки добавления в друзья
    String addButtonFriend = ".//span[@class='content__0ej09' and text()='Добавить в друзья']";
    // Локатор для поиска кнопки с информацией о пользователе, где есть кнопка выхода из аккаунта
    String logOutButton = ".//div[@class='ucard-mini toolbar_ucard js-toolbar-menu']";
    // Локатор для поиска кнопки выхода из аккаунта
    String logOutClick = ".//a[text()='Выйти']";
    // Локатор для поиска кнопки подтверждения выхода из аккаунта
    String acceptLogOutClick = ".//input[@value='Выйти']";
    // Локатор для поиска входящих заявок в друзья
    String friendRequests = ".//div[text()='Заявки в друзья ']//..";
    // Локатор результатов ожидания загрузки входящих заявок в друзья
    String waitAccept = ".//div[contains(text(), 'Входящие заявки в друзья')]";
    // Локатор для поиска кнопки "Добавить в друзья"
    String acceptButton = ".//span[text()='Принять' and contains(@class, 'accept')]";
    // Локатор для поиска списка друзей
    String allFriends = ".//a[@class='n-t bold']";


    // Локатор для поиска кнопки "Дополнительная информация о друге"
    String additionalInfoFriend = ".//span[@class='u-menu_a toggle-dropdown']//*[contains(@class, 'more')]";
    // Локатор для кнопки "Удалить из друзей"
    String deleteFriend = ".//li[@class='u-menu_li  __custom']//a[text()='Удалить из друзей']";
    // Локатор для подтверждения удаления друга
    String acceptDelete = ".//input[@value='Прекратить']";
    // Локатор для кнопки "Закрыть", появляющейся после удаления друга
    String acceptInfo = ".//input[@value='Закрыть']";
    // Локатор для вкладки "Подписки"
    String friendSubscriptions = ".//a[contains(@href, 'subscriptions')]";
    // Локатор для поиска всех имен подписчиков
    String allSubs = ".//a[@class='n-t bold']";
    // Локатор для поиска кнопки "Отписаться"
    String unsubscribe = ".//a[text()='Отписаться' and contains(@class, 'lvl2')]";
}