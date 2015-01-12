package controller;


import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryWorkplace;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
       // Workplace test = new Workplace(new Location(4,5),4,"saadslkt");
       // daoWp.update(test);
        //System.out.println(facWp.objectToJson(test));
        return facWp.arrayListToJson(daoWp.findAll()).toString();

    }

    public String postResponseFromResquest(HttpServletRequest request){
        //new location

        Workplace wpToAdd = facWp.jsonToObject(getJsonFromRequest(request));
        if(daoWp.create(wpToAdd) != null){
            return facWp.objectToJson(daoWp.findByName(wpToAdd.getName())).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.ALREADY_IN_BASE,StatusedMessage.FAILURE_POST_WORKPLACE)).toString();
        }

    }

    public String deleteResponseFromResquest(HttpServletRequest request){

        Workplace wpToDelete = null;
        wpToDelete = daoWp.find(Long.parseLong(request.getParameter(JsonKey.id)));


        if(wpToDelete != null){
            daoWp.delete(wpToDelete);
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS,StatusedMessage.SUCCESS_DELETE_WORKPLACE)).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_DELETE_WORKPLACE)).toString();
        }

    }

    public String putResponseFromResquest(HttpServletRequest request){

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

        Workplace wpToUp = null;
        try {
            wpToUp = facWp.jsonToObject(new JSONObject(entry));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(daoWp.update(wpToUp)){
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS,StatusedMessage.SUCCESS_PUT_WORKPLACE)).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_PUT_WORKPLACE)).toString();
        }
    }
}
