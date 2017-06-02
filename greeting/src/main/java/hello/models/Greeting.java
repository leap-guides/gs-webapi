package hello.models;

public class Greeting {

    private final String id;
    private final String message;

    public Greeting(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}