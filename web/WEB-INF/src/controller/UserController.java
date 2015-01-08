package controller;

import model.User;
import model.dao.DAOUser;
import model.jsonFactory.factoryUser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

            Map m = request.getParameterMap();
            Set s = m.entrySet();
            Iterator it = s.iterator();
            JSONObject json = new JSONObject();

            while(it.hasNext()){

                Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();

                String key             = entry.getKey();
                String value         = entry.getValue();

                try {
                    json.put(key,value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            User newUser = null;

                newUser = daoUs.create(facUs.jsonToObject(json));

                return facUs.objectToJson(newUser).toString();
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
