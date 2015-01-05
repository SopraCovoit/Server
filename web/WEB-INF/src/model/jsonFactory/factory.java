package model.jsonFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */

public abstract class factory<T> {

    /*
    JSON USER
     */

    protected String id = "id";
    protected String name="name";
    protected String surname="surname";
    protected String mail="mail";
    protected String phone="phone";
    protected String isDriver="isDriver";
    protected String workplace="workplace";
    protected String password="password";

    /*
    JSON PATH
     */
    protected String latitude = "latitude";
    protected String longitude = "longitude";
    protected String departure_hour = "departure_hour";
    protected String direction = "direction";
    protected String user_id = "user_id";

    /*
    JSON WORKPLACE
     */
    //every field already before

    /*
    JSON ERROR
     */
    protected String type = "type";
    protected String message = "message";

    public abstract T jsonToObject(JSONObject json);

    public abstract JSONObject objectToJson(T object);

    public abstract JSONArray arrayListToJson(ArrayList<T> list);


}
