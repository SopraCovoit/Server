package model.jsonFactory;

import model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */

public class factoryUser extends factory<User> {

    @Override
    public User jsonToObject(JSONObject json) {
        try {
            try{
                json.getString(password);
            }catch(JSONException e){
                return new User(json.getString(name),
                        json.getString(surname),
                        json.getInt(id),
                        json.getString(mail),
                        json.getString(phone),
                        json.getBoolean(isDriver),
                        json.getInt(workplace),
                        "");
            }
            return new User(json.getString(name),
                    json.getString(surname),
                    json.getInt(id),
                    json.getString(mail),
                    json.getString(phone),
                    json.getBoolean(isDriver),
                    json.getInt(workplace),
                    json.getString(password));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject objectToJson(User object) {
        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(name,object.getName());
            jsonToReturn.put(surname,object.getSurname());
            jsonToReturn.put(id,object.getId());
            jsonToReturn.put(mail,object.getMail());
            jsonToReturn.put(phone,object.getPhone());
            jsonToReturn.put(isDriver,object.isDriver());
            jsonToReturn.put(workplace,object.getWorkplaceId());
            jsonToReturn.put(password,object.getPassWord());
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
