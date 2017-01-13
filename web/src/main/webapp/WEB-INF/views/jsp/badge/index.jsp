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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
    <jsp:attribute name="body">

        <div class="jumbotron">
            <h3>Badge list</h3>
            <br/><br/>
            <table class="table">
                <thead>
                    <tr>
                        <th>Badge ID</th>
                        <th>Trainer</th>
                        <th>Origin</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${badges}" var="badge">
                        <tr>
                            <td class="col-xs-2 lead-column " >
                                <c:out value="${badge.id}"/>
                            </td>

                            <td class="col-xs-2 ">
                                <c:out value="${badge.trainer.firstName}"/>
                                <c:out value=" ${badge.trainer.lastName}"/>
                            </td>

                            <td class="col-xs-2 lead-column">
                                <c:out value="${badge.origin.city}"/>
                            </td>
                            <sec:authorize access="hasRole('ADMIN')">
                            <td class="col-xs-1">
                                <form:form method="post" action="${pageContext.request.contextPath}/badge/remove/${badge.id}">
                                    <button class="btn btn-default" type="submit" style="color:#B22222;">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true" ></span>
                                    </button>
                                </form:form>
                            </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
            </table>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="${pageContext.request.contextPath}/badge/create" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    New Badge
                </a>
            </sec:authorize>
        </div>


    </jsp:attribute>
</t:template>