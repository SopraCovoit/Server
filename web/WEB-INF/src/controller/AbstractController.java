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

}
