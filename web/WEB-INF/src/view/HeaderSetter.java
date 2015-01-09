package view;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by julescantegril on 09/01/2015.
 */
public class HeaderSetter {

    public static void addCorsHeader(HttpServletResponse response){
        //TODO: externalize the Allow-Origin
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.setContentType("text/plain");
    }

}
