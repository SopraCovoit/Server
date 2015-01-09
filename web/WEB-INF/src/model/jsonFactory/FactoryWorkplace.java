package model.jsonFactory;

import model.Location;
import model.Workplace;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class FactoryWorkplace extends Factory<Workplace> {

    @Override
    public Workplace jsonToObject(JSONObject json) {
        try {

            return new Workplace(new Location(json.getDouble(JsonKey.latitude),json.getDouble(JsonKey.longitude)),
                    json.getInt(JsonKey.id),
                    json.getString(JsonKey.name));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(Workplace object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(JsonKey.latitude,object.getLocation().getLatitude());
            jsonToReturn.put(JsonKey.longitude,object.getLocation().getLongitude());
            jsonToReturn.put(JsonKey.id,object.getId());
            jsonToReturn.put(JsonKey.name,object.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToReturn;
    }

    @Override
    public JSONArray arrayListToJson(ArrayList<Workplace> list) {
        JSONArray jsonToReturn = new JSONArray();
        for(int i = 0;i<list.size();i++){
            jsonToReturn.put(objectToJson(list.get(i)));
        }
        return jsonToReturn;
    }
}
