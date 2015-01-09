package controller;


import model.Location;
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
        Workplace test = new Workplace(new Location(4,5),4,"saadslkt");
       // daoWp.update(test);
        System.out.println(facWp.objectToJson(test));
        return facWp.arrayListToJson(daoWp.findAll()).toString();

    }

    public String postResponseFromResquest(HttpServletRequest request){
        //new location
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

        Workplace wpToAdd = facWp.jsonToObject(json);
        if(daoWp.create(wpToAdd) != null){
            return facWp.objectToJson(daoWp.findByName(wpToAdd.getName())).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_POST_WORKPLACE)).toString();
        }

    }

    public String deleteResponseFromResquest(HttpServletRequest request){

        Long.parseLong(request.getParameter(JsonKey.id));
        Workplace wpToDelete = daoWp.find(Long.parseLong(request.getParameter(JsonKey.id)));

        if(wpToDelete != null){
            daoWp.delete(wpToDelete);
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS,StatusedMessage.SUCCESS_DELETE_WORKPLACE)).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_DELETE_WORKPLACE)).toString();
        }

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
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_PUT_WORKPLACE)).toString();
        }
    }
}
