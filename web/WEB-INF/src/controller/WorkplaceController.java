package controller;


import model.dao.DAOWorkplace;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class WorkplaceController extends AbstractController {

    DAOWorkplace daoWp;

    public WorkplaceController(){
        daoWp = new DAOWorkplace();
    }

    public String getResponseFromResquest(HttpServletRequest request){

        return  null;
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
