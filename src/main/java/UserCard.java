import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class UserCard {

    SelenideElement root;

    public UserCard(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.find(By.className("ellip")).getText();
    }

    public SelenideElement getMessageButton() {
        return root.find(By.className("tico"));
    }
}
