<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
    <jsp:attribute name="body">
        <a href="/pa165/pokemon/new" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Pokemon
        </a>


<table class="table">
        <thead>

<tr>
    <th>Pokemon ID</th>
    <th>Name</th>
    <th>Nickname</th>
    <th>Level</th>
    <th>Type</th>
    <th>Trainer</th>
</tr>
</thead>
<tbody>
        <c:forEach items="${pokemons}" var="pokemon">
    <tr>
        <td><c:out value="${pokemon.id}"/></td>
        <td><c:out value="${pokemon.name}"/></td>
        <td><c:out value="${pokemon.nickname}"/></td>
        <td><c:out value="${pokemon.level}"/></td>
        <td>
            <c:forEach items="${pokemon.type}" var="type">
                <c:out value="${type} "/>
            </c:forEach>
        </td>
        <td><c:out value="${pokemon.trainer.firstName} ${pokemon.trainer.lastName}"/></td>

    </tr>
</c:forEach>
    </tbody>
</table>
</jsp:attribute>
</t:template>
