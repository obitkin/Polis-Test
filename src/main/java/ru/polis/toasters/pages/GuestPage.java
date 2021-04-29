package ru.polis.toasters.pages;

import com.codeborne.selenide.*;
import ru.polis.toasters.elements.GuestBlock;
import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.elements.ToolbarRight;

public class GuestPage {

    private Toolbar toolbar;

    public Toolbar getToolbars() {
        return (toolbar == null) ? toolbar = new Toolbar() : toolbar;
    }

    private GuestBlock guestBlock;

    public GuestBlock getGuestBlock() {
        return (guestBlock == null) ? guestBlock = new GuestBlock() : guestBlock;
    }

    private ToolbarRight toolbarRight;

    public ToolbarRight getToolbarRight() {
        return (toolbarRight == null) ? toolbarRight = new ToolbarRight() : toolbarRight;
    }

    public GuestPage() {
        guestBlock.getSelenideBlock().shouldBe(Condition.visible);
    }

}
