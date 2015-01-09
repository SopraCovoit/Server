package model.jsonFactory;

import model.Location;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 09/01/2015.
 */
public class FactoryLocation extends Factory<Location>  {

    @Override
    public Location jsonToObject(JSONObject json) {
        try {
            return new Location(json.getDouble(JsonKey.latitude),json.getDouble(JsonKey.longitude));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(Location object) {
        JSONObject json = new JSONObject();
        try {
            json.put(JsonKey.latitude,object.getLatitude());
            json.put(JsonKey.longitude,object.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public JSONArray arrayListToJson(ArrayList<Location> list) {
        return null;
    }
}
