package controller;

import model.Path;
import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOPath;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryPath;
import model.jsonFactory.FactoryWorkplace;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonKey;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class PathController extends AbstractController {

    DAOPath daoPt;
    FactoryPath facPath;
    FactoryError facEr;
    FactoryWorkplace facWp;
    int distance = 5;

    public PathController(){
        daoPt = new DAOPath();
        facPath = new FactoryPath();
        facWp = new FactoryWorkplace();
        facEr = new FactoryError();
    }

    public String getResponseFromResquest(HttpServletRequest request){

        try {
            /*if (!TokenList.leverage((request.getParameter(JsonKey.token)))) {
                return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_TOKEN,StatusedMessage.FAILURE_GET_PATH)).toString();
            }*/
        }catch(NullPointerException e){
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.BAD_TOKEN,StatusedMessage.FAILURE_GET_PATH)).toString();
        }

            try {
                JSONObject workplaceJson = new JSONObject(request.getParameter(JsonKey.workplace));
                double lat = Double.parseDouble(request.getParameter(JsonKey.latitude));
                double longi = Double.parseDouble(request.getParameter(JsonKey.longitude));

                Workplace wp = facWp.jsonToObject(workplaceJson);
                return facPath.arrayListToJson(daoPt.findAllPath(wp.getId(),lat,longi,distance)).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return  null;
    }

    public String postResponseFromResquest(HttpServletRequest request){

      //  Path path = new Path(new Location(5,6),"13h40",10,"HOME",9,5);
       // System.out.println(facPath.objectToJson(path).toString());

        Path pathToAdd = facPath.jsonToObject(getJsonFromRequest(request));
        System.out.println(pathToAdd.getWorkPlaceId());
        if(daoPt.create(pathToAdd) != null){
            return facPath.objectToJson(pathToAdd).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.ALREADY_IN_BASE,StatusedMessage.FAILURE_POST_WORKPLACE)).toString();
        }
    }

    public String deleteResponseFromResquest(HttpServletRequest request){
        String json = null;

        if (request.getParameter(id) != null) {
            Path pathToDelete = daoPt.find(Long.parseLong(request.getParameter(id)));
            if(pathToDelete == null){
                json = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_DELETE_PATH)).toString();
            }else{
                if(daoPt.delete(pathToDelete)){
                    json = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.SUCCESS_DELETE_PATH)).toString();
                }else{
                    json = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_DELETE_PATH)).toString();
                }
            }
        }else{
            json = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_DELETE_PATH)).toString();
        }

        return json;
    }

    public String putResponseFromResquest(HttpServletRequest request){
        String retStr;

        ServletInputStream inputStream = null;
        String entry = "" ;
        try {
            inputStream = request.getInputStream();
            int next = 0;

            while(next != -1){
                next = inputStream.read();
                entry = entry+(char)next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isUpdated = false;
        try {
            isUpdated = daoPt.update(facPath.jsonToObject(new JSONObject(entry)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isUpdated) {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS, StatusedMessage.SUCCESS_PUT_USER)).toString();
        }
        else {
            retStr = facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS, StatusedMessage.FAILURE_PUT_USER)).toString();
        }
        return retStr;
    }
}
