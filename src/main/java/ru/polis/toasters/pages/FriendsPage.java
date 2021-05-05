package ru.polis.toasters.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byXpath;

// PageObject для страницы "Друзья"
public class FriendsPage {

    public FriendsPage() {
        waitFriends();
    }

    // Выполняет переход на страницу "Добавить друга"
    public AddFriendPage goToFriendsAdd() {
        String friendAddLocator = ".//a[@title='Найти по имени и фамилии']";
        $(byXpath(friendAddLocator)).click();
        return new AddFriendPage();
    }

    // Ищет вкладку "Входящие запросы в друзья"
    public void goToFriendRequests() {
        String friendsRequests = ".//div[contains(text(),'Заявки в друзья')]//..";
        $(byXpath(friendsRequests)).click();
    }

    // Ищет кнопку "Принять заявку в друзья"
    public void acceptClick() {
        String acceptBut = ".//span[text()='Принять' and contains(@class, 'accept')]";
        if ($(byXpath(acceptBut)).exists()) {
            $(byXpath(acceptBut)).click();
        }
    }

    // Ищет кнопку "Скрыть заявку в друзья"
    public void declineClick() {
        String declineBut = ".//span[text()='Скрыть' and contains(@class, 'decline')]";
        if ($(byXpath(declineBut)).exists()) {
            $(byXpath(declineBut)).click();
        }
    }

    // Ищет всех друзей данного пользователя
    public List<SelenideElement> findFriends() {
        String friendsCards = ".//a[@class='n-t bold']";
        return $$(byXpath(friendsCards));
    }

    // Ищет друга в коллекции друзей
    public int findFriend(List<SelenideElement> friends, String user) {
        int cnt = 0;
        for (SelenideElement friend : friends) {
            if (friend.toString().contains(user)) {
                friend.click();
                cnt++;
                break;
            }
        }
        return cnt;
    }

    // Клик по кнопке с дополнительной информацией
    public void clickAddInfo() {
        String additionalInfoFriend = ".//span[@class='u-menu_a toggle-dropdown']//*[contains(@class, 'more')]";
        SelenideElement additionalFriendInfo = $(byXpath(additionalInfoFriend)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        additionalFriendInfo.click();
    }

    // Ищет вкладку "Подписки"
    public void goToFriendSubscriptions() {
        String friendsSubs = ".//a[contains(@href, 'subscriptions')]";
        $(byXpath(friendsSubs)).click();
    }

    // Ожидание загрузки вкладки "Входящие заявки в друзья"
    public void waitAccept() {
        String waitAccept = ".//div[contains(text(), 'Заявки в друзья')]";
        $(byXpath(waitAccept)).shouldBe(Condition.appear, Duration.ofSeconds(10));
    }

    // Ожидание загрузки вкладки "Друзья"
    public void waitFriends() {
        String waitFriends = ".//div[@id='hook_Block_MyFriendsMRB']";
        $(byXpath(waitFriends)).shouldBe(Condition.appear, Duration.ofSeconds(10));
    }

    // Ожидание загрузки вкладки "Мои подписки"
    public void waitSubs() {
        String waitSubs = ".//div[text()='Мои подписки']";
        $(byXpath(waitSubs)).shouldBe(Condition.appear, Duration.ofSeconds(10));
    }

    // Удаление друга
    public void clickDeleteFriend() {
        String deleteFriend = ".//li[@class='u-menu_li  __custom']//a[text()='Удалить из друзей']";
        $(byXpath(deleteFriend)).click();
        String acceptDelete = ".//input[@value='Прекратить']";
        $(byXpath(acceptDelete)).click();
        String acceptInfo = ".//input[@value='Закрыть']";
        $(byXpath(acceptInfo)).click();
    }

    // Удаляет подписчика
    public void clickDeleteSub() {
        String unsubscribe = ".//a[text()='Отписаться' and contains(@class, 'lvl2')]";
        $(byXpath(unsubscribe)).click();
    }

    // Получает всех подписчиков пользователя
    public List<SelenideElement> getSubs() {
        String allSubs = ".//a[@class='n-t bold']";
        return $$(byXpath(allSubs));
    }

    // Получает количество совпадений имени друга со всеми друзьями
    public int countFriend(List<SelenideElement> friends, String user) {
        int cnt = 0;
        for (SelenideElement friend : friends) {
            if (friend.toString().contains(user)) {
                cnt++;
            }
        }
        return cnt;
    }

    // Получает выбранного подписчика пользователя из списка подписчиков пользователя
    public int getSub(List<SelenideElement> mySubs, String name) {
        int cnt = 0;
        for (SelenideElement mySub : mySubs) {
            if (mySub.toString().contains(name)) {
                mySub.click();
                cnt++;
                break;
            }
        }
        return cnt;
    }

    // Проверяет, существуют ли подписчики у пользователя
    public boolean subsExist() {
        String subsBlock = ".//div[@id='hook_Block_UserFriendRequestMRB']";
        $(byXpath(subsBlock)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        String subsNotExist = ".//div[contains(text(),'хотят с вами дружить')]";
        return !$(byXpath(subsNotExist)).exists();
    }
}
