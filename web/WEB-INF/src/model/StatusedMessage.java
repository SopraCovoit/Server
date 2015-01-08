package model;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class StatusedMessage {

    private final static String SUCCESS_MESSAGE ="Success";
    private final static int SUCCESS_STATUS =200;
    private final static String FAILURE_MESSAGE ="Failure";
    private final static int FAILURE_STATUS =500;

    int id;
    String message;

    public StatusedMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
