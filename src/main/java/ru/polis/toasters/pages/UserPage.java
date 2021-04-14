package ru.polis.toasters.pages;

import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.elements.ToolbarRight;


public class UserPage {

    private final Toolbar toolbar = new Toolbar();

    public Toolbar getToolbars() {
        return toolbar;
    }

    private final ToolbarRight toolbarRight = new ToolbarRight();

    public ToolbarRight getToolbarRight() {
        return toolbarRight;
    }

}