package org.gattolfo.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrueFalseQuestion extends Question{


    public TrueFalseQuestion(String text, Boolean idCorrect) {
        super(text, new HashMap<>(), idCorrect.toString());
        getAnswerMap().put(Boolean.TRUE.toString(), new Answer() {
            @Override
            public String getId() {
                return Boolean.TRUE.toString();
            }

            @Override
            public String getAnswer() {
                return "True";
            }
        });
        getAnswerMap().put(Boolean.FALSE.toString(), new Answer() {
            @Override
            public String getId() {
                return Boolean.FALSE.toString();
            }

            @Override
            public String getAnswer() {
                return "False";
            }
        });
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
