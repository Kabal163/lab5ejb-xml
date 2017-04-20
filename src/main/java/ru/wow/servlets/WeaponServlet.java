package ru.wow.servlets;

import ru.wow.ejb.interfaces.WeaponHandler;
import ru.wow.models.Weapon;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
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
        String searchFor = request.getHeader("searchFor");
        PrintWriter writer = response.getWriter();
        switch (searchFor){
            case "single":
                Weapon weapon = weaponBean.findWeapon(getWeaponId(request));
                if(weapon != null){
                    request.setAttribute("weapon", weapon);
                    request.getRequestDispatcher("/files/components/weaponTable.jsp").forward(request, response);
                } else sendResultStatus(false, response);
                break;
            case "singleXml":
                String weaponXml = weaponBean.getWeaponAsHtmlById(getWeaponId(request));
                if(weaponXml != null) writer.println(weaponXml);
                else sendResultStatus(false, response);
                break;
            case "byName":
                String weaponsByName = weaponBean.getWeaponAsHtmlByName(getWeaponName(request));
                if(weaponsByName != null) writer.println(weaponsByName);
                else sendResultStatus(false, response);
                break;
            case "byLevel":
                String weaponsByLevel = weaponBean.getWeaponAsHtmlByLevel(getWeaponLevel(request));
                if(weaponsByLevel != null) writer.println(weaponsByLevel);
                else sendResultStatus(false, response);
                break;
            case "allXml":
                String allWeaponXml = weaponBean.getAllWeaponAsHtml();
                if(allWeaponXml != null) writer.println(allWeaponXml);
                else sendResultStatus(false, response);
                break;
        }
    }

    private long getWeaponId(HttpServletRequest request){
        return Long.parseLong(request.getParameter("weaponId"));
    }

    private String getWeaponName(HttpServletRequest request){
        return request.getParameter("weaponName");
    }

    private int getWeaponLevel(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("weaponLevel"));
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
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (isSuccess) writer.println("{\"status\":\"success\"}");
        else writer.println("{\"status\":\"false\"}");
    }
}