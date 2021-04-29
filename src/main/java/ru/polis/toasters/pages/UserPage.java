package ru.polis.toasters.pages;

import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.elements.ToolbarRight;


public class UserPage {

    private Toolbar toolbar = new Toolbar();

    public Toolbar getToolbars() {
        return toolbar;
    }

    private ToolbarRight toolbarRight = new ToolbarRight();

    public ToolbarRight getToolbarRight() {
        return toolbarRight;
    }

}