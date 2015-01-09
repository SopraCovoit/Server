package controller;

import model.StatusedMessage;
import model.User;
import model.dao.DAOUser;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryUser;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;
import utils.TokenList;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class UserController extends AbstractController {

    DAOUser daoUs;
    FactoryUser facUs;
    FactoryError facEr;

    public UserController() {
        daoUs = new DAOUser();
        facUs = new FactoryUser();
        facEr = new FactoryError();

    }

    public String getResponseFromResquest(HttpServletRequest request) {

        String json = null;
        if (request.getParameter(id) != null) {
            json = facUs.objectToJson(daoUs.find(Long.parseLong(request.getParameter(JsonKey.id)))).toString();
        } else {
            json = facUs.arrayListToJson(daoUs.findAll()).toString();
        }
        return json;
    }

    public String postResponseFromResquest(HttpServletRequest request) {
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();


        Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();

        JSONObject json = null;
        try {
            json = new JSONObject(entry.getKey());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
            User newUser = daoUs.create(facUs.jsonToObject(json));
            newUser.setToken(TokenList.getNewToken());
        return facUs.objectToJson(newUser).toString();
    }

    public String deleteResponseFromResquest(HttpServletRequest request) {
        String json = null;
        boolean success = false;
        if (request.getParameter(id) != null) {
            success = daoUs.delete(daoUs.find(Long.parseLong(request.getParameter(id))));
        }
        if (success) {
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.FAILURE_DELETE_USER)).toString();
        } else {

        }

        return json;
    }

    public String putResponseFromResquest(HttpServletRequest request) {
        String retStr;
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();


        Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();

        JSONObject json = null;
        try {
            json = new JSONObject(entry.getKey());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        boolean isUpdated = daoUs.update(facUs.jsonToObject(json));
        if (isUpdated) {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.FAILURE_PUT_USER)).toString();
        }
        else {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_PUT_USER)).toString();
        }
        return retStr;
    }
}
