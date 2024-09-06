package org.gattolfo.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Question {

    private String text;
    private Map<String,Answer> answerMap;
    private final String idCorrect;

    public Question(final String text, final Map<String,Answer> answerMap, final String idCorrect) {
        this.text = text;
        this.answerMap = answerMap;
        this.idCorrect = idCorrect;
    }

    public String getText(){
        return text;
    }

    public  boolean checkAnswer(Answer id){
        return checkAnswer(id.getId());
    }
    public boolean checkAnswer(String id){
        return id.equals(idCorrect);
    }
    public Map<String,Answer> getAnswerMap(){
        return answerMap;
    }

    public String getIdCorrect() {
        return idCorrect;
    }
}
