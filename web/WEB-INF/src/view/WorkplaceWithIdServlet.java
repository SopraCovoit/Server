package view;

import controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 12/01/15.
 */
@WebServlet(name = "WorkplaceWithIdServlet")
public class WorkplaceWithIdServlet extends HttpServlet {
    private AbstractController c;

    protected void initController(){
        if(c == null){
            c = new WorkplaceWithIdController();
        }
    }
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        initController();
        PrintWriter out = httpServletResponse.getWriter();
        String respFromRequest = c.postResponseFromResquest(httpServletRequest);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            httpServletResponse.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        initController();
        PrintWriter out = httpServletResponse.getWriter();
        String respFromRequest = c.deleteResponseFromResquest(httpServletRequest);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            httpServletResponse.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        initController();
        PrintWriter out = httpServletResponse.getWriter();
        String respFromRequest = c.putResponseFromResquest(httpServletRequest);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            httpServletResponse.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        initController();
        PrintWriter out = httpServletResponse.getWriter();
        String respFromRequest = c.getResponseFromResquest(httpServletRequest);
        if(AbstractController.isError(respFromRequest) == -1){
            out.write(respFromRequest);
        }else{
            out.write(respFromRequest);
            httpServletResponse.sendError(AbstractController.isError(respFromRequest));
        }
    }

    @Override
    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        super.doOptions(httpServletRequest, httpServletResponse);
    }
}
