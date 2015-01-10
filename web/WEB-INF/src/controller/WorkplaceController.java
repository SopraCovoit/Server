package controller;


import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryWorkplace;
import utils.JsonKey;

import javax.servlet.http.HttpServletRequest;

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
        Workplace wpToUp = facWp.jsonToObject(getJsonFromRequest(request));
        if(daoWp.update(wpToUp)){
            return facWp.objectToJson(wpToUp).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_PUT_WORKPLACE)).toString();
        }
    }
}
