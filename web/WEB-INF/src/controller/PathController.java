package controller;

import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOPath;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryPath;
import model.jsonFactory.FactoryWorkplace;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;
import utils.TokenList;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class PathController extends AbstractController {

    DAOPath daoPt;
    FactoryPath facPath;
    FactoryError facEr;
    FactoryWorkplace facWp;
    int distance = 5;

    public PathController(){
        daoPt = new DAOPath();
        facPath = new FactoryPath();
        facWp = new FactoryWorkplace();
        facEr = new FactoryError();
    }


    public String getResponseFromResquest(HttpServletRequest request){

        try {
            if (!TokenList.leverage((request.getParameter(JsonKey.token)))) {
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_TOKEN,StatusedMessage.FAILURE_GET_PATH)).toString();
            }
        }catch(NullPointerException e){
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_TOKEN,StatusedMessage.FAILURE_GET_PATH)).toString();
        }

            try {
                JSONObject workplaceJson = new JSONObject(request.getParameter(JsonKey.workplace));
                double lat = Double.parseDouble(request.getParameter(JsonKey.latitude));
                double longi = Double.parseDouble(request.getParameter(JsonKey.longitude));

                Workplace wp = facWp.jsonToObject(workplaceJson);
                return facPath.arrayListToJson(daoPt.findAllPath(wp.getId(),lat,longi,distance)).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return  null;
    }

    //le reste n'est pas utilisé
    public String postResponseFromResquest(HttpServletRequest request){
        return  null;
    }

    public String deleteResponseFromResquest(HttpServletRequest request){
        return  null;
    }

    public String putResponseFromResquest(HttpServletRequest request){
        return  null;
    }
}
