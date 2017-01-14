<%-- 
    Author     : Jakub Holy (saintjackie)
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
    <jsp:attribute name="body">        

        <h2 align="center">Stadiums</h2>

        <table class="table">
            <thead>
                <tr>
                    <th>City</th>
                    <th>Pokemon Type</th>
                    <th>Leader</th>
                    <th>Actions</th>


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
                        <td>

                            <sec:authorize access="hasRole('ADMIN')">
                                <form:form method="post" action="${pageContext.request.contextPath}/stadium/delete/${stadium.id}">
                                    <a href="<c:url value="/stadium/edit/${stadium.id}" />" class="btn btn-default"/>
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                    </a>
                                    <button type="submit" class="btn btn-default" style="color:#B22222;">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </button>
                                </form:form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/stadium/new" />" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                New Stadium
            </a>
        </sec:authorize>        

    </jsp:attribute>
</t:template>