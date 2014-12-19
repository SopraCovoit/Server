package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by julescantegril on 19/12/2014.
 */
public abstract class AbstractController {

    public abstract HttpServletResponse getResponseFromResquest(HttpServletRequest request);

    public abstract HttpServletResponse postResponseFromResquest(HttpServletRequest request);

    public abstract HttpServletResponse deleteResponseFromResquest(HttpServletRequest request);

    public abstract HttpServletResponse putResponseFromResquest(HttpServletRequest request);

}
