package model.jsonFactory;

import model.User;
import model.Workplace;
import model.dao.DAOPath;
import model.dao.DAOWorkplace;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import java.util.ArrayList;

/**
 * Created by julescantegril on 05/01/2015.
 */

public class FactoryUser extends Factory<User> {


    @Override
    public User jsonToObject(JSONObject json){
        User userToReturn = null;
        try {
            userToReturn = new User(json.getString(JsonKey.name),
                    json.getString(JsonKey.surname),
                    json.getString(JsonKey.mail),
                    json.getString(JsonKey.phone),
                    json.getBoolean(JsonKey.isDriver),
                    json.getJSONObject(JsonKey.workplace).getInt(JsonKey.id));
        }catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            userToReturn.setId(json.getInt(JsonKey.id));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        try {
            userToReturn.setPassWord(json.getString(JsonKey.password));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return userToReturn;
    }

    @Override
    public JSONObject objectToJson(User object) {
        DAOWorkplace daoWp = new DAOWorkplace();
        DAOPath daoPath = new DAOPath();

        FactoryWorkplace facWp = new FactoryWorkplace();
        FactoryPath facPath = new FactoryPath();

        Workplace wpUser = daoWp.find(object.getWorkplaceId());

        if(wpUser == null){
            return null;
        }

        JSONObject jsonToReturn = new JSONObject();
        try {
            jsonToReturn.put(JsonKey.name,object.getName());
            jsonToReturn.put(JsonKey.surname,object.getSurname());
            jsonToReturn.put(JsonKey.id,object.getId());
            jsonToReturn.put(JsonKey.mail,object.getMail());
            jsonToReturn.put(JsonKey.phone,object.getPhone());
            jsonToReturn.put(JsonKey.isDriver,object.isDriver());
            jsonToReturn.put(JsonKey.workplace,facWp.objectToJson(wpUser));
            jsonToReturn.put(JsonKey.path,facPath.arrayListToJson(daoPath.findAllUserPath(object.getId()),false));
            jsonToReturn.put(JsonKey.password,object.getPassWord());
            jsonToReturn.put(JsonKey.token,object.getToken());
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
