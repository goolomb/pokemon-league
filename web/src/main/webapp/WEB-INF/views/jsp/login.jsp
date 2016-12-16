<%-- 
    Author     : Martin Golomb (goolomb)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="body">
        <div align="center">
            <div id="status_message" style="color:green">${alert_success}</div>
            <table>
                <tr>
                    <td>
                        <div class="login-card">
                            username:admin/user password:admin/user
                            <c:url value="/login" var="loginUrl"/>
                            <form action="${loginUrl}" method="post">
                                <input type="text" id="username" name="username" size="25" placeholder="Username">
                                <input type="password" id="password" name="password" size="15" placeholder="Password">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" name="login" class="login login-submit" value="Log in">
                            </form>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </jsp:attribute>
</t:template>