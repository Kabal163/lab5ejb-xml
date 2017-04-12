<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <!-- CSS -->
    <link href="${pageContext.servletContext.contextPath}/files/css/findingStyles.css" rel="stylesheet">
    <title>Просмотр элемента</title>
</head>
<body>
    <!-- Navigation -->
    <c:set var="searchObject" value="${param.name}"/>
    <nav class="navbar fixed-top navbar-toggleable-sm navbar-inverse bg-inverse">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">WoW</a>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Создать<span class="sr-only">(current)</span></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/views/createItems.jsp?name=personage">Персонажа</a>
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/views/createItems.jsp?name=weapon">Оружие</a>
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/views/createItems.jsp?name=equipment">Экипировку</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Найти<span class="sr-only">(current)</span></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/views/viewItems.jsp?name=personage">Персонажа</a>
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/views/viewItems.jsp?name=weapon">Оружие</a>
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/views/viewItems.jsp?name=equipment">Экипировку</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <c:choose>
                    <c:when test="${searchObject.equals(\"equipment\")}">
                        <jsp:useBean id="EquipmentBean" class="ru.wow.ejb.interfaces.impls.EquipmentBean" />
                        <div class="input-group navbar-form">
                            <select class="form-control mr-sm-2 select-item" name="equipmentId" id="select-equipment">
                                <c:forEach var="equipment" items="${EquipmentBean.findAllEquipment()}">
                                    <option value="${equipment.id}"><c:out value="${equipment.name}"/></option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="findEquipment()">Найти экипировку</button>
                        </div>
                    </c:when>
                    <c:when test="${searchObject.equals(\"weapon\")}">
                        <jsp:useBean id="WeaponBean" class="ru.wow.ejb.interfaces.impls.WeaponBean" />
                        <div class="input-group navbar-form">
                            <select class="form-control mr-sm-2 select-item" name="weaponId" id="select-weapon">
                                <c:forEach var="weapon" items="${WeaponBean.findAllWeapon()}">
                                    <option value="${weapon.id}"><c:out value="${weapon.name}"/></option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="findWeapon()">Найти оружие</button>
                        </div>
                    </c:when>
                    <c:when test="${searchObject.equals(\"personage\")}">
                        <jsp:useBean id="PersonageBean" class="ru.wow.ejb.interfaces.impls.PersonageBean" />
                        <div class="input-group navbar-form">
                            <select class="form-control mr-sm-2 select-item" name="personageId" id="select-personage">
                                <c:forEach var="personage" items="${PersonageBean.findAllPersonage()}">
                                    <option value="${personage.id}"><c:out value="${personage.nickname}"/></option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="findPersonage()">Найти персонажа</button>
                        </div>
                    </c:when>
                </c:choose>
            </form>
        </div>
    </nav>
    <div class="container view-holder">

    </div>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script src="${pageContext.servletContext.contextPath}/files/js/finding.js"></script>
    <script src="${pageContext.servletContext.contextPath}/files/js/removing.js"></script>
    <script src="${pageContext.servletContext.contextPath}/files/js/alerts.js"></script>
</body>
</html>
