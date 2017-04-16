package ru.wow.servlets;

import com.google.gson.Gson;
import ru.wow.ejb.interfaces.EquipmentHandler;
import ru.wow.ejb.interfaces.PersonageHandler;
import ru.wow.ejb.interfaces.WeaponHandler;
import ru.wow.ejb.interfaces.impls.PersonageBean;
import ru.wow.models.Equipment;
import ru.wow.models.Personage;
import ru.wow.models.Weapon;
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

@WebServlet(name = "PersonageServlet", urlPatterns = "/personageHandling")
public class PersonageServlet extends HttpServlet {

    @EJB
    PersonageHandler personageBean;
    @EJB
    WeaponHandler weaponBean;
    @EJB
    EquipmentHandler equipmentBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getHeader("action");
        Personage personage = null;
        boolean isSuccess = false;
        switch (action){
            case "create":
                personage = getPersonage(request);
                isSuccess = personageBean.createPersonage(personage);
                sendResultStatus(isSuccess, response);
                break;
            case "update":
                personage = getPersonage(request);
                isSuccess = personageBean.updatePersonage(personage);
                sendResultStatus(isSuccess, response);
                break;
            case "remove":
                long personageId = Long.parseLong(request.getParameter("personageId"));
                isSuccess = personageBean.removeById(personageId);
                sendResultStatus(isSuccess, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=windows-1251");
        String searchFor = request.getHeader("amount");
        String responseType = request.getHeader("responseType");
        long personageId = Long.parseLong(request.getParameter("personageId"));
        PrintWriter writer = response.getWriter();
        switch (searchFor){
            case "single":
                if(responseType.equals("xml")){
                    String personageXml = personageBean.getPersonageAsXmlById(personageId);
                    if(personageXml != null) writer.println(personageXml);
                    else sendResultStatus(false, response);
                    break;
                } else {
                    Personage personage = personageBean.findPersonage(personageId);
                    request.setAttribute("personage", personage);
                    request.getRequestDispatcher("/files/components/personageTable.jsp").forward(request, response);
                }
                break;
            case "all":
                break;
        }
    }

    private Personage getPersonage(HttpServletRequest request) throws UnsupportedEncodingException {
        Personage personage = new Personage();
        String id = request.getParameter("personageId");
        if(id != null){
            long personageId = Long.parseLong(id);
            personage.setId(personageId);
        }
        personage.setNickname(request.getParameter("nickname"));
        personage.setLevel(Integer.parseInt(request.getParameter("level")));
        personage.setRace(request.getParameter("race"));
        personage.setWeapon(weaponBean.findWeapon(Integer.parseInt(request.getParameter("weapon"))));
        personage.setEquipment(equipmentBean.findEquipment(Integer.parseInt(request.getParameter("equipment"))));
        return personage;
    }

    private void sendResultStatus(boolean isSuccess, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        if (isSuccess) writer.println("{\"status\":\"success\"}");
        else writer.println("{\"status\":\"fail\"}");
    }
}
