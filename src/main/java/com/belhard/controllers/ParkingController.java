package com.belhard.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.belhard.domain.ParkingEntity;
import com.belhard.service.ParkingService;
import com.belhard.service.factory.ServiceFactory;
import com.belhard.util.ConstantsMng;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ParkingController", urlPatterns = {"/ParkingController"})
public class ParkingController extends CRUDController {

    private static final long serialVersionUID = 6297383302078230511L;
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParkingEntity parking = new ParkingEntity();
        parking.setStreet(request.getParameter("street"));
        parkingService.addParking(parking);
        response.sendRedirect(request.getContextPath() + "/ParkingController?action=list");
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        parkingService.deleteParking(id);
        response.sendRedirect(request.getContextPath() + "/ParkingController?action=list");
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("parkings", parkingService.getAllParking());
        forward(ConstantsMng.LIST_PARKING, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParkingEntity parking = new ParkingEntity();
        parking.setStreet(request.getParameter("street"));
        parking.setParkingId(id);
        parkingService.updateParking(parking);
        response.sendRedirect(request.getContextPath() + "/ParkingController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParkingEntity parking = parkingService.getParkingById(id);
        request.setAttribute("parking", parking);
        forward(ConstantsMng.ADD_OR_EDIT_PARKING, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(ConstantsMng.ADD_OR_EDIT_PARKING, request, response);
    }
}
