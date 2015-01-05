package model;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class Error {


    int id;
    String type;
    String message;

    public Error(int id, String type, String message) {
        this.id = id;
        this.type = type;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
