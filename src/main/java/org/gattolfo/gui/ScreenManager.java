package org.gattolfo.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {

    private final Stage stage;
    protected Screen actualScreen = null;
    public ScreenManager(final Stage stage) {
        this.stage = stage;
    }

    public void start(){
        changeScreen(new WelcomeScreen(this));
        stage.show();
    }

    public void changeScreen(final Screen screen) {
        actualScreen = screen;
        actualScreen.initView();
        stage.setScene(new Scene(actualScreen.getView()));
    }
    public Stage getStage(){
        return stage;
    }

}
