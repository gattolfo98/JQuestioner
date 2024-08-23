package org.gattolfo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import atlantafx.base.controls.ModalPane;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.gattolfo.element.OpenQuestion;
import org.gattolfo.element.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionEditorScreen extends Screen{

    private final ScreenManager manager;

    private String setName;
    private String creatorName;
    private String setDescription;
    private StackPane root;
    private List<Question> questionList;

    public QuestionEditorScreen(ScreenManager manager, String setName, String creatorName, String setDescription, List<Question> questionList) {
        this.manager = manager;
        this.setName = setName;
        this.creatorName = creatorName;
        this.setDescription = setDescription;
        this.questionList = questionList;
    }

    @Override
    public void initView() {

        root = new StackPane();
        VBox vbox = new VBox();

        HBox titleBox = new HBox();
        titleBox.setPadding(new Insets(10,10,10,10));
        Label title = new Label("Set editor");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        dropShadow.setColor(Color.GRAY);
        title.setEffect(dropShadow);
        titleBox.getChildren().add(title);
        vbox.getChildren().add(titleBox);
        vbox.getChildren().add(buildUpperPart());

        newQuestionBox = new ModalPane();
        //newQuestionBox.setPersistent(true);
        root.getChildren().add(newQuestionBox);

        vbox.getChildren().add(buildSeparator());
        vbox.getChildren().add(buildSearchPart());
        vbox.getChildren().add(buildSeparator());
        vbox.getChildren().add(buildAddButtonPart());
        vbox.getChildren().add(buildQuestionListPart());

        root.getChildren().add(vbox);
    }

    private Parent buildSeparator(){
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setPadding(new Insets(16,16,16,16));
        return separator;
    }

    private Parent buildUpperPart(){
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10,10,10,16));


        VBox nameBox = new VBox();
        Label setNameLabel = new Label("Set Name");
        nameBox.getChildren().add(setNameLabel);

        HBox setNameFieldBox = new HBox();
        setNameFieldBox.setPadding(new Insets(0, 16, 0, 0));
        TextField setNameField = new TextField();
        setNameField.setText(this.setName);

        setNameFieldBox.getChildren().add(setNameField);
        nameBox.getChildren().add(setNameFieldBox);
        root.getChildren().add(nameBox);


        VBox creatorNameBox = new VBox();
        creatorNameBox.setPadding(new Insets(0,16,0,0));
        Label creatorNameLabel = new Label("Creator Name");
        creatorNameBox.getChildren().add(creatorNameLabel);
        HBox creatorNameFieldBox = new HBox();
        creatorNameFieldBox.setPadding(new Insets(0, 0, 0, 16));
        TextField creatorNameField = new TextField();
        creatorNameField.setText(this.creatorName);
        creatorNameFieldBox.getChildren().add(creatorNameField);
        creatorNameBox.getChildren().add(creatorNameFieldBox);
        root.getChildren().add(creatorNameBox);

        VBox descriptionBox = new VBox();
        Label descriptionLabel = new Label("Description");
        descriptionBox.getChildren().add(descriptionLabel);
        HBox descriptionTextBox = new HBox();
        descriptionTextBox.setPadding(new Insets(0, 0, 0, 16));
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setPrefSize(400, 100);
        descriptionTextArea.setText(this.setDescription);
        descriptionTextBox.getChildren().add(descriptionTextArea);
        descriptionBox.getChildren().add(descriptionTextBox);
        root.getChildren().add(descriptionBox);

        return root;
    }


    private Parent buildSearchPart(){
        VBox root = new VBox();
        root.setPadding(new Insets(16,0,0,32));

        Label searchLaber = new Label("Search");
        root.getChildren().add(searchLaber);

        HBox searchdBox = new HBox();
        searchdBox.setSpacing(10);
        TextField searchField = new TextField();

        searchdBox.getChildren().add(searchField);
        root.getChildren().add(searchdBox);

        Button searchButton = new Button("sburro");
        searchdBox.getChildren().add(searchButton);

        return root;
    }

    private Parent buildAddButtonPart(){
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER_RIGHT);
        Button addButton = new Button("Add");
        root.getChildren().add(addButton);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    newQuestionBox.show(buildModalPane());
            }

        });

        return root;
    }

    private Parent buildQuestionListPart(){
        VBox root = new VBox();

        VBox questionBox;
        for(Question question : questionList){
            questionBox = new VBox();
            Label titleQuestion = new Label(question.getText());
            questionBox.getChildren().add(titleQuestion);
            root.getChildren().add(questionBox);
        }
        return root;
    }

    ModalPane newQuestionBox = null;
    private Parent buildModalPane(){
        Dialog dialog = new Dialog(450,450);

        Label title = new Label("New Question");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        dialog.getChildren().add(title);

        VBox insideBox = new VBox();
        insideBox.setPadding(new Insets(16,16,15,16));
        dialog.getChildren().add(insideBox);

        VBox questionTitle = new VBox();
        Label questionLabel = new Label("Title:");
        questionTitle.getChildren().add(questionLabel);
        insideBox.getChildren().add(questionTitle);
        VBox questionTitleBox = new VBox();
        questionTitleBox.setPadding(new Insets(0, 8, 0, 16));
        TextField questionTitleField = new TextField();
        questionTitleBox.getChildren().add(questionTitleField);
        questionTitle.getChildren().add(questionTitleBox);


        VBox typeBox = new VBox();
        VBox openedTypeView = new VBox();

        insideBox.getChildren().add(typeBox);
        insideBox.getChildren().add(buildSeparator());

        MenuButton addButton = new MenuButton("type");
        typeBox.getChildren().add(addButton);
        MenuItem openItem = new MenuItem("Open");
        Parent openItemView = buildOpenNewQuestionBox();
        openItem.setOnAction(e ->{
            System.out.println("open");
            addButton.setText("Open");
            openedTypeView.getChildren().clear();
            openedTypeView.getChildren().add(openItemView);
        });
        addButton.getItems().add(openItem);
        MenuItem tfItem = new MenuItem("True or False");
        Parent tfQuestionBox = buildTrueFalseQuestionBox();
        tfItem.setOnAction(e->{
            System.out.println("True or False");
            addButton.setText("True or False");
            openedTypeView.getChildren().clear();
            openedTypeView.getChildren().add(tfQuestionBox);
        });
        addButton.getItems().add(tfItem);
        var multipleItem = new MenuItem("Multiple");
        multipleItem.setOnAction(e->{
            System.out.println("Multiple");
            addButton.setText("Multiple");
        });
        addButton.getItems().add(multipleItem);
        insideBox.getChildren().add(openedTypeView);

        VBox insertButtonBox = new VBox();
        insertButtonBox.setFillWidth(true);
        VBox.setVgrow(insertButtonBox, Priority.ALWAYS);
        insertButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        Button insertButton = new Button();
        insertButton.setText("confirm");
        insertButton.setOnAction(e->{
            if(openedTypeView == openItemView){
                if(!openQuestionCorrectOne.getText().isBlank()){

                    Question newQuestion = new OpenQuestion(questionTitleField.getText(), new ArrayList<>(),new HashMap<>(),openQuestionCorrectOne.getText());e
                }
            }else if(openedTypeView == tfQuestionBox){
                if(toggleGroup.getSelectedToggle() != null){
                    //TODO: aggiungi la questione
                }
            }
        });
        insertButtonBox.getChildren().add(insertButton);
        insideBox.getChildren().add(insertButtonBox);

        return dialog;
    }


    TextField openQuestionCorrectOne;
    private Parent buildOpenNewQuestionBox(){
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER_LEFT);
        Label content = new Label("Answer:");
        contentBox.getChildren().add(content);
        VBox answerBox = new VBox();
        answerBox.setAlignment(Pos.CENTER_LEFT);
        openQuestionCorrectOne = new TextField();
        answerBox.getChildren().add(openQuestionCorrectOne);
        root.getChildren().add(contentBox);
        root.getChildren().add(answerBox);

        return root;
    }

    ToggleGroup toggleGroup;
    private Parent buildTrueFalseQuestionBox(){
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        VBox labelBox = new VBox();
        root.getChildren().add(labelBox);
        labelBox.setAlignment(Pos.CENTER_LEFT);
        Label content = new Label("Choose the correct answers:");
        labelBox.getChildren().add(content);

        VBox selecterBox = new VBox();
        root.getChildren().add(selecterBox);
        RadioButton trueButton = new RadioButton("True");
        selecterBox.getChildren().add(trueButton);
        RadioButton falseButton = new RadioButton("False");
        selecterBox.getChildren().add(falseButton);
        toggleGroup = new ToggleGroup();

        trueButton.setToggleGroup(toggleGroup);
        falseButton.setToggleGroup(toggleGroup);
        return root;
    }



    @Override
    public Parent getView() {
        return root;
    }

    private class Dialog extends VBox {

        public Dialog(int width, int height) {
            super();

            setSpacing(10);
            setAlignment(Pos.TOP_CENTER);
            setMinSize(width, height);
            setMaxSize(width, height);
            setStyle(
                    "-fx-background-color: -color-bg-default;" +
                    "  -fx-border-color: -color-border-muted;" +
                    "  -fx-background-radius: 15;" +
                    " -fx-border-radius: 15;"
            );
        }
    }
}
