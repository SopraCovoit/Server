package controller;

import model.StatusedMessage;
import model.User;
import model.dao.DAOUser;
import model.jsonFactory.FactoryError;
import model.jsonFactory.FactoryUser;
import utils.JsonKey;
import utils.TokenList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by root on 09/01/15.
 */
public class ConnectionController extends AbstractController {
    DAOUser daoUs;
    FactoryError facEr;
    FactoryUser facUs;

    public ConnectionController(){
        daoUs = new DAOUser();
        facUs = new FactoryUser();
        facEr = new FactoryError();

    }
    @Override
    public String postResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String getResponseFromResquest(HttpServletRequest request) {
        User toReturn = daoUs.find(request.getParameter(this.mail),request.getParameter(JsonKey.password));
        if( toReturn != null){
            String token = TokenList.getNewToken();
            toReturn.setToken(token);
            return facUs.objectToJson(toReturn).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(HttpServletResponse.SC_FORBIDDEN,StatusedMessage.FAILURE_POST_USER)).toString();
        }
    }

    @Override
    public String deleteResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String putResponseFromResquest(HttpServletRequest request) {
        return null;
    }


}
