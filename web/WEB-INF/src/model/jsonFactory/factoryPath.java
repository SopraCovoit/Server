package model.jsonFactory;

import model.Location;
import model.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class factoryPath extends factory<Path> {

    @Override
    public Path jsonToObject(JSONObject json) {
        try {

            return new Path(new Location(json.getDouble(JsonKey.latitude),json.getDouble(JsonKey.longitude)),
                    json.getString(JsonKey.departure_hour),
                    json.getInt(JsonKey.workplace),
                    json.getString(JsonKey.direction),
                    json.getInt(JsonKey.user_id),
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
            jsonToReturn.put(JsonKey.latitude,object.getLocation().getLatitude());
            jsonToReturn.put(JsonKey.longitude,object.getLocation().getLongitude());
            jsonToReturn.put(JsonKey.departure_hour,object.getDepartureHour());
            jsonToReturn.put(JsonKey.workplace,object.getWorkPlaceId());
            jsonToReturn.put(JsonKey.direction,object.getDirection());
            jsonToReturn.put(JsonKey.user_id,object.getUserId());
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
