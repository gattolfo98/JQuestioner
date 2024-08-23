package org.gattolfo.element;

import java.util.ArrayList;
import java.util.Map;

public class OpenQuestion extends Question{

    public OpenQuestion(String text, ArrayList<String> images, Map<String, Answer> answerMap, String idCorrect) {
        super(text, images, answerMap, idCorrect);
    }

}
