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
        PrintWriter writer = response.getWriter();
        switch (searchFor){
            case "single":
                Equipment equipment = equipmentBean.findEquipment(getEquipmentId(request));
                request.setAttribute("equipment", equipment);
                request.getRequestDispatcher("/files/components/equipmentTable.jsp").forward(request, response);
                break;
            case "singleXml":
                String equipmentXml = equipmentBean.getEquipmentAsHtmlById(getEquipmentId(request));
                if(equipmentXml != null) writer.println(equipmentXml);
                else sendResultStatus(false, response);
                break;
            case "byName":
                String equipmentsByName = equipmentBean.getEquipmentAsHtmlByName(getEquipmentName(request));
                if(equipmentsByName != null) writer.println(equipmentsByName);
                else sendResultStatus(false, response);
                break;
            case "byLevel":
                String equipmentsByLevel = equipmentBean.getEquipmentAsHtmlByLevel(getEquipmentLevel(request));
                if(equipmentsByLevel != null) writer.println(equipmentsByLevel);
                else sendResultStatus(false, response);
                break;
            case "allXml":
                String allEquipmentXml = equipmentBean.getAllEquipmentAsHtml();
                System.out.println(allEquipmentXml);
                if(allEquipmentXml != null) writer.println(allEquipmentXml);
                else sendResultStatus(false, response);
                break;
        }
    }

    private long getEquipmentId(HttpServletRequest request){
        return Long.parseLong(request.getParameter("equipmentId"));
    }

    private String getEquipmentName(HttpServletRequest request){
        return request.getParameter("equipmentName");
    }

    private int getEquipmentLevel(HttpServletRequest request){
        return Integer.parseInt(request.getParameter("equipmentLevel"));
    }

    private Equipment getEquipment(HttpServletRequest request) throws UnsupportedEncodingException {
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
