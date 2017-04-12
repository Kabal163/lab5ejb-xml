<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<div class="container text-center show-item">
    <div class="view-pane">
        <table class="table table-inverse">
            <thead><tr><th></th><th>Персонаж</th></tr></thead>
            <tr>
                <td>Имя персонажа</td>
                <td>${personage.nickname}</td>
            </tr>
            <tr>
                <td>Уровень</td>
                <td>${personage.level}</td>
            </tr>
            <tr>
                <td>Расса</td>
                <td>${personage.race}</td>
            </tr>
            <tr>
                <td>Сила</td>
                <td>${personage.power}</td>
            </tr>
            <tr>
                <td>Паррирование</td>
                <td>${personage.parry}</td>
            </tr>
            <tr>
                <td>Защита</td>
                <td>${personage.protection}</td>
            </tr>
            <tr>
                <td>Скорость</td>
                <td>${personage.speed}</td>
            </tr>
            <tr>
                <td>Экипировка</td>
                <td id="personage_equipment" onclick="findEquipment(${personage.equipment.id})">${personage.equipment.name}</td>
            </tr>
            <tr>
                <td>Оружие</td>
                <td id="personage_weapon" onclick="findWeapon(${personage.weapon.id})">${personage.weapon.name}</td>
            </tr>
        </table>
        <div class="btn-group" role="group">
            <a href="${pageContext.servletContext.contextPath}/views/editItems.jsp?searchObject=personage&id=${personage.id}"
               class="btn btn-secondary">Изменить
            </a>
            <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">Удалить</button>
        </div>
        <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert">
            <button type="button" class="close" onclick="hideAlert('alert-delete-personage')"><i class="glyphicon glyphicon-remove"></i></button>
            <strong>Удалить?</strong> Вы действительно хотите удалить данного персонажа?
            <hr>
            <button type="button" class="btn btn-danger" onclick="removePersonage(${personage.id})">Удалить</button>
        </div>
    </div>
    <div class="alert alert-success text-center collapse alert-successfully-deleted" role="alert">
        <p>Успешно удалено!
        <button type="button" class="btn btn-secondary" onclick="">Восстановить</button>
        </p>
    </div>
    <div class="alert alert-error alert-server-error collapse">
        <h4>ERROR</h4>
        <p>Внутренняя ошибка сервера</p>
    </div>
</div>

