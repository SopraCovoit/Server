package controller;

import model.dao.DAO_Path;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class PathController extends AbstractController {

    DAO_Path daoPt;

    public PathController(){
        daoPt = new DAO_Path();
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
