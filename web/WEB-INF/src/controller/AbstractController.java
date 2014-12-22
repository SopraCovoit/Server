package controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julescantegril on 19/12/2014.
 */

public abstract class AbstractController {

    public abstract String getResponseFromResquest(HttpServletRequest request);

    public abstract String postResponseFromResquest(HttpServletRequest request);

    public abstract String deleteResponseFromResquest(HttpServletRequest request);

    public abstract String putResponseFromResquest(HttpServletRequest request);

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
