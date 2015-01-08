package model.jsonFactory;

import model.StatusedMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class FactoryError extends Factory<StatusedMessage> {



    @Override
    public StatusedMessage jsonToObject(JSONObject json) {
        try {
            return new StatusedMessage(json.getInt(JsonKey.id),json.getString(JsonKey.message));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(StatusedMessage object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(JsonKey.id,object.getId());
            jsonToReturn.put(JsonKey.message,object.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToReturn;
    }

    @Override
    public JSONArray arrayListToJson(ArrayList<StatusedMessage> list) {
        return null;
    }
}
