<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<jsp:useBean id="WeaponBean" class="ru.wow.ejb.interfaces.impls.WeaponBean" />
<jsp:useBean id="EquipmentBean" class="ru.wow.ejb.interfaces.impls.EquipmentBean" />
<div class="container create-item">
    <div class="row">
        <div class="col-xs-8 col-sm-4 creation-pane">
            <form class="creation-form" id="form-personage-creation">
                <div class="form-group">
                    <input type="text" name="nickname" class="form-control" placeholder="Имя персонажа" required>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">Уровень</span>
                    <select class="form-control lvl-select" name="level" selectOnTab="true">
                        <c:forEach var="lvl" begin="1" end="5">
                            <option value="${lvl}">${lvl}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" name="race" class="form-control"
                           placeholder="Расса"
                           required>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">Оружие</span>
                    <select class="form-control" name="weapon" data-live-search="true">
                        <c:forEach var="weapon" items="${WeaponBean.findAllWeapon()}">
                            <option value="${weapon.id}"><c:out value="${weapon.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">Экипировка</span>
                    <select class="form-control" name="equipment" data-live-search="true">
                        <jsp:useBean id="Gson" class="com.google.gson.Gson"/>
                        <c:forEach var="equipment" items="${EquipmentBean.findAllEquipment()}">
                            <option value="${equipment.id}"><c:out value="${equipment.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <hr>
                <button type="button" class="btn btn-primary btn-block submit" onclick="createPersonage()">Создать</button>
            </form>
            <div class="alert alert-success text-center alert-successfully-created collapse" role="alert">
                <p>Успешно добавлено!</p>
            </div>
            <div class="alert alert-error alert-server-error collapse">
                <h4>ERROR</h4>
                <p>Внутренняя ошибка сервера</p>
            </div>
        </div>
    </div>
</div>
