package controller;

import model.bdd.DAO_Workplace;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class WorkplaceController extends AbstractController {

    DAO_Workplace daoWp;

    public WorkplaceController(){
        daoWp = new DAO_Workplace();
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
