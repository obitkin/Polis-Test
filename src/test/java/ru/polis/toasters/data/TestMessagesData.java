package ru.polis.toasters.data;

public interface TestMessagesData {
    // Данные бота 1
    String user1 = "89995632015";
    String userName1 = "Карл Дёниц";
    String password1 = "Kirill11";

    // Данные бота 2
    String user2 = "89944056284";
    String userName2 = "Георгий Второй";
    String password2 = "Kirill2";

    // Кол-во смайликов в отправленном сообщении
    int smileCnt = 3;
    // Изображение смайлика
    String smile = "\uD83D\uDE02";

    // Локатор для вкладки "Сообщения"
    String messageLayer = ".//div[@id='msg_layer']";
    // Локатор для поиска бота 1
    String user1Dialog = "//msg-parsed-text[text()='" + userName1 + "']";
    // Локатор для поиска бота 2
    String user2Dialog = "//msg-parsed-text[text()='" + userName2 + "']";
    // Локатор для поиска кнопки "Смайлики"
    String smileMessageButton = ".//msg-button[@title='Смайлики']";
    // Локатор для поиска вкладки смайликов
    String smileLableButton = "//msg-l10n[text()='Смайлики']";
    // Локатор для поиска выбранного смайлика
    String smileIconButton = ".//section[@class='first']//img[@alt='" + smile + "']";
    // Локатор для поиска кнопки "Отправить сообщение"
    String sendSmilesButton = "//msg-button[@title='Отправить']";
    // Локатор для поиска кнопки с информацией о пользователе, где есть кнопка выхода из аккаунта
    String logOutButton = "//div[@class='ucard-mini toolbar_ucard js-toolbar-menu']";
    // Локатор для поиска кнопки выхода из аккаунта
    String logOutClick = "//a[text()='Выйти']";
    // Локатор для поиска кнопки подтверждения выхода из аккаунта
    String acceptLogOutClick = ".//input[@value='Выйти']";
    // Локатор для поиска всех сообщений в выбранном диалоге
    String getMessages = ".//msg-parsed-text[@class='text']";
    // Локатор для поиска элементов в сообщении
    String elementsMessages = ".//img[@alt='" + smile + "']";
}
