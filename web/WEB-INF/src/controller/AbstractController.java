package controller;

import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by julescantegril on 19/12/2014.
 */

public abstract class AbstractController {

    public abstract String getResponseFromResquest(HttpServletRequest request);

    public abstract String postResponseFromResquest(HttpServletRequest request);

    public abstract String deleteResponseFromResquest(HttpServletRequest request);

    public abstract String putResponseFromResquest(HttpServletRequest request);

    public JSONObject getJsonFromRequest(HttpServletRequest request){
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();

      //  System.out.println(request.getParameterMap().toString()+" map ");
       // System.out.println(request.getParameterNames().toString()+" names ");

        Map.Entry<String,String> entry = (Map.Entry<String,String>)it.next();

        try {
            return new JSONObject(entry.getKey());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int isError(String er){
        try {
            JSONObject json = new JSONObject(er);
            if(json.getInt(JsonKey.status) != 200){
                return json.getInt(JsonKey.status);
            }else{
                return json.getInt(JsonKey.status);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;//c'est pas une erreur, ni vrai ni faut on renvoit
        }

    }

    /**
     * Every key we use to get every values in our JSonObject
     */

    protected String id = "id";
    protected String name = "name";
    protected String surname = "surname";
    protected String mail = "mail";
    protected String phone = "phone";
    protected String isDriver = "isDriver";
    protected String home = "home";
    protected String latitude = "lat";
    protected String longitude = "lng";
    protected String workplaceId = "workplace";

    protected String location = "location";
    protected String departureHour = "departure_hour";
    protected String direction = "direction";
    protected String user = "user";

    protected String error = "error";
    protected String type = "type";
    protected String message = "message";

}
