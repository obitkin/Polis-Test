
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    String baseUrl = "https://ok.ru/";

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void open() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    public WebElement getLogin() {
        return login;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getEnter() {
        return enter;
    }

    public WebDriver loginMe(WebDriver driver,String login, String password) {
        getLogin().sendKeys(login);
        getPassword().sendKeys(password);
        enter.click();
        return driver;
    }

    @FindBy(id = "field_email")
    WebElement login;

    @FindBy(id = "field_password")
    WebElement password;

    @FindBy(xpath = "//input[@value='Войти в Одноклассники']")
    WebElement enter;
}
