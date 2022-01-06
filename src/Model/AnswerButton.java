package Model;

import javax.swing.*;

public class AnswerButton extends JButton {
    int questionIndex;

    public AnswerButton(int questionIndex) {
        super("Answer");
        this.questionIndex = questionIndex;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }
}
