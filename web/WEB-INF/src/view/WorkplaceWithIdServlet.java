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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(response);
        initController();
        PrintWriter out = response.getWriter();
        out.write(c.deleteResponseFromResquest(request));
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        initController();
        PrintWriter out = httpServletResponse.getWriter();
        out.write(c.deleteResponseFromResquest(httpServletRequest));
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HeaderSetter.addCorsHeader(httpServletResponse);
        initController();
        PrintWriter out = httpServletResponse.getWriter();
        out.write(c.putResponseFromResquest(httpServletRequest));
    }

    @Override
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
