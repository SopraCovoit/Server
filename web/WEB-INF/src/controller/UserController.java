package controller;

import model.StatusedMessage;
import model.User;
import model.dao.DAOUser;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryUser;
import utils.JsonKey;
import utils.TokenList;

import javax.servlet.http.HttpServletRequest;

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

        boolean isUpdated = daoUs.update(facUs.jsonToObject(getJsonFromRequest(request)));
        if (isUpdated) {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.FAILURE_PUT_USER)).toString();
        }
        else {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_PUT_USER)).toString();
        }
        return retStr;
    }
}
