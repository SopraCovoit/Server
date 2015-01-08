package controller;


import model.dao.DAOWorkplace;
import model.jsonFactory.factoryUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class WorkplaceController extends AbstractController {

    DAOWorkplace daoWp;
    factoryUser facWp;

    public WorkplaceController(){

        daoWp = new DAOWorkplace();
        facWp = new factoryUser();

    }

    public String getResponseFromResquest(HttpServletRequest request){
        //send all worplace

        return facWp.objectToJson(daoWp.findAll().toString());

    }

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
