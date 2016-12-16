<%-- 
    Author     : Jakub Holy (saintjackie)
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="body">        

        <h2 align="center">Stadiums</h2>

        <table class="table">
            <thead>
                <tr>
                    <th>City</th>
                    <th>Pokemon Type</th>
                    <th>Leader</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${stadiums}" var="stadium">
                    <tr>
                        <td><c:out value="${stadium.city}"/></td>
                        <td>
                            <c:forEach items="${stadium.type}" var="type">
                                <c:out value="${type}, "/>
                            </c:forEach>
                        </td>
                        <td><c:out value="${stadium.leader.firstName} ${stadium.leader.lastName}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/stadium/new" />" class="btn btn-default">
                New Stadium
            </a>
        </sec:authorize>        

    </jsp:attribute>
</t:template>