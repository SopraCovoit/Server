package view;

import controller.AbstractController;
import controller.PathController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet class used to access to path information with http methods
 * Created by root on 19/12/14.
 */
public class PathServlet extends HttpServlet {

    private AbstractController c;

    protected void initController(){
        if(c == null){
            c = new PathController();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HeaderSetter.addCorsHeader(resp);
        initController();
        PrintWriter out = resp.getWriter();
        String respFromRequest = c.getResponseFromResquest(req);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            resp.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
         protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HeaderSetter.addCorsHeader(resp);
        initController();
        PrintWriter out = resp.getWriter();
        String respFromRequest = c.postResponseFromResquest(req);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            resp.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HeaderSetter.addCorsHeader(resp);
        initController();
        PrintWriter out = resp.getWriter();
        String respFromRequest = c.putResponseFromResquest(req);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            resp.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HeaderSetter.addCorsHeader(resp);
        initController();
        PrintWriter out = resp.getWriter();
        String respFromRequest = c.deleteResponseFromResquest(req);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            resp.sendError(AbstractController.isError(respFromRequest));
        }
    }
    @Override
    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        super.doOptions(httpServletRequest, httpServletResponse);
    }

}
