package controller;

import model.dao.DAO_User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class UserController extends AbstractController {

    DAO_User daoUs;

    public UserController(){
        daoUs = new DAO_User();
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
