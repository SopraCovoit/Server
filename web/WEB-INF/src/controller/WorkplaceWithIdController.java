package controller;

import model.StatusedMessage;
import model.Workplace;
import model.dao.DAOWorkplace;
import model.jsonFactory.FactoryError;
import utils.JsonKey;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 12/01/15.
 */
public class WorkplaceWithIdController extends AbstractController {
    DAOWorkplace daoWp;
    FactoryError facEr;

    public WorkplaceWithIdController(){
        this.daoWp = new DAOWorkplace();
        this.facEr = new FactoryError();
    }
    @Override
    public String getResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String postResponseFromResquest(HttpServletRequest request) {
        return null;
    }

    @Override
    public String deleteResponseFromResquest(HttpServletRequest request) {
        Workplace wpToDelete;
        wpToDelete = daoWp.find(Long.parseLong(request.getContextPath()));
        if(wpToDelete != null){
            daoWp.delete(wpToDelete);
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.SUCCESS_STATUS,StatusedMessage.SUCCESS_DELETE_WORKPLACE)).toString();
        }else{
            return facEr.objectToJson(new StatusedMessage(StatusedMessage.FAILURE_STATUS,StatusedMessage.FAILURE_DELETE_WORKPLACE)).toString();
        }

    }

    @Override
    public String putResponseFromResquest(HttpServletRequest request) {
        return null;
    }
}
