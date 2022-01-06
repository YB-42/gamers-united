package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Answer {
    String text;
    String userEmail;
    LocalDateTime time;
    int id;
    public Answer(int ansID, String text, String userEmail, LocalDateTime time) {
        this.id=ansID;
        this.text = text;
        this.userEmail = userEmail;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
