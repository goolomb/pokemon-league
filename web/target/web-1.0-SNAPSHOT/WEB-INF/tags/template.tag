<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="${pageContext.request.contextPath}/resources/css//templateStyle.css" rel="stylesheet">
</head>

<body class="body">
<div class="wrapper">
<!-- navigation bar -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Pokémon App</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/badge">Badges</a></li>
                <li><a href="${pageContext.request.contextPath}/pokemon">Pokemons</a></li>
                <li><a href="${pageContext.request.contextPath}/trainer">Trainers</a></li>
                <li><a href="${pageContext.request.contextPath}/stadium">Stadiums</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- page body -->
<div class="container-fluid text-center">
    <div class="row content">
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
    <p>Sem prídu mená</p>
</footer>

<!-- JavaScript libraries -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<jsp:invoke fragment="scripts"/>

</body>
</html>