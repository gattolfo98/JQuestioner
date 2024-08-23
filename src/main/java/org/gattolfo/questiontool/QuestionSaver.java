package org.gattolfo.questiontool;

import org.gattolfo.element.Answer;
import org.gattolfo.element.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class QuestionSaver {
    private final List<Question> questions;
    private Document document;
    public QuestionSaver(List<Question> questions) {
        this.questions = questions;
    }

    public boolean buildDocument(String questionName, String username) {
        if(questions == null || questions.isEmpty()) return false;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            Element rootElement = document.createElement("questions");
            rootElement.setAttribute("name", questionName);
            document.appendChild(rootElement);
            for(Question question : questions) {
                Element questionElement = document.createElement("question");
                addQuestionBody(document,question,questionElement);
                Element answerElement = document.createElement("answers");
                answerElement.setAttribute("correctOne",question.getIdCorrect());
                addAnswers(document,question.getAnswerMap(),answerElement);
                questionElement.appendChild(answerElement);
                rootElement.appendChild(questionElement);
            }
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(questionName+".xml"));
            transformer.transform(source,result);
            System.out.println("creatosos");
        } catch (ParserConfigurationException e) {
            return false;
        } catch (TransformerException ex) {
            return false;
        }
        return true;
    }

    private void addQuestionBody(Document document,Question question,Element questionElement) {
        Element questionBody = document.createElement("body");
        questionElement.appendChild(questionBody);
        if(question.getText() != null){
            Element textElement = document.createElement("questionTitle");
            textElement.appendChild(document.createTextNode(question.getText()));
            questionBody.appendChild(textElement);
        }
        for(String imgPath : question.getImages()){
            Element imageElement = document.createElement("questionImage");
            imageElement.appendChild(document.createTextNode(imgPath));
            questionBody.appendChild(imageElement);
        }
    }

    private void addAnswers(Document document, Map<String,Answer> answers, Element answersElement) {
        for(Map.Entry<String,Answer> entry : answers.entrySet()){
            Element answerElement = document.createElement("answer");
            answerElement.setAttribute("type", (entry.getValue().isImage())? "image":"text");
            answerElement.setAttribute("id", entry.getKey());
            answerElement.appendChild(document.createTextNode(entry.getValue().getAnswer()));
            answersElement.appendChild(answerElement);
        }
    }
}
