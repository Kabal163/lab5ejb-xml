<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<div class="container text-center show-item">
    <div class="view-pane">
        <table class="table table-inverse">
            <thead><tr><th></th><th>Экипировка</th></tr></thead>
            <tr>
                <td>Название экипировки</td>
                <td>${equipment.name}</td>
            </tr>
            <tr>
                <td>Уровень</td>
                <td>${equipment.level}</td>
            </tr>
            <tr>
                <td>К защите</td>
                <td>${equipment.protection}</td>
            </tr>
            <tr>
                <td>К скорости</td>
                <td>${equipment.speed}</td>
            </tr>
            <tr>
                <td>К паррированию</td>
                <td>${equipment.parry}</td>
            </tr>
            <tr>
                <td>Цена</td>
                <td>${equipment.price}</td>
            </tr>
        </table>
        <c:if test="${equipment.id != 7}">
        <div class="btn-group" role="group">
            <a href="${pageContext.servletContext.contextPath}/views/editItems.jsp?searchObject=equipment&id=${equipment.id}"
               class="btn btn-secondary">Изменить
            </a>
            <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">Удалить</button>
        </div>
        <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert" id="alert-delete-equipment">
            <button type="button" class="close" onclick="hideAlert('alert-delete-equipment')"><i class="glyphicon glyphicon-remove"></i></button>
            <strong>Удалить?</strong> Вы действительно хотите удалить данную экипировку?
            <hr>
            <button type="button" class="btn btn-danger" onclick="removeEquipment(${equipment.id})">Удалить</button>
        </div>
        </c:if>
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
