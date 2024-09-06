package org.gattolfo.gui;

import atlantafx.base.layout.DeckPane;
import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class QuestionEditorHomeScreen extends Screen{

    private final ScreenManager manager;
    private VBox root;
    public QuestionEditorHomeScreen(ScreenManager manager) {
        this.manager = manager;
    }

    @Override
    public void initView() {
        root = new VBox();
        root.setAlignment(Pos.TOP_LEFT);
        root.setSpacing(10);

        root.setPadding(new Insets(0, 10, 10, 10));
        root.getChildren().add(makeTopBar());
        root.getChildren().add(makeEditOptionTabPane());
        root.getChildren().add(bottomBar());
    }

    public HBox bottomBar(){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        Button backButton = new Button("Back");
        backButton.setOnAction(e->{
            manager.changeScreen(new WelcomeScreen(manager));
        });
        hbox.getChildren().add(backButton);

        return hbox;
    }

    private Node makeTopBar(){

        GridPane grid = new GridPane();



        Label title = new Label("Question Editor");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        dropShadow.setColor(Color.GRAY);
        title.setEffect(dropShadow);
        title.setPadding(new Insets(16,0,0,0));

        ColumnConstraints columnConstraints = new ColumnConstraints();

        columnConstraints.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(columnConstraints);
        grid.add(title, 0, 0);
        return grid;
    }


    private TabPane makeEditOptionTabPane(){
        TabPane tabPane = new TabPane();
        Tab newSetTab = new Tab("New Set",buildNewSetNode());
        newSetTab.setClosable(false);
        Tab editSetTab = new Tab("Edit Set");
        editSetTab.setClosable(false);
        tabPane.getTabs().add(newSetTab);
        tabPane.getTabs().add(editSetTab);
        return tabPane;
    }

    private Node buildNewSetNode(){
        StackPane stackPane = new StackPane();
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_LEFT);
        box.setMaxWidth(600);
        box.setPrefWidth(600);
        box.setFillWidth(false);


        Label nameText = new Label("Set Name");
        nameText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        nameText.setPadding(new Insets(0, 0, 16, 0));
        box.getChildren().add(nameText);

        HBox boxName = new HBox();
        boxName.setPadding(new Insets(0,0,0,16));
        TextField nameField = new TextField();
        nameField.setPrefWidth(600);
        nameField.setPromptText("Name");
        boxName.getChildren().add(nameField);
        box.getChildren().add(boxName);

        Label creatorText = new Label("Creator name");
        creatorText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        creatorText.setPadding(new Insets(32, 0, 16, 0));
        box.getChildren().add(creatorText);

        HBox boxCreator = new HBox();
        boxCreator.setPadding(new Insets(0,0,0,16));
        TextField creatorField = new TextField();
        creatorField.setPrefWidth(600);
        creatorField.setPromptText("Creator name");
        boxCreator.getChildren().add(creatorField);
        box.getChildren().add(boxCreator);


        Label descriptionLabel = new Label("Description");
        descriptionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        descriptionLabel.setPadding(new Insets(32, 0, 16, 0));
        box.getChildren().add(descriptionLabel);

        HBox descriptionBox = new HBox();
        descriptionBox.setPadding(new Insets(0,0,0,16));
        TextArea descriptionText = new TextArea();
        descriptionText.setPrefSize(600,200);
        descriptionText.setPromptText("Description");
        descriptionBox.getChildren().add(descriptionText);
        box.getChildren().add(descriptionBox);

        HBox confirmButtonBox = new HBox();
        confirmButtonBox.setAlignment(Pos.CENTER_RIGHT);
        confirmButtonBox.setPadding(new Insets(32,0,0,0));
        Button confirmButton = new Button("confirm");
        confirmButton.setOnAction(e -> {
            manager.changeScreen(new QuestionEditorScreen(manager, nameField.getText(), creatorField.getText(), descriptionText.getText(), new ArrayList<>()));
        });
        confirmButtonBox.getChildren().add(confirmButton);
        box.getChildren().add(confirmButtonBox);

        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.setMaxWidth(600);
        container.setPadding(new Insets(32,0,0,0));
        container.getChildren().addAll(box,confirmButtonBox);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll( container);
        return stackPane;
    }

    @Override
    public Parent getView() {
        return root;
    }
}
