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
            return new Workplace(new Location(json.getJSONObject(JsonKey.location).getDouble(JsonKey.latitude),json.getJSONObject(JsonKey.location).getDouble(JsonKey.longitude)),
                    json.getInt(JsonKey.id),
                    json.getString(JsonKey.name));
        } catch (JSONException e) {
            try {
                return new Workplace(new Location(json.getJSONObject(JsonKey.location).getDouble(JsonKey.latitude),json.getJSONObject(JsonKey.location).getDouble(JsonKey.longitude)),
                        0,
                        json.getString(JsonKey.name));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(Workplace object) {
        JSONObject jsonToReturn = new JSONObject();
        FactoryLocation facLoc = new FactoryLocation();
        try {
            jsonToReturn.put(JsonKey.location,facLoc.objectToJson(object.getLocation()));
            jsonToReturn.put(JsonKey.id,object.getId());
            jsonToReturn.put(JsonKey.name,object.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }catch(NullPointerException e){
            return null;
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
