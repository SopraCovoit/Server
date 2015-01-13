package view;

import controller.AbstractController;
import controller.ConnectionController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 09/01/15.
 */
public class ConnectionServlet extends HttpServlet {
    private AbstractController c;

    protected void initController(){
        if(c == null){
            c = new ConnectionController();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(response);
        initController();
        PrintWriter out = response.getWriter();
        out.write(c.getResponseFromResquest(request));
    }

    @Override
    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        super.doOptions(httpServletRequest, httpServletResponse);
    }
}
