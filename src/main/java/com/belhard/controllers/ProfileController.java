package com.belhard.controllers;

import com.belhard.domain.UserEntity;
import com.belhard.service.RentItemService;
import com.belhard.service.UserService;
import com.belhard.service.factory.ServiceFactory;
import com.belhard.util.ConstantsMng;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProfileController", urlPatterns = {"/ProfileController"})
public class ProfileController extends CRUDController {

    private static final long serialVersionUID = 6297383302078130511L;

    private UserService userService = ServiceFactory.getFactory().getUserService();
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "historyrent":
                this.historyRent(request, response);
                break;
            default:
                super.service(request, response);
        }
    }

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        Integer userId = user.getId();
        request.setAttribute("profile", userService.getUserById(userId));
        forward(ConstantsMng.LIST_PROFILE, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserEntity user = new UserEntity();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        userService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/ProfileController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        Integer userId = user.getId();
        request.setAttribute("profile", userService.getUserById(userId));
        forward(ConstantsMng.ADD_OR_EDIT_PROFILE, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void historyRent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        Integer userId = user.getId();
        request.setAttribute("rents", rentItemService.historyRent(userId));
        forward(ConstantsMng.LIST_HISTORY_RENT, request, response);
    }
}
