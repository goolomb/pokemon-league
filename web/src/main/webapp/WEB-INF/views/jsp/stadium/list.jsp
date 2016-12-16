<%-- 
    Document   : list
    Created on : 16.12.2016, 13:46:53
    Author     : Jakub Holy (saintjackie)
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="body">
        <my:a href="/new" class="btn btn-default">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Stadium
        </my:a>

        <table class="table">
            <thead>
                <tr>
                    <th>City</th>
                    <th>Type</th>
                    <th>Leader</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${stadiums}" var="stadium">
                <tr>
                <td><c:out value="${stadium.city}"/></td>
                <td>
                    <c:forEach items="${stadium.type}" var="type">
                    <c:out value="${type} "/>
                    </c:forEach>
                </td>
                <td><c:out value="${stadium.leader.firstName} ${stadium.leader.lastName}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



</jsp:attribute>
</t:template>