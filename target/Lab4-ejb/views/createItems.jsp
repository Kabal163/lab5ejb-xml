<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.servletContext.contextPath}/files/css/creationStyles.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <title>Создание оружия</title>
</head>
<body>
    <c:set var="creationObject" value="${param.name}"/>
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
        </div>
    </nav>
    <div class="container">
        <c:choose>
            <c:when test="${creationObject.equals(\"weapon\")}">
                <jsp:include page="../files/components/createWeapon.jsp"/>
            </c:when>
            <c:when test="${creationObject.equals(\"personage\")}">
                <jsp:include page="../files/components/createPersonage.jsp"/>
            </c:when>
            <c:when test="${creationObject.equals(\"equipment\")}">
                <jsp:include page="../files/components/createEquipment.jsp"/>
            </c:when>
        </c:choose>
    </div>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script src="${pageContext.servletContext.contextPath}/files/js/creation.js"></script>
    <script src="${pageContext.servletContext.contextPath}/files/js/scoresCounting.js"></script>
    <script src="${pageContext.servletContext.contextPath}/files/js/alerts.js"></script>
</body>
</html>
