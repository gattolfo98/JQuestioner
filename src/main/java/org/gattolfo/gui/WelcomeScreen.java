package org.gattolfo.gui;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class WelcomeScreen extends  Screen {
    private StackPane root;
    private final ScreenManager manager;
    public WelcomeScreen(ScreenManager manager){
        this.manager = manager;
    }

    @Override
    public void initView() {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        Button btn = new Button();
        btn.setText("Load Questions");
        Button btnbuilder = new Button();
        btnbuilder.setText("Questions builder");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("1");
            }
        });
        btnbuilder.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                manager.changeScreen(new QuestionEditorHomeScreen(manager));
            }
        });

        HBox box = new HBox(50);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(btn, btnbuilder);
        root = new StackPane();
        root.getChildren().add(box);
    }

    @Override
    public Parent getView() {
        return root;
    }
}
