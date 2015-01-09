package controller;

import model.StatusedMessage;
import model.User;
import model.dao.DAOUser;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryUser;
import org.json.JSONException;
import org.json.JSONObject;
import utils.TokenMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class UserController extends AbstractController {

    DAOUser daoUs;
    FactoryUser facUs;
    FactoryError facEr;

    public UserController(){
        daoUs = new DAOUser();
       facUs = new FactoryUser();
        facEr = new FactoryError();

    }

    String id = "id";
    String workplaceId = "workplaceId";
    String password = "password";
    String mail = "mail";

    String token = "postdata";


    public String getResponseFromResquest(HttpServletRequest request){

        String json = null;
        if(request.getParameter(id) != null) {
            json = facUs.objectToJson(daoUs.find(Long.parseLong(request.getParameter(id)))).toString();
        }else {
            //System.out.println("avant");
            json = facUs.arrayListToJson(daoUs.findAll()).toString();
            //System.out.println("après");
        }
        //System.out.println(json);
        return json;
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
            long token = TokenMap.getNewToken();
            if( toReturn != null){
                TokenMap.addToken(token, toReturn);
                return String.valueOf(token);
            }else{
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_POST_USER)).toString();
            }
        }
    }

    public String deleteResponseFromResquest(HttpServletRequest request){
        String json = null;
        boolean success = false;
        if(request.getParameter(id) !=null) {
            success = daoUs.delete(daoUs.find(Long.parseLong(request.getParameter(id))));
        }
        if(success){
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS,StatusedMessage.FAILURE_DELETE_USER)).toString();
        }else{

        }

        return json;
    }

    public String putResponseFromResquest(HttpServletRequest request){
        return  null;
    }
}
