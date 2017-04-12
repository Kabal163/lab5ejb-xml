<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<div class="container create-item">
    <div class="row">
        <div class="col-xs-8 col-sm-4 creation-pane">
            <p><h3>������� �������: <span class="scores">1000</span></h3></p>
            <form class="creation-form" id="form-weapon-creation">
                <div class="form-group">
                    <input type="text" name="name" class="form-control" placeholder="��������" required>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">�������</span>
                    <select class="form-control lvl-select" name="level" onchange="recountLvlScores()" selectOnTab="true">
                        <c:forEach var="lvl" begin="1" end="5">
                            <option value="${lvl}">${lvl}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <input type="number" name="damage" class="form-control statistics"
                           placeholder="� �����"
                           onfocus="addScores(this.value)"
                           onblur="minusScores(this.value)">
                </div>
                <div class="form-group">
                    <input type="number" name="parry" class="form-control statistics"
                           placeholder="� �����������"
                           onfocus="addScores(this.value)"
                           onblur="minusScores(this.value)">
                </div>
                <hr>
                <button type="button" class="btn btn-primary btn-block submit" onclick="createWeapon()">�������</button>
            </form>
            <div class="alert alert-success text-center alert-successfully-created collapse" role="alert">
                <p>������� ���������!</p>
            </div>
            <div class="alert alert-warning alert-invalid-scores collapse">
                <h4>������������ ����� ������ ��������</h4>
                <p>����������� ���������� ����� ������ ��������, ���� �������� ������� ������</p>
            </div>
            <div class="alert alert-error alert-server-error collapse">
                <h4>ERROR</h4>
                <p>���������� ������ �������</p>
            </div>
        </div>
    </div>
</div>
