<%--
  Created by IntelliJ IDEA.
  User: Marek
  Date: 16-Dec-16
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:attribute name="body">
         <form:form method="post" action="${pageContext.request.contextPath}/badge/create" modelAttribute="badgeCreate" cssClass="form-horizontal">

         </form:form>

    </jsp:attribute>
</t:template>