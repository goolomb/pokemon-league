<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" required="false" fragment="true" %>
<%@ attribute name="body" required="true" fragment="true" %>
<%@ attribute name="scripts" required="false" fragment="true" %>




<!DOCTYPE html>
<html lang="cs">
    <head>
        <meta charset="utf-8">

        <title>Pokemon app</title>

        <!-- bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/resources/css/templateStyle.css" rel="stylesheet">
    </head>

    <body class="body" background="${pageContext.request.contextPath}/resources/img/pokemon.jpg">
        <div class="content wrapper">
            <!-- navigation bar -->
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}">Pokémon App <img id="logo" src="${pageContext.request.contextPath}/resources/img/Pokeball.PNG"/></a>

                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav pull-right">
                            <li><a href="${pageContext.request.contextPath}/badge/index">Badges</a></li>
                            <li><a href="${pageContext.request.contextPath}/pokemon/list">Pokemons</a></li>
                            <li><a href="${pageContext.request.contextPath}/trainer/list">Trainers</a></li>
                            <li><a href="${pageContext.request.contextPath}/stadium/list">Stadiums</a></li>
                                <sec:authorize access="isAuthenticated()">
                                <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Log Out</a></li>
                                </sec:authorize>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- page body -->
            <div class="container-fluid">
                <sec:authorize access="isAuthenticated()">
                    <p style="margin: 0 10px 0 0">Logged in as <b><sec:authentication property="principal.username" /></b></p>

                </sec:authorize>



                <!-- page body -->    
                <div class="row content text-center">
                    
                    <!-- alerts messages -->
                    <c:if test="${not empty alert_success}">
                        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
                    </c:if>
                    <c:if test="${not empty alert_warning}">
                        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
                    </c:if>
                    <c:if test="${not empty alert_danger}">
                        <div class="alert alert-danger" role="alert"><c:out value="${alert_danger}"/></div>
                    </c:if>
                    <!--end alerts-->
                    
                    <div class="col-sm-2 sidenav">
                    </div>
                    <div class="col-sm-8 text-left">
                        <jsp:invoke fragment="body"/>
                    </div>
                    <div class="col-sm-2 sidenav">
                    </div>
                </div>
            </div>

        </div>

        <!-- footer -->
        <footer class="container-fluid footer text-center">
            <p>© 2016 Martina Minátová, Marek Perichta, Martin Golomb, Jakub Holý</p>
        </footer>

        <!-- JavaScript libraries -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <jsp:invoke fragment="scripts"/>

    </body>
</html>