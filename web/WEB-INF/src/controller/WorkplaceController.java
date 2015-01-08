package controller;


import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryWorkplace;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class WorkplaceController extends AbstractController {

    DAOWorkplace daoWp;
    FactoryWorkplace facWp;
    FactoryError facEr;

    public WorkplaceController(){

        daoWp = new DAOWorkplace();
        facWp = new FactoryWorkplace();
        facEr = new FactoryError();

    }

    public String getResponseFromResquest(HttpServletRequest request){
        //send all worplace
        return facWp.arrayListToJson(daoWp.findAll()).toString();

    }

    public String postResponseFromResquest(HttpServletRequest request){
        //new location
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();
        JSONObject json = new JSONObject();

        while(it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();

            String key             = entry.getKey();
            String value         = entry.getValue();

            try {
                json.put(key,value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Workplace wpToAdd = facWp.jsonToObject(json);
        if(daoWp.create(wpToAdd) != null){
            return facWp.objectToJson(wpToAdd).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_MESSAGE)).toString();
        }

    }

    public String deleteResponseFromResquest(HttpServletRequest request){

        Long.parseLong(request.getParameter(JsonKey.id));
        Workplace wpToDelete = daoWp.find(Long.parseLong(request.getParameter(JsonKey.id)));

        if(wpToDelete != null){
            daoWp.delete(wpToDelete);
            //return success
        }else{
            //return fail
        }

        return  null;
    }

    public String putResponseFromResquest(HttpServletRequest request){
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();
        JSONObject json = new JSONObject();

        while(it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();

            String key             = entry.getKey();
            String value         = entry.getValue();

            try {
                json.put(key,value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Workplace wpToUp = facWp.jsonToObject(json);
        if(daoWp.update(wpToUp)){
            return facWp.objectToJson(wpToUp).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(1,"Fail to update workplace")).toString();
        }
    }
}
