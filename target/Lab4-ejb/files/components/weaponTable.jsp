<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<div class="container text-center show-item">
    <div class="view-pane">
        <table class="table table-inverse">
            <thead><tr><th></th><th>Оружие</th></tr></thead>
            <tr>
                <td>Название оружия</td>
                <td>${weapon.name}</td>
            </tr>
            <tr>
                <td>Уровень</td>
                <td>${weapon.level}</td>
            </tr>
            <tr>
                <td>К урону</td>
                <td>${weapon.power}</td>
            </tr>
            <tr>
                <td>К паррированию</td>
                <td>${weapon.parry}</td>
            </tr>
            <tr>
                <td>Цена</td>
                <td>${weapon.price}</td>
            </tr>
        </table>
        <c:if test="${weapon.id != 2}">
        <div class="btn-group" role="group">
            <a href="${pageContext.servletContext.contextPath}/views/editItems.jsp?searchObject=weapon&id=${weapon.id}"
               class="btn btn-secondary">Изменить
            </a>
            <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">Удалить</button>
        </div>
        <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert">
            <strong>Удалить?</strong> Вы действительно хотите удалить данное оружие?
            <hr>
            <button type="button" class="btn btn-danger" onclick="removeWeapon(${weapon.id})">Удалить</button>
        </div>
        </c:if>
    </div>
    <div class="alert alert-success text-center collapse alert-successfully-deleted" role="alert">
        <p>Успешно удалено!</p>
    </div>
    <div class="alert alert-error alert-server-error collapse">
        <h4>ERROR</h4>
        <p>Внутренняя ошибка сервера</p>
    </div>
</div>


