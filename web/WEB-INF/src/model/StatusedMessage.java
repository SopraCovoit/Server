package model;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class StatusedMessage {

    public final static int SUCCESS_STATUS =200;
    public final static int FAILURE_STATUS =500;
    public final static int BAD_TOKEN = 401;
    public final static int BAD_SYNTAX = 400;
    public final static int TEAPOT_BRAW = 418;


    public final static String SUCCESS_GET_WORKPLACE ="Success getting workplace";
    public final static String FAILURE_GET_WORKPLACE ="Failure getting workplace";
    public final static String SUCCESS_PUT_WORKPLACE ="Success putting workplace";
    public final static String FAILURE_PUT_WORKPLACE ="Failure putting workplace";
    public final static String SUCCESS_DELETE_WORKPLACE ="Success deleting workplace";
    public final static String FAILURE_DELETE_WORKPLACE ="Failure deleting workplace";
    public final static String SUCCESS_POST_WORKPLACE ="Success post workplace";
    public final static String FAILURE_POST_WORKPLACE ="Failure post workplace";
    public final static String SUCCESS_GET_USER ="Success getting user";
    public final static String FAILURE_GET_USER="Failure getting user";
    public final static String SUCCESS_PUT_USER ="Success putting user";
    public final static String FAILURE_PUT_USER ="Failure putting user";
    public final static String SUCCESS_DELETE_USER ="Success deleting user";
    public final static String FAILURE_DELETE_USER ="Failure deleting user";
    public final static String SUCCESS_POST_USER ="Success post user";
    public final static String FAILURE_POST_USER ="Failure post user";
    public final static String SUCCESS_GET_PATH ="Success getting path";
    public final static String FAILURE_GET_PATH="Failure getting path";
    public final static String SUCCESS_PUT_PATH ="Success putting path";
    public final static String FAILURE_PUT_PATH ="Failure putting path";
    public final static String SUCCESS_DELETE_PATH ="Success deleting path";
    public final static String FAILURE_DELETE_PATH ="Failure deleting path";
    public final static String SUCCESS_POST_PATH ="Success post path";
    public final static String FAILURE_POST_PATH ="Failure post path";

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
