package model.jsonFactory;

import model.Location;
import model.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class FactoryPath extends Factory<Path> {

    @Override
    public Path jsonToObject(JSONObject json) {
        try {

            return new Path(new Location(json.getDouble(latitude),json.getDouble(longitude)),
                    json.getString(departure_hour),
                    json.getInt(workplace),
                    json.getString(direction),
                    json.getInt(user_id),
                    0);//PATH ID A 0 ?
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(Path object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(latitude,object.getLocation().getLatitude());
            jsonToReturn.put(longitude,object.getLocation().getLongitude());
            jsonToReturn.put(departure_hour,object.getDepartureHour());
            jsonToReturn.put(workplace,object.getWorkPlaceId());
            jsonToReturn.put(direction,object.getDirection());
            jsonToReturn.put(user_id,object.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToReturn;
    }

    @Override
    public JSONArray arrayListToJson(ArrayList<Path> list) {
        JSONArray jsonToReturn = new JSONArray();
        for(int i = 0;i<list.size();i++){
            jsonToReturn.put(objectToJson(list.get(i)));
        }
        return jsonToReturn;
    }
}
