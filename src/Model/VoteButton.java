package Model;

import javax.swing.*;

public class VoteButton extends JButton {
    int answerID;

    public VoteButton(String text) {
        super(text);
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }
}
