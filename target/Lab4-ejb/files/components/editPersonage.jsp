<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<jsp:useBean id="PersonageBean" class="ru.wow.ejb.interfaces.impls.PersonageBean"/>
<jsp:useBean id="WeaponBean" class="ru.wow.ejb.interfaces.impls.WeaponBean" />
<jsp:useBean id="EquipmentBean" class="ru.wow.ejb.interfaces.impls.EquipmentBean" />
<c:set var="personage" value="${PersonageBean.findPersonage(param.id)}"/>
<div class="text-center edit-item">
    <div class="edition-pane">
        <form id="form-personage-edit">
            <table class="table table-inverse">
                <thead><tr><th></th><th>Персонаж</th><th></th></tr></thead>
                <tr>
                    <td>Имя персонажа</td>
                    <td>${personage.nickname}</td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="nickname" class="form-control" value="${personage.nickname}" required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Уровень</td>
                    <td>${personage.level}</td>
                    <td>
                        <div class="form-group input-group">
                            <span class="input-group-addon">Уровень</span>
                            <select class="form-control lvl-select" name="level" selectOnTab="true">
                                <c:forEach var="lvl" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${personage.level == lvl}">
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
                    <td>Расса</td>
                    <td>${personage.race}</td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="race" class="form-control"
                                   value="${personage.race}"
                                   required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Экипировка</td>
                    <td>${personage.equipment.name}</td>
                    <td>
                        <div class="form-group input-group">
                            <span class="input-group-addon">Экипировка</span>
                            <select class="form-control" name="equipment" data-live-search="true">
                                <jsp:useBean id="Gson" class="com.google.gson.Gson"/>
                                <c:forEach var="equipment" items="${EquipmentBean.findAllEquipment()}">
                                    <c:choose>
                                        <c:when test="${personage.equipment.id == equipment.id}">
                                            <option value="${equipment.id}" selected><c:out value="${equipment.name}"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${equipment.id}"><c:out value="${equipment.name}"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Оружие</td>
                    <td>${personage.weapon.name}</td>
                    <td>
                        <div class="form-group input-group">
                            <span class="input-group-addon">Оружие</span>
                            <select class="form-control" name="weapon" data-live-search="true">
                                <c:forEach var="weapon" items="${WeaponBean.findAllWeapon()}">
                                    <c:choose>
                                        <c:when test="${personage.weapon.id == weapon.id}">
                                            <option value="${weapon.id}" selected><c:out value="${weapon.name}"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${weapon.id}"><c:out value="${weapon.name}"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
            </table>
            <input name="personageId" value="${personage.id}" class="item-id"/>
            <button type="button" class="btn btn-primary" onclick="submitPersonageChanges()">Изменить</button>
        </form>
    </div>
    <div class="alert alert-success text-center alert-successfully-updated collapse" role="alert">
        <p>Успешно изменено!</p>
    </div>
    <div class="alert alert-error alert-server-error collapse">
        <h4>ERROR</h4>
        <p>Внутренняя ошибка сервера</p>
    </div>
</div>
