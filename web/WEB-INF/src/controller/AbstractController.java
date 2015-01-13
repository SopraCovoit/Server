package controller;

import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by julescantegril on 19/12/2014.
 */

public abstract class AbstractController {

    public abstract String getResponseFromResquest(HttpServletRequest request);

    public abstract String postResponseFromResquest(HttpServletRequest request);

    public abstract String deleteResponseFromResquest(HttpServletRequest request);

    public abstract String putResponseFromResquest(HttpServletRequest request);

    public JSONObject getJsonFromRequest(HttpServletRequest request){

        JSONObject ret = null;

        try {
            String body = getBody(request);
            //System.out.println("nll"+body+"er");
            ret = new JSONObject(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[1024];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            //System.out.println("RETURN HERE");
            body = stringBuilder.toString();
            return body;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
       // System.out.println("RETURN LA");
        body = stringBuilder.toString();
        return body;
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
