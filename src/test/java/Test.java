import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Test extends BaseTest{

    LoginPage loginPage;

    UserPage userPage;
    //GuestPage guestPage;

    String login = "";
    String password = "";


    @Before
    public void begin() {
        setup();
        loginPage = new LoginPage(driver);
        loginPage.open();
        userPage = new UserPage(driver);
        //guestPage = new GuestPage(driver);
    }

    @org.junit.Test
    public void emptyGuestTest() {
        driver = loginPage.loginMe(driver,login, password);

        userPage = new UserPage(driver);
        driver = userPage.PressGuestButtom(userPage.hookBlock, driver);

        //guestPage = new GuestPage(driver);
        //Assert.assertEquals(guestPage.guestWindow,"Ходите друг к другу в гости!Загляните в гости к друзьям, а они заглянут к вам! Мои друзья");


    }


    @After
    public void end() {
        quit();
    }
}
