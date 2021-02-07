package phone.vishnu.institute.model;

public class Message {
    private String title;
    private String message;

    public Message() {
    }

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Text1) {
        this.title = Text1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String Text2) {
        this.message = Text2;
    }
}
