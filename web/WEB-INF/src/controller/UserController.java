package controller;

import model.User;
import model.dao.DAOUser;
import model.jsonFactory.FactoryUser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class UserController extends AbstractController {

    DAOUser daoUs;
    FactoryUser facUs;

    public UserController(){
        daoUs = new DAOUser();
       facUs = new FactoryUser();
    }

    String id = "id";
    String workplaceId = "workplaceId";
    String token = "postdata";

    public String getResponseFromResquest(HttpServletRequest request){
        String json = null;
        if(request.getParameter(id) !=null) {
            json = facUs.objectToJson(daoUs.find(Long.parseLong(request.getParameter(id)))).toString();
        }else {
            System.out.println("avant");
            json = facUs.arrayListToJson(daoUs.findAll()).toString();
            System.out.println("après");
        }
        System.out.println(json);
        return json;
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
        }else {
            //TOKEN
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
