<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<div class="container text-center show-item">
    <div class="view-pane">
        <table class="table table-inverse">
            <thead><tr><th></th><th>����������</th></tr></thead>
            <tr>
                <td>�������� ����������</td>
                <td>${equipment.name}</td>
            </tr>
            <tr>
                <td>�������</td>
                <td>${equipment.level}</td>
            </tr>
            <tr>
                <td>� ������</td>
                <td>${equipment.protection}</td>
            </tr>
            <tr>
                <td>� ��������</td>
                <td>${equipment.speed}</td>
            </tr>
            <tr>
                <td>� ������������</td>
                <td>${equipment.parry}</td>
            </tr>
            <tr>
                <td>����</td>
                <td>${equipment.price}</td>
            </tr>
        </table>
        <c:if test="${equipment.id != 7}">
        <div class="btn-group" role="group">
            <a href="${pageContext.servletContext.contextPath}/views/editItems.jsp?searchObject=equipment&id=${equipment.id}"
               class="btn btn-secondary">��������
            </a>
            <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">�������</button>
        </div>
        <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert" id="alert-delete-equipment">
            <button type="button" class="close" onclick="hideAlert('alert-delete-equipment')"><i class="glyphicon glyphicon-remove"></i></button>
            <strong>�������?</strong> �� ������������� ������ ������� ������ ����������?
            <hr>
            <button type="button" class="btn btn-danger" onclick="removeEquipment(${equipment.id})">�������</button>
        </div>
        </c:if>
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
