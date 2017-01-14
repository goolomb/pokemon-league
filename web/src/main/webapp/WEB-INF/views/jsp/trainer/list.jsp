<%-- 
    Author     : Martin Golomb (goolomb)
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:template>
    <jsp:attribute name="body">

        <sec:authorize access="hasRole('ADMIN')">
            <a href="/pa165/trainer/new" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                New Trainer
            </a>
        </sec:authorize>


        <table class="table">
            <thead>

                <tr>
                    <th>Trainer ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Pokemons</th>
                    <th>Badges</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${trainers}" var="trainer">
                    <tr>
                        <td><c:out value="${trainer.id}"/></td>
                        <td><c:out value="${trainer.firstName}"/></td>
                        <td><c:out value="${trainer.lastName}"/></td>
                        <td><fmt:formatDate value="${trainer.birthDate}" pattern="dd.MM.yyyy" /></td>
            <%--<td>
                <c:forEach items="${trainer.pokemons}" var="pokemon">
                    <c:out value="${pokemon.name} "/>
                </c:forEach>
            </td>
            <td>
                <c:forEach items="${trainer.badges}" var="badge">
                    <c:out value="${badge.origin.city} "/>
                </c:forEach>
            </td>
            --%>

        </tr>
    </c:forEach>
</tbody>
</table>
</jsp:attribute>
</t:template>
