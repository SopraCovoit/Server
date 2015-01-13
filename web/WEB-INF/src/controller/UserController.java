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

import javax.servlet.http.HttpServletRequest;

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


        String json;
        json = facUs.arrayListToJson(daoUs.findAll()).toString();
        if(json == null) {
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX, StatusedMessage.FAILURE_GET_USER)).toString();
        }
        return json;
    }

    public String postResponseFromResquest(HttpServletRequest request) {

        User newUser = null;
        JSONObject requestInString = getJsonFromRequest(request);

        if(isWorkplaceValid(requestInString)){
            try{
                newUser = daoUs.create(facUs.jsonToObject(requestInString));
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

    @Override
    public String deleteResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    private boolean isWorkplaceValid(JSONObject request){
        boolean ret = false;
        try{
            JSONObject json = request;
            if(daoWp.find(json.getJSONObject(JsonKey.workplace).getInt(JsonKey.id)) != null){
                ret = true;
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return ret;
    }

    public String putResponseFromResquest(HttpServletRequest request) {
        return null;
    }
}
