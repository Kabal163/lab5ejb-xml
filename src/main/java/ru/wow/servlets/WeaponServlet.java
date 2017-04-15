package ru.wow.servlets;

import ru.wow.ejb.interfaces.WeaponHandler;
import ru.wow.models.Weapon;

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

@WebServlet(name = "WeaponServlet", urlPatterns = "/weaponHandling")
public class WeaponServlet extends HttpServlet {
    @EJB
    WeaponHandler weaponBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String action = request.getHeader("action");
        Weapon weapon = null;
        boolean isSuccess = false;
        switch (action){
            case "create":
                weapon = getWeapon(request);
                isSuccess = weaponBean.createWeapon(weapon);
                sendResultStatus(isSuccess, response);
                break;
            case "update":
                weapon = getWeapon(request);
                isSuccess = weaponBean.updateWeapon(weapon);
                sendResultStatus(isSuccess, response);
                break;
            case "remove":
                long weaponId = Long.parseLong(request.getParameter("weaponId"));
                isSuccess = weaponBean.removeById(weaponId);
                sendResultStatus(isSuccess, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=windows-1251");
        String searchFor = request.getHeader("amount");
        String responseType = request.getHeader("responseType");
        PrintWriter writer = response.getWriter();
        int weaponId = Integer.parseInt(request.getParameter("weaponId"));
        switch (searchFor){
            case "single":
                if(responseType.equals("xml")){
                    String weaponXml = weaponBean.getWeaponAsHtmlById(weaponId);
                    if(weaponXml != null) writer.println(weaponXml);
                    else sendResultStatus(false, response);
                } else {
                    Weapon weapon = weaponBean.findWeapon(weaponId);
                    request.setAttribute("weapon", weapon);
                    request.getRequestDispatcher("/files/components/weaponTable.jsp").forward(request, response);
                    writer.println(weapon);
                }
                break;
            case "all":
                List<Weapon> allWeapon = weaponBean.findAllWeapon();
                break;
        }
    }

    private Weapon getWeapon(HttpServletRequest request) throws UnsupportedEncodingException {
        Weapon weapon = new Weapon();
        String id = request.getParameter("weaponId");
        if(id != null) {
            long weaponId = Long.parseLong(id);
            weapon.setId(weaponId);
        }
        weapon.setName(request.getParameter("name"));
        weapon.setLevel(Integer.parseInt(request.getParameter("level")));
        weapon.setPower(Integer.parseInt(request.getParameter("damage")));
        weapon.setParry(Integer.parseInt(request.getParameter("parry")));
        return weapon;
    }

    private void sendResultStatus(boolean isSuccess, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        if (isSuccess) writer.println("{\"status\":\"success\"}");
        else writer.println("{\"status\":\"false\"}");
    }
}