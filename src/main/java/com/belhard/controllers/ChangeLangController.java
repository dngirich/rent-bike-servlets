package com.belhard.controllers;

import com.belhard.util.ConstantsMng;
import com.belhard.util.RequestUtils;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ChangeLangController", urlPatterns = {"/ChangeLangController"})
public class ChangeLangController extends HttpServlet {

    private static final long serialVersionUID = 6297383302078240511L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestUtils.setLocale(request);
        forward(ConstantsMng.INDEX, request, response);
    }

    private void forward(String forward, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
