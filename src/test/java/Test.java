import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.$;

public class Test extends BaseTest{

    LoginPage loginPage;

    String login = "edr";
    String password = "erftry";

    @Before
    public void begin() {
        setup();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @org.junit.Test
    public void loginTest() {
        loginPage.loginMe(login, password);
    }

    @After
    public void end() {
        quit();
    }
}
