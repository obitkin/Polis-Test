import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    String baseUrl = "https://ok.ru/";

    private SelenideElement login = $(By.id("field_email"));
    private SelenideElement password = $(By.id("field_password"));
    private SelenideElement enter = $(By.xpath("//input[@value='Войти в Одноклассники']"));

    public LoginPage() {
        open(baseUrl);
    }

    public SelenideElement getLogin() {
        return login;
    }

    public SelenideElement getPassword() {
        return password;
    }

    public SelenideElement getEnter() {
        return enter;
    }

    public UserPage loginMe(String login, String password) {
        getLogin().setValue(login);
        getPassword().setValue(password);
        getEnter().click();
        return new UserPage();
    }
}
