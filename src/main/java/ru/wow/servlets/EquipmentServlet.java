package ru.wow.servlets;

import com.google.gson.Gson;
import ru.wow.ejb.interfaces.EquipmentHandler;
import ru.wow.models.Equipment;
import ru.wow.util.HibernateUtil;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


@WebServlet(name = "EquipmentServlet", urlPatterns = "/equipmentHandling")
public class EquipmentServlet extends HttpServlet {
    @EJB
    EquipmentHandler equipmentBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getHeader("action");
        Equipment equipment = null;
        boolean isSuccess = false;
        switch (action){
            case "create":
                equipment = getEquipment(request);
                isSuccess = equipmentBean.createEquipment(equipment);
                sendResultStatus(isSuccess, response);
                break;
            case "update":
                equipment = getEquipment(request);
                isSuccess = equipmentBean.updateEquipment(equipment);
                sendResultStatus(isSuccess, response);
                break;
            case "remove":
                long equipmentId = Long.parseLong(request.getParameter("equipmentId"));
                isSuccess = equipmentBean.removeById(equipmentId);
                sendResultStatus(isSuccess, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=windows-1251");
        String searchFor = request.getHeader("searchFor");
        int equipmentId = Integer.parseInt(request.getParameter("equipmentId"));
        PrintWriter writer = response.getWriter();
        switch (searchFor){
            case "single":
                Equipment equipment = equipmentBean.findEquipment(equipmentId);
                request.setAttribute("equipment", equipment);
                request.getRequestDispatcher("/files/components/equipmentTable.jsp").forward(request, response);
                break;
            case "singleXml":
                String equipmentXml = equipmentBean.getEquipmentAsHtmlById(equipmentId);
                if(equipmentXml != null) writer.println(equipmentXml);
                else sendResultStatus(false, response);
                break;
            case "byName":

                break;
            case "all":
                List<Equipment> allEquipment = equipmentBean.findAllEquipment();
                break;
        }
    }

    private Equipment getEquipment(HttpServletRequest request) throws UnsupportedEncodingException, UnsupportedEncodingException {
        Equipment equipment = new Equipment();
        String id = request.getParameter("equipmentId");
        if(id != null){
            long equipmentId = Long.parseLong(id);
            equipment.setId(equipmentId);
        }
        equipment.setName(request.getParameter("name"));
        equipment.setLevel(Integer.parseInt(request.getParameter("level")));
        equipment.setParry(Integer.parseInt(request.getParameter("parry")));
        equipment.setProtection(Integer.parseInt(request.getParameter("protection")));
        equipment.setSpeed(Integer.parseInt(request.getParameter("speed")));
        return equipment;
    }

    private void sendResultStatus(boolean isSuccess, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (isSuccess) writer.println("{\"status\":\"success\"}");
        else writer.println("{\"status\":\"fail\"}");
    }
}
