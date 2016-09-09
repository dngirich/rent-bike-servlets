package com.belhard.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.belhard.domain.BikeEntity;
import com.belhard.domain.RentItemEntity;
import com.belhard.domain.UserEntity;
import com.belhard.service.BikeService;
import com.belhard.service.ParkingService;
import com.belhard.service.RentItemService;
import com.belhard.service.factory.ServiceFactory;
import com.belhard.util.ConstantsMng;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BikeController", urlPatterns = {"/BikeController"})
public class BikeController extends CRUDController {

    private static final long serialVersionUID = 6297383302028230511L;

    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "showbike":
                this.showBikeByParkingId(getIdParameter(request), request, response);
                break;
            case "avalaible":
                this.showAvalaibleBike(request, response);
                break;
            case "rentbike":
                this.rentBike(getIdParameter(request), request, response);
                break;
            case "returnbike":
                this.returnBike(getIdParameter(request), request, response);
                break;
            default:
                super.service(request, response);
        }
    }

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BikeEntity bike = new BikeEntity();
        bike.setType(request.getParameter("type"));
        bike.setMarka(request.getParameter("marka"));
        bike.setSize(request.getParameter("size"));
        bike.setAvalaible(true);
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bikeService.createBike(bike);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bikeService.deleteBike(id);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        Integer userId = user.getId();
        RentItemEntity rentItem = rentItemService.findTakenByUser(userId);
        if (rentItem != null) {
            request.setAttribute("userRentedBikeId", rentItem.getBikeId());
        }
        request.setAttribute("bikes", bikeService.getAllBikes());
        forward(ConstantsMng.LIST_BIKES, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BikeEntity bike = new BikeEntity();
        bike.setType(request.getParameter("type"));
        bike.setMarka(request.getParameter("marka"));
        bike.setSize(request.getParameter("size"));
        bike.setAvalaible(false);
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bike.setBikeId(id);
        bikeService.updateBike(bike);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BikeEntity bike = bikeService.getBikeById(id);
        request.setAttribute("bike", bike);
        request.setAttribute("parkings", parkingService.getAllParking());
        forward(ConstantsMng.ADD_OR_EDIT_BIKE, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("parkings", parkingService.getAllParking());
        forward(ConstantsMng.ADD_OR_EDIT_BIKE, request, response);

    }

    void showAvalaibleBike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bikes", bikeService.showAvalaibleBike());
        forward(ConstantsMng.LIST_BIKES, request, response);
    }

    void rentBike(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        Integer userId = user.getId();
        bikeService.rentBike(id, userId);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    void returnBike(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        Integer userId = user.getId();
        bikeService.returnBike(id, userId);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    void showBikeByParkingId(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bikes", bikeService.showBikeByParkingId(id));
        forward(ConstantsMng.LIST_BIKES, request, response);
    }
}
