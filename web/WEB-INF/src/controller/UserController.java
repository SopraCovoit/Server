package controller;

import model.User;
import model.dao.DAOUser;
import model.jsonFactory.factoryUser;
import org.json.JSONException;
import org.json.JSONObject;

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
    String password = "password";
    String mail = "mail";

    String token = "postdata";


    public String getResponseFromResquest(HttpServletRequest request){
       return facUs.objectToJson(daoUs.find(Long.parseLong(request.getParameter(id)))).toString();
    }

    public String postResponseFromResquest(HttpServletRequest request){
        if(request.getParameter(token) == null){
            User newUser = null;
            try {
                newUser = daoUs.create(facUs.jsonToObject(new JSONObject(request.getParameter("postdata"))));
            } catch (JSONException e) {
                e.printStackTrace();
            }
                return facUs.objectToJson(newUser).toString();
            //RETURN JUST TOKEN
        }else {
            //TOKEN
            User toReturn = daoUs.find(request.getParameter(this.mail),request.getParameter(this.password));
            if( toReturn != null){

            }

        }
        return null;
    }

    public String deleteResponseFromResquest(HttpServletRequest request){
        return  null;
    }

    public String putResponseFromResquest(HttpServletRequest request){
        return  null;
    }
}
