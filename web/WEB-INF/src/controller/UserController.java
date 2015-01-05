package controller;

import model.dao.DAOUser;
import model.jsonFactory.factoryUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class UserController extends AbstractController {

    DAOUser daoUs;
    factoryUser facUs;

    public UserController(){
        daoUs = new DAOUser();
        facUs = new factoryUser();
    }

    String id = "id";
    String postdata = "postdata";

    public String getResponseFromResquest(HttpServletRequest request){
       return facUs.objectToJson(daoUs.find(Long.parseLong(request.getParameter(id)))).toString();
    }

    public String postResponseFromResquest(HttpServletRequest request){
       /* try {
            facUs.objectToJson(daoUs.create(facUs.jsonToObject(new JSONObject(request.getParameter(postdata))))).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    public String deleteResponseFromResquest(HttpServletRequest request){
        return  null;
    }

    public String putResponseFromResquest(HttpServletRequest request){
        return  null;
    }
}
