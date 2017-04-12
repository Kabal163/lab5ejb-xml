<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<div class="container text-center show-item">
    <div class="view-pane">
        <table class="table table-inverse">
            <thead><tr><th></th><th>��������</th></tr></thead>
            <tr>
                <td>��� ���������</td>
                <td>${personage.nickname}</td>
            </tr>
            <tr>
                <td>�������</td>
                <td>${personage.level}</td>
            </tr>
            <tr>
                <td>�����</td>
                <td>${personage.race}</td>
            </tr>
            <tr>
                <td>����</td>
                <td>${personage.power}</td>
            </tr>
            <tr>
                <td>������������</td>
                <td>${personage.parry}</td>
            </tr>
            <tr>
                <td>������</td>
                <td>${personage.protection}</td>
            </tr>
            <tr>
                <td>��������</td>
                <td>${personage.speed}</td>
            </tr>
            <tr>
                <td>����������</td>
                <td id="personage_equipment" onclick="findEquipment(${personage.equipment.id})">${personage.equipment.name}</td>
            </tr>
            <tr>
                <td>������</td>
                <td id="personage_weapon" onclick="findWeapon(${personage.weapon.id})">${personage.weapon.name}</td>
            </tr>
        </table>
        <div class="btn-group" role="group">
            <a href="${pageContext.servletContext.contextPath}/views/editItems.jsp?searchObject=personage&id=${personage.id}"
               class="btn btn-secondary">��������
            </a>
            <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">�������</button>
        </div>
        <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert">
            <button type="button" class="close" onclick="hideAlert('alert-delete-personage')"><i class="glyphicon glyphicon-remove"></i></button>
            <strong>�������?</strong> �� ������������� ������ ������� ������� ���������?
            <hr>
            <button type="button" class="btn btn-danger" onclick="removePersonage(${personage.id})">�������</button>
        </div>
    </div>
    <div class="alert alert-success text-center collapse alert-successfully-deleted" role="alert">
        <p>������� �������!
        <button type="button" class="btn btn-secondary" onclick="">������������</button>
        </p>
    </div>
    <div class="alert alert-error alert-server-error collapse">
        <h4>ERROR</h4>
        <p>���������� ������ �������</p>
    </div>
</div>

