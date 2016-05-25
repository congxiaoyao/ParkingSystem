package com.congxiaoyao.service;

import com.congxiaoyao.utils.SFResult;
import org.omg.CORBA.NameValuePair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by congxiaoyao on 2016/5/14.
 */
public abstract class MyHttpServlet extends HttpServlet {

    private HttpServletResponse response;
    private PrintWriter printWriter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        this.response = resp;
        this.printWriter = response.getWriter();
        handle(req, resp);
    }

    public abstract void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;


    public void printSuccess() throws IOException {
        printWriter.print(SFResult.success());
    }

    public void printFailure(String reason) throws IOException{
        printWriter.print(SFResult.failure(reason));
    }

    public void print(String string) {
        printWriter.print(string);
    }

    public void print(Object object) {
        printWriter.print(object);
    }

}
