<%--
  Author: Martina Minatova
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
    <jsp:attribute name="body">

        <h2 align="center">Pokemons</h2>

        <sec:authorize access="hasRole('ADMIN')">

            <a href="/pa165/pokemon/new" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                New Pokemon
            </a>
        </sec:authorize>

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
                        <td>
                            <sec:authorize access="hasRole('ADMIN')">
                            <a href="<c:url value="/pokemon/edit/${pokemon.id}" />" class="btn btn-default btn-xs"/>
                                Edit
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                            </a>
                            </sec:authorize>
                        </td>
                        <td>
                            <sec:authorize access="hasRole('ADMIN')">
                            <a href="<c:url value="/pokemon/delete/${pokemon.id}" />" class="btn btn-default btn-xs"/>
                                Delete
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</t:template>
