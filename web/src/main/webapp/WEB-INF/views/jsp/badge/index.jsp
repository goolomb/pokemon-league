<%--
  Created by IntelliJ IDEA.
  User: Marek
  Date: 16-Dec-16
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="body">

        <div class="jumbotron">
            <sec:authorize access="hasRole('ADMIN')">

                <a href="${pageContext.request.contextPath}/badge/new" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    New Badge
                </a>
            </sec:authorize>
            <br/><br/>
            <table class="table">
                <thead>
                    <tr>
                        <th>Badge ID</th>
                        <th>Origin</th>
                        <th>Trainer</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${badges}" var="badge">
                        <tr>
                            <td>
                                <c:out value="${badge.id}"/>
                            </td>

                            <td class="col-xs-3 lead-column">
                                <c:out value="${badge.origin.city}"/>
                            </td>

                            <td class="col-xs-3 ">
                                <c:out value="${badge.trainer.firstName}"/>
                                <c:out value=" ${badge.trainer.lastName}"/>
                            </td>

                        </tr>
                    </c:forEach>
            </table>
        </div>


    </jsp:attribute>
</t:template>