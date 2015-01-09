package view;

import controller.AbstractController;
import controller.WorkplaceController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet class used to access to workplaces informations with http methods
 * Created by root on 19/12/14.
 */


public class WorkplaceServlet extends HttpServlet {


    private AbstractController c;

    protected void initController(){
        if(c == null){
            c = new WorkplaceController();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        initController();
        PrintWriter out = resp.getWriter();
        out.write(c.postResponseFromResquest(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
        resp.setContentType("text/plain");
        initController();
        PrintWriter out = resp.getWriter();
        out.write(c.putResponseFromResquest(req));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/plain");
        initController();
        PrintWriter out = resp.getWriter();
        out.write(c.getResponseFromResquest(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        resp.setContentType("text/plain");
        initController();
        PrintWriter out = resp.getWriter();
        out.write(c.deleteResponseFromResquest(req));
    }

}
