package ru.polis.toasters.pages;

import com.codeborne.selenide.CollectionCondition;
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
    public void goToFriendRequests(String friendRequests) {
        $(byXpath(friendRequests)).click();
    }

    // Ищет кнопку "Принять заявку в друзья"
    public void acceptClick(String acceptButton) {
        $(byXpath(acceptButton)).click();
    }

    // Ищет всех друзей данного пользователя
    public List<SelenideElement> findFriends() {
        return $$(byXpath(".//a[@class='n-t bold']"));
    }

    // Ищет друга в коллекции друзей
    public void findFriend(List<SelenideElement> friends, String user) {
        for (SelenideElement friend : friends) {
            if (friend.toString().contains(user)) {
                friend.click();
                break;
            }
        }
    }

    // Клик по кнопке с дополнительной информацией
    public void clickAddInfo() {
        String additionalInfoFriend = ".//span[@class='u-menu_a toggle-dropdown']//*[contains(@class, 'more')]";
        SelenideElement additionalFriendInfo = $(byXpath(additionalInfoFriend)).shouldBe(Condition.appear, Duration.ofSeconds(10));
        additionalFriendInfo.click();
    }

    // Ищет вкладку "Подписки"
    public void goToFriendSubscriptions(String friendSubscriptions) { $(byXpath(friendSubscriptions)).click();}

    // Ожидание загрузки вкладки "Входящие заявки в друзья"
    public void waitAccept() {
        String waitAccept = ".//div[contains(text(), 'Входящие заявки в друзья')]";
        $(byXpath(waitAccept)).shouldBe(Condition.appear, Duration.ofSeconds(10));
    }

    // Ожидание загрузки вкладки "Друзья"
    public void waitFriends() {
        String waitFriends = ".//div[@id='middleColumn']";
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
        return $$(byXpath(allSubs)).shouldBe(CollectionCondition.sizeGreaterThan(-1));
    }

    // Получает количество совпадений имени друга со всеми друзьями
    public int countFriend(List<SelenideElement> friends, String user) {
        int cnt = 0;
        for (SelenideElement friend : friends) {
            if (friend.text().equals(user)) {
                cnt++;
            }
        }
        return cnt;
    }

    // Получает выбранного подписчика пользователя из списка подписчиков пользователя
    public void getSub(List<SelenideElement> mySubs, String name) {
        for (SelenideElement mySub : mySubs) {
            if (mySub.toString().contains(name)) {
                mySub.click();
                break;
            }
        }
    }
}
