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

    // Локатор для поиска кнопки "Смайлики"
    String smileMessageButton = ".//msg-button[@title='Смайлики']";
    // Локатор для поиска вкладки смайликов
    String smileLableButton = "//msg-l10n[text()='Смайлики']";
    // Локатор для поиска кнопки "Отправить сообщение"
    String sendSmilesButton = "//msg-button[@title='Отправить']";
}
