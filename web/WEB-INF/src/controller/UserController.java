package controller;

import model.Path;
import model.StatusedMessage;
import model.User;
import model.dao.DAOPath;
import model.dao.DAOUser;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryPath;
import model.jsonFactory.FactoryUser;
import org.json.JSONArray;
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

        DAOPath daoPt = new DAOPath();
        FactoryPath facPt = new FactoryPath();

        User newUser = null;
        JSONObject requestInString = getJsonFromRequest(request);

        if(isWorkplaceValid(requestInString)){

            //add work place if exist

            try{
                newUser = daoUs.create(facUs.jsonToObject(requestInString));
                newUser.setToken(TokenList.getNewToken());
                int goodId = daoUs.findByMail(newUser.getMail()).getId();
                newUser.setId(goodId);

                try {
                    JSONArray paths = requestInString.getJSONArray(JsonKey.path);
                    //System.out.println(paths.toString()+" la");
                    for(int i = 0;i< paths.length();i++){
                        Path pathToAdd = facPt.jsonToObject(paths.getJSONObject(i));
                        pathToAdd.setUserId(goodId);
                        daoPt.create(pathToAdd);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }catch(NullPointerException e){
                e.printStackTrace();
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.FAILURE_POST_USER)).toString();
            }

            if(facUs.objectToJson(newUser).toString() == null){
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_SYNTAX,StatusedMessage.FAILURE_POST_USER)).toString();
            }else{
               // System.out.println("y a bon");
                System.out.println(facUs.objectToJson(newUser).toString());
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
        JSONObject json = request;
        System.out.println(json.toString());
        try{
            if(daoWp.find(json.getJSONObject(JsonKey.workplace).getInt(JsonKey.id)) != null){
                return true;
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return false;
    }

    public String putResponseFromResquest(HttpServletRequest request) {
        return null;
    }
}
