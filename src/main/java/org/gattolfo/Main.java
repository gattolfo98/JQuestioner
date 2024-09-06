package org.gattolfo;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.gattolfo.element.Answer;
import org.gattolfo.element.MultipleChoiceQuestion;
import org.gattolfo.element.Question;
import org.gattolfo.gui.ScreenManager;
import org.gattolfo.questiontool.QuestionSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private ScreenManager screenManager;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(900);
        stage.setHeight(800);
        screenManager = new ScreenManager(stage);
        screenManager.start();
    }


    static class TextAnswer extends Answer {
        private final String id;
        private final String answer;

        public TextAnswer(String id, String answer) {
            this.id = id;
            this.answer = answer;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getAnswer() {
            return answer;
        }

    }

    // Metodo per testare QuestionSaver
    public void testQuestionSaver(String questionName, String username) {
        // Creare le risposte
        Answer answer1 = new TextAnswer("1", "Risposta A");
        Answer answer2 = new TextAnswer("2", "Risposta B");
        Answer answer3 = new TextAnswer("3", "Risposta C");

        // Mappa delle risposte
        Map<String, Answer> answerMap = new HashMap<>();
        answerMap.put(answer1.getId(), answer1);
        answerMap.put(answer2.getId(), answer2);
        answerMap.put(answer3.getId(), answer3);

        // Creare una domanda
        Question question = new MultipleChoiceQuestion("Qual è la capitale d'Italia?", answerMap, answer1);

        // Lista delle domande
        List<Question> questions = new ArrayList<>();
        questions.add(question);

        // Istanziare QuestionSaver e costruire il documento
        QuestionSaver questionSaver = new QuestionSaver(questions);
        boolean success = questionSaver.buildDocument(questionName, username);

        if (success) {
            System.out.println("Documento XML creato con successo!");
        } else {
            System.out.println("Errore nella creazione del documento XML.");
        }
    }

}