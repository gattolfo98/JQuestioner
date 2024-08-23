package org.gattolfo.gui;

import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class Screen {

    public abstract void initView();
    public abstract Parent getView();
}
