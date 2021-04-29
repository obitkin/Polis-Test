package ru.polis.toasters.pages;

import ru.polis.toasters.elements.Toolbar;
import ru.polis.toasters.elements.ToolbarRight;


public class UserPage {

    private Toolbar toolbar;

    public Toolbar getToolbars() {
        return (toolbar == null) ? toolbar = new Toolbar() : toolbar;
    }

    private ToolbarRight toolbarRight;

    public ToolbarRight getToolbarRight() {
        return (toolbarRight == null) ? toolbarRight = new ToolbarRight() : toolbarRight;
    }

}