package com.belhard.controllers;

import com.belhard.domain.BikeEntity;
import com.belhard.domain.SupportItemEntity;
import com.belhard.service.BikeService;
import com.belhard.service.SupportItemService;
import com.belhard.service.factory.ServiceFactory;
import com.belhard.util.ConstantsMng;
import com.belhard.util.MessageUtils;
import com.belhard.util.RequestUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "SupportItemController", urlPatterns = {"/SupportItemController"})
public class SupportItemController extends CRUDController {

    private static final long serialVersionUID = 6297383302778230511L;
    private SupportItemService supportItemService = ServiceFactory.getFactory().getSupportItemService();
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "unperformeditem":
                this.unperformedItem(request, response);
                break;
            case "repair":
                this.repairItem(request, response);
                break;
            default:
                super.service(request, response);
        }
    }

    @Override
    void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SupportItemEntity supportItem = new SupportItemEntity();

        supportItem.setBikeId(Integer.parseInt(request.getParameter("id")));
        supportItem.setDescription(request.getParameter("description"));
        supportItem.setStatus(Boolean.valueOf(request.getParameter("status")));
        supportItemService.createItem(supportItem);

        request.setAttribute("items", supportItemService.getAllItems());
        String message = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.BID_MEASSAGE);
        request.setAttribute("bid", message);
        request.setAttribute("bikes", bikeService.getAllBikes());
        forward(ConstantsMng.LIST_BIKES, request, response);
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("items", supportItemService.getAllItems());
        forward(ConstantsMng.LIST_ITEMS, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bikeId = Integer.parseInt(request.getParameter("id"));
        BikeEntity bike = bikeService.getBikeById(bikeId);
        request.setAttribute("bike", bike);
        forward(ConstantsMng.ADD_ITEMS, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    void unperformedItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("items", supportItemService.unperformedItem());
        forward(ConstantsMng.LIST_ITEMS, request, response);

    }

    void repairItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bikeId = Integer.parseInt(request.getParameter("id"));
        supportItemService.repairItem(bikeId);
        request.setAttribute("items", supportItemService.getAllItems());
        forward(ConstantsMng.LIST_ITEMS, request, response);
    }

}
