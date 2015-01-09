package view;

import controller.AbstractController;
import controller.ConnectionController;
import controller.UserController;
import model.User;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(response);

        initController();
        PrintWriter out = response.getWriter();
        out.write(c.postResponseFromResquest(request));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
