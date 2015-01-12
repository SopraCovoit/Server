package model.jsonFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */

public abstract class Factory<T> {


    public abstract T jsonToObject(JSONObject json) throws JSONException;

    public abstract JSONObject objectToJson(T object);

    public abstract JSONArray arrayListToJson(ArrayList<T> list);


}
