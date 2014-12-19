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

    private String id = "id";
    private String name = "name";
    private String surname = "surname";
    private String mail = "mail";
    private String phone = "phone";
    private String isDriver = "isDriver";
    private String home = "home";
    private String latitude = "lat";
    private String longitude = "lng";
    private String workplace = "workplace";

    private String location = "location";
    private String departureHour = "departure_hour";
    private String direction = "direction";
    private String user = "user";

    private String error = "error";
    private String type = "type";
    private String message = "message";

}
