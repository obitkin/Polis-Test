import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Test extends BaseTest implements TestData {

    LoginPage loginPage;

    @Before
    public void begin() {
        setup();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @org.junit.Test
    public void MyTest() {
        UserPage userPage = loginPage.loginMe(login, password);
        Assert.assertEquals(userPage.getCurrentUrl(), userPageURL);

        GuestPage guestPage = userPage.goToGuest();
        List<UserCard> listOfUsers = guestPage.getGuestCard();

        Assert.assertEquals(names.size(), listOfUsers.size());
        listOfUsers.forEach(x -> System.out.println(x.getName()));

        for (UserCard userCard : listOfUsers) {
            Assert.assertTrue(names.contains(userCard.getName()));
        }
    }

    @After
    public void end() {
        quit();
    }
}
