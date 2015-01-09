package model.jsonFactory;

import model.Location;
import model.Path;
import model.dao.DAOUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */
public class FactoryPath extends Factory<Path> {

    @Override
    public Path jsonToObject(JSONObject json) {
        try {

            return new Path(new Location(json.getJSONObject(JsonKey.location).getDouble(JsonKey.latitude),json.getJSONObject(JsonKey.location).getDouble(JsonKey.longitude)),
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
        DAOUser daoUs = new DAOUser();
        FactoryUser facUs = new FactoryUser();
        FactoryLocation facLoc = new FactoryLocation();
        try {
            jsonToReturn.put(JsonKey.location,facLoc.objectToJson(object.getLocation()));
            jsonToReturn.put(JsonKey.departure_hour,object.getDepartureHour());
            jsonToReturn.put(JsonKey.workplace,object.getWorkPlaceId());
            jsonToReturn.put(JsonKey.direction,object.getDirection());
            jsonToReturn.put(JsonKey.user_id,facUs.objectToJson(daoUs.find(object.getUserId())));
            jsonToReturn.put(JsonKey.distance,object.getDistance());
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
