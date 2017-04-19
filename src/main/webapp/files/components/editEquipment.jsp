<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<jsp:useBean id="EquipmentBean" class="ru.wow.ejb.interfaces.impls.EquipmentBean"/>
<c:set var="equipment" value="${EquipmentBean.findEquipment(param.id)}"/>
<div class="text-center edit-item">
    <div class="edition-pane">
        <form id="form-equipment-edit">
            <table class="table table-inverse">
                <thead><tr><th></th><th>Единицы таланта: </th><th class="scores">1000</th></tr></thead>
                <tr>
                    <td>Название экипировки</td>
                    <td>${equipment.name}</td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" value="${equipment.name}" required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Уровень</td>
                    <td>${equipment.level}</td>
                    <td>
                        <div class="form-group input-group">
                            <span class="input-group-addon">Уровень</span>
                            <select class="form-control lvl-select" name="level" onchange="recountLvlScores()" selectOnTab="true">
                                <c:forEach var="lvl" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${equipment.level == lvl}">
                                            <option value="${lvl}" selected>${lvl}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${lvl}">${lvl}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>К защите</td>
                    <td>${equipment.protection}</td>
                    <td>
                        <div class="form-group">
                            <input type="number" name="protection" class="form-control statistics"
                                   value="${equipment.protection}"
                                   onfocus="addScores(this.value)"
                                   onblur="minusScores(this.value)">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>К скорости</td>
                    <td>${equipment.speed}</td>
                    <td>
                        <div class="form-group">
                            <input type="number" name="speed" class="form-control statistics"
                                   value="${equipment.speed}"
                                   onfocus="addScores(this.value)"
                                   onblur="minusScores(this.value)">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>К паррированию</td>
                    <td>${equipment.parry}</td>
                    <td>
                        <div class="form-group">
                            <input type="number" name="parry" class="form-control statistics"
                                   value="${equipment.parry}"
                                   onfocus="addScores(this.value)"
                                   onblur="minusScores(this.value)">
                        </div>
                    </td>
                </tr>
            </table>
            <input name="equipmentId" value="${equipment.id}" class="item-id"/>
            <button type="button" class="btn btn-outline-primary" onclick="submitEquipmentChanges()">Изменить</button>
        </form>
    </div>
    <div class="alert alert-success text-center alert-successfully-updated collapse" role="alert">
        <p>Успешно изменено!</p>
    </div>
    <div class="alert alert-warning alert-invalid-scores collapse">
        <h4>Некорректное число единиц талантов</h4>
        <p>Используйте корректное число единиц талантов, либо увеличте уровень оружия</p>
    </div>
    <div class="alert alert-error alert-server-error collapse">
        <h4>ERROR</h4>
        <p>Внутренняя ошибка сервера</p>
    </div>
</div>