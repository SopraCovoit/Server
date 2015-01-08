package model.jsonFactory;

import model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */

public class factoryUser extends factory<User> {


    @Override
    public User jsonToObject(JSONObject json) {
        try {
            try{
                json.getString(JsonKey.password);
            }catch(JSONException e){
                return new User(json.getString(JsonKey.name),
                        json.getString(JsonKey.surname),
                        json.getInt(JsonKey.id),
                        json.getString(JsonKey.mail),
                        json.getString(JsonKey.phone),
                        json.getBoolean(JsonKey.isDriver),
                        json.getInt(JsonKey.workplace),
                        "");
            }
            return new User(json.getString(JsonKey.name),
                    json.getString(JsonKey.surname),
                    json.getInt(JsonKey.id),
                    json.getString(JsonKey.mail),
                    json.getString(JsonKey.phone),
                    json.getBoolean(JsonKey.isDriver),
                    json.getInt(JsonKey.workplace),
                    json.getString(JsonKey.password));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(User object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(JsonKey.name,object.getName());
            jsonToReturn.put(JsonKey.surname,object.getSurname());
            jsonToReturn.put(JsonKey.id,object.getId());
            jsonToReturn.put(JsonKey.mail,object.getMail());
            jsonToReturn.put(JsonKey.phone,object.getPhone());
            jsonToReturn.put(JsonKey.isDriver,object.isDriver());
            jsonToReturn.put(JsonKey.workplace,object.getWorkplaceId());
            jsonToReturn.put(JsonKey.password,object.getPassWord());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToReturn;
    }

    @Override
    public JSONArray arrayListToJson(ArrayList<User> list) {
        JSONArray jsonToReturn = new JSONArray();
        for(int i = 0;i<list.size();i++){
            jsonToReturn.put(objectToJson(list.get(i)));
        }
        return jsonToReturn;
    }
}
