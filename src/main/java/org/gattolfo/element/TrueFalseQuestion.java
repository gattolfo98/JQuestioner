package org.gattolfo.element;

import java.util.ArrayList;
import java.util.Map;

public class TrueFalseQuestion extends Question{


    public TrueFalseQuestion(String text, ArrayList<String> images, Map<String, Answer> answerMap, Boolean idCorrect) {
        super(text, images, answerMap, idCorrect.toString());
    }

    @Override
    public boolean checkAnswer(Answer id) {
        return checkAnswer(id.getId());
    }

    @Override
    public boolean checkAnswer(String id) {
        return id.equals(this.getIdCorrect());
    }
}
