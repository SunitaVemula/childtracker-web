/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.urlrewriting;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@WebServlet(name = "NotFound", urlPatterns = {"/notfound"})
public class NotFound extends HttpServlet {

    @Inject
    private UrlRouting urlRouting;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        urlRouting.route404(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
