package model.jsonFactory;

import model.Error;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class FactoryError extends Factory<Error> {



    @Override
    public Error jsonToObject(JSONObject json) {
        try {
            return new Error(json.getInt(JsonKey.id),json.getString(JsonKey.type),json.getString(JsonKey.message));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(Error object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(JsonKey.id,object.getId());
            jsonToReturn.put(JsonKey.message,object.getMessage());
            jsonToReturn.put(JsonKey.type,object.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToReturn;
    }

    @Override
    public JSONArray arrayListToJson(ArrayList<Error> list) {
        return null;
    }
}
