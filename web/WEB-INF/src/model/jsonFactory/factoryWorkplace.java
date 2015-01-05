package model.jsonFactory;

import model.Location;
import model.Workplace;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class factoryWorkplace extends factory<Workplace> {

    @Override
    public Workplace jsonToObject(JSONObject json) {
        try {

            return new Workplace(new Location(json.getDouble(latitude),json.getDouble(longitude)),
                    json.getInt(id),
                    json.getString(name));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(Workplace object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(latitude,object.getLocation().getLatitude());
            jsonToReturn.put(longitude,object.getLocation().getLongitude());
            jsonToReturn.put(id,object.getId());
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
