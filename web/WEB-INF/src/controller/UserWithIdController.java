package controller;

import model.StatusedMessage;
import model.User;
import model.dao.DAOUser;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryUser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by root on 12/01/15.
 */
public class UserWithIdController extends AbstractController {
    FactoryUser facUs;
    FactoryError facEr;
    DAOUser daoUs;

    public UserWithIdController() {
        daoUs = new DAOUser();
        facUs = new FactoryUser();
        facEr = new FactoryError();

    }
    @Override
    public String getResponseFromResquest(HttpServletRequest request) {
        String json;
        String id = request.getPathInfo();
        id="" + id.substring(id.lastIndexOf("/")+1, id.length());
        json = facUs.objectToJson(daoUs.find(Long.parseLong(id))).toString();
        if(json == null){
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.FAILURE_GET_USER)).toString();
        }
        return json;
    }

    @Override
    public String postResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String deleteResponseFromResquest(HttpServletRequest request) {
        String json;
        String id = request.getPathInfo();
        id="" + id.substring(id.lastIndexOf("/")+1, id.length());
        User userToDelete = daoUs.find(Long.parseLong(id));
        if(userToDelete == null){
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_DELETE_USER)).toString();
        }else{
            daoUs.delete(userToDelete);
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.SUCCESS_DELETE_USER)).toString();
        }
        return json;
    }

    @Override
    public String putResponseFromResquest(HttpServletRequest request) {
        String retStr;

        ServletInputStream inputStream;
        String entry = "" ;
        try {
            inputStream = request.getInputStream();
            int next = 0;

            while(next != -1){
                next = inputStream.read();
                entry = entry+(char)next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isUpdated = false;
        try {
            isUpdated = daoUs.update(facUs.jsonToObject(new JSONObject(entry)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isUpdated) {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.SUCCESS_PUT_USER)).toString();
        }
        else {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_PUT_USER)).toString();
        }
        return retStr;
    }
}
