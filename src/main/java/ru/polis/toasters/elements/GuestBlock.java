package ru.polis.toasters.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class GuestBlock {

    private final SelenideElement root = $("div#hook_Block_UserGuests");

    public SelenideElement getSelenideBlock() {
        return root;
    }

    public List<GuestCard> getGuestCard() {
        ElementsCollection guestCards = root.$$(byXpath(".//div[@class=\"portlet_b\"]/.//div[@class=\"user-grid-card\"]"));
        List<GuestCard> res = new ArrayList<>();
        for (SelenideElement s : guestCards) {
            res.add(new GuestCard(s));
        }
        return res;
    }

}
