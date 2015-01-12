package controller;

import model.StatusedMessage;
import model.User;
import model.dao.DAOUser;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryUser;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;
import utils.TokenList;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class UserController extends AbstractController {

    DAOUser daoUs;
    DAOWorkplace daoWp;
    FactoryUser facUs;
    FactoryError facEr;


    public UserController() {
        daoUs = new DAOUser();
        daoWp = new DAOWorkplace();
        facUs = new FactoryUser();
        facEr = new FactoryError();

    }

    public String getResponseFromResquest(HttpServletRequest request) {


        String json = null;
        if (request.getParameter(JsonKey.id) != null) {
            json = facUs.objectToJson(daoUs.find(Long.parseLong(request.getParameter(JsonKey.id)))).toString();
        } else {
            json = facUs.arrayListToJson(daoUs.findAll()).toString();
        }
        if(json != null){
            return json;
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.FAILURE_GET_USER)).toString();
        }
    }

    public String postResponseFromResquest(HttpServletRequest request) {

        User newUser = null;
        if(isWorkplaceValid(request)){
            try{
                newUser = daoUs.create(facUs.jsonToObject(getJsonFromRequest(request)));
                newUser.setToken(TokenList.getNewToken());
                newUser.setId(daoUs.findByMail(newUser.getMail()).getId());
            }catch(NullPointerException e){
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.FAILURE_POST_USER)).toString();
            }

            if(facUs.objectToJson(newUser).toString() == null){
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.FAILURE_POST_USER)).toString();
            }else{
                return facUs.objectToJson(newUser).toString();
            }
        }
        return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.NO_SUCH_WORKPLACE)).toString();
    }

    private boolean isWorkplaceValid(HttpServletRequest request){
        boolean ret = false;
        try{
            JSONObject json = getJsonFromRequest(request);
            if(daoWp.find(json.getJSONObject(JsonKey.workplace).getInt(JsonKey.id)) != null){
                ret = true;
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return ret;
    }

    public String deleteResponseFromResquest(HttpServletRequest request) {
        String json = null;

        if (request.getParameter(id) != null) {
            User userToDelete = daoUs.find(Long.parseLong(request.getParameter(id)));
            if(userToDelete == null){
                json = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_DELETE_USER)).toString();
            }else{
                daoUs.delete(userToDelete);
                json = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.SUCCESS_DELETE_USER)).toString();
            }
        }

        return json;
    }

    public String putResponseFromResquest(HttpServletRequest request) {
        String retStr;

        ServletInputStream inputStream = null;
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
