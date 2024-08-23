package org.gattolfo.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MultipleChoiceQuestion extends Question{



    public MultipleChoiceQuestion(String text, Map<String, Answer> answerMap, Answer correct) {
        super(text, new ArrayList<>(),answerMap, correct.getId());

    }
    public MultipleChoiceQuestion(ArrayList<String> images, Map<String, Answer> answerMap,Answer correct) {
        super("", images, answerMap, correct.getId());

    }

    public MultipleChoiceQuestion(String text, ArrayList<String> images, Map<String, Answer> answerMap, Answer correct) {
        super(text, images, answerMap, correct.getId());
    }

    @Override
    public boolean checkAnswer(Answer id) {
        return Objects.equals(id.getId(), getIdCorrect());
    }

    @Override
    public boolean checkAnswer(String id) {
        return Objects.equals(id, getIdCorrect());
    }

}
