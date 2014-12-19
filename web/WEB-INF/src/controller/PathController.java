package controller;

import model.dao.DAOPath;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class PathController extends AbstractController {

    DAOPath daoPt;

    public PathController(){
        daoPt = new DAOPath();
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
