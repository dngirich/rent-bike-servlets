package com.belhard.controllers;

import com.belhard.util.Role;
import com.belhard.domain.UserEntity;
import com.belhard.service.UserService;
import com.belhard.service.factory.ServiceFactory;
import com.belhard.util.ConstantsMng;
import com.belhard.util.MessageUtils;
import com.belhard.util.RequestUtils;
import com.belhard.util.StringUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Register", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 629738330207823052L;
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter(ConstantsMng.PARAM_NAME_FIRST_NAME);
        String lastName = request.getParameter(ConstantsMng.PARAM_NAME_LAST_NAME);
        String login = request.getParameter(ConstantsMng.PARAM_NAME_LOGIN);
        String password = request.getParameter(ConstantsMng.PARAM_NAME_PASSWORD);

        Map<String, String> errorMap = validateRegisterDetails(request, firstName, lastName, login, password);

        if (errorMap.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setRoles(Arrays.asList(Role.USER));
            userService.addUser(user);
            HttpSession session = request.getSession(true);
            session.setAttribute(ConstantsMng.PARAM_NAME_FIRST_NAME, user.getFirstName());
            session.setAttribute(ConstantsMng.PARAM_NAME_LAST_NAME, user.getLastName());
            session.setAttribute(ConstantsMng.PARAM_NAME_LOGIN, user.getEmail());
            session.setAttribute(ConstantsMng.PARAM_NAME_PASSWORD, user.getPassword());
            String msg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                    MessageUtils.VALIDATION_SUCCESSFUL_MESSAGE);
            request.setAttribute(ConstantsMng.ATR_SUCCESSFUL, msg);
            forwardToView(ConstantsMng.LOGIN, request, response);

        } else {
            request.setAttribute(ConstantsMng.ATR_ERRORS, errorMap);
            forwardToView(ConstantsMng.REGISTRATION, request, response);
        }
    }

    private Map<String, String> validateRegisterDetails(HttpServletRequest request,
            String firstName, String lastName, String login, String password) {
        Map<String, String> errorMap = new HashMap<>();
        String loginMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_EMAIL_ERROR_MESSAGE);
        if (StringUtils.isEmpty(login)) {
            errorMap.put(ConstantsMng.PARAM_NAME_LOGIN, loginMsg);
        }
        String passwordMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_PASSWORD_ERROR_MESSAGE);
        if (StringUtils.isEmpty(password)) {
            errorMap.put(ConstantsMng.PARAM_NAME_PASSWORD, passwordMsg);
        }
        String firstNameMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_FIRST_NAME_ERROR_MESSAGE);
        if (StringUtils.isEmpty(firstName)) {
            errorMap.put(ConstantsMng.PARAM_NAME_FIRST_NAME, firstNameMsg);
        }
        String lastNameMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_LAST_NAME_ERROR_MESSAGE);
        if (StringUtils.isEmpty(lastName)) {
            errorMap.put(ConstantsMng.PARAM_NAME_LAST_NAME, lastNameMsg);
        }
        return errorMap;
    }

    private void forwardToView(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
        dispatcher.forward(request, response);
    }
}
