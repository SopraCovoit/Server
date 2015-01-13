package controller;

import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryWorkplace;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by root on 12/01/15.
 */
public class WorkplaceWithIdController extends AbstractController {
    DAOWorkplace daoWp;
    FactoryError facEr;
    FactoryWorkplace facWp;

    public WorkplaceWithIdController(){
        facWp = new FactoryWorkplace();
        this.daoWp = new DAOWorkplace();
        this.facEr = new FactoryError();
    }
    @Override
    public String getResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String postResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String deleteResponseFromResquest(HttpServletRequest request) {
        Workplace wpToDelete;
        String id = request.getPathInfo();
        id="" + id.substring(id.lastIndexOf("/")+1, id.length());
        wpToDelete = daoWp.find(Long.parseLong(id));
        if(wpToDelete != null){
            daoWp.delete(wpToDelete);
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS,StatusedMessage.SUCCESS_DELETE_WORKPLACE)).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_DELETE_WORKPLACE)).toString();
        }

    }

    @Override
    public String putResponseFromResquest(HttpServletRequest request) {
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
