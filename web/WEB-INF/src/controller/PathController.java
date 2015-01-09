package controller;

import model.Workplace;
import model.dao.DAOPath;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryPath;
import model.jsonFactory.FactoryWorkplace;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class PathController extends AbstractController {

    DAOPath daoPt;
    FactoryPath facPath;
    FactoryError facEr;
    FactoryWorkplace facWp;

    public PathController(){
        daoPt = new DAOPath();
        facPath = new FactoryPath();
        facWp = new FactoryWorkplace();
        facEr = new FactoryError();
    }


    public String getResponseFromResquest(HttpServletRequest request){
        if(request.getParameter(JsonKey.longitude) == null || request.getParameter(JsonKey.latitude) == null){

        }else{
            try {
                JSONObject workplaceJson = new JSONObject(request.getParameter(JsonKey.workplace));
                Workplace wp = facWp.jsonToObject(workplaceJson);
                return facPath.arrayListToJson(daoPt.findAllWorkPlacePath(wp.getId())).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
