package ru.polis.toasters.pages;

import com.codeborne.selenide.*;
import ru.polis.toasters.elements.GuestBlock;
import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.elements.ToolbarRight;

public class GuestPage {

    private final Toolbar toolbar = new Toolbar();

    public Toolbar getToolbars() {
        return toolbar;
    }

    private final GuestBlock guestBlock = new GuestBlock();

    public GuestBlock getGuestBlock() {
        return guestBlock;
    }

    private final ToolbarRight toolbarRight = new ToolbarRight();

    public ToolbarRight getToolbarRight() {
        return toolbarRight;
    }

    public GuestPage() {
        guestBlock.getSelenideBlock().shouldBe(Condition.visible);
    }

}
