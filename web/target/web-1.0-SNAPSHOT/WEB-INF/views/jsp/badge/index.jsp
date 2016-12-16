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
            <table class="table">
                <thead>
                <tr>
                    <th>Origin</th>
                    <th>Trainer</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${badges}" var="badge">
                <tr>
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