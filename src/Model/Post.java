package Model;

public class Post {
    String question;
    User poster;
    String body;


    public Post(String question,User user) {
        this.question = question;
        this.poster=user;
    }

    public Post(String question, String body, User poster) {
        this.question = question;
        this.poster = poster;
        this.body = body;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
