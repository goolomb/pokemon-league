<%-- 
    Author     : Martin Golomb (goolomb)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
    <jsp:attribute name="body">

        <h2 align="center">Trainers</h2>
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
                        <td><c:out value="${badgesCount[trainer.id]}"/></td>
                        <td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <a href="<c:url value="/trainer/edit/${trainer.id}" />" class="btn btn-default btn-xs"/>Edit
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                </a>
                            </sec:authorize>
                        </td>

                        <td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <form:form method="post" action="${pageContext.request.contextPath}/trainer/delete/${trainer.id}">
                                    <button class="btn btn-default" type="submit" style="color:#B22222;">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true" ></span>
                                    </button>
                                </form:form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</t:template>
