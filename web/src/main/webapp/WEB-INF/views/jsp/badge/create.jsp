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
         <form:form method="post" action="${pageContext.request.contextPath}/badge/create" modelAttribute="badgeCreate"
                    cssClass="form-horizontal">

                <div class="form-group ${trainer_error?'has-error':''}">
                    <form:label path="trainer" cssClass="col-sm-2 control-label">Trainer</form:label>
                    <div class="col-sm-6">
                    <form:select path="trainer" cssClass="form-control">
                        <c:forEach items="${trainers}" var="t">
                            <form:option value="${t.id}">${t.firstName} ${t.lastName}</form:option>
                        </c:forEach>
                    </form:select>
                        <form:errors path="trainer" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${origin_error?'has-error':''}">
                    <form:label path="origin" cssClass="col-sm-2 control-label">Origin</form:label>
                    <div class="col-sm-6">
                    <form:select path="origin" cssClass="form-control">
                        <c:forEach items="${origins}" var="o">
                            <form:option value="${o.id}">${o.city}</form:option>
                        </c:forEach>
                    </form:select>
                        <form:errors path="origin" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-2">
                        <button class="btn btn-default" type="submit">Create Badge</button>
                    </div>
                </div>

         </form:form>
    </jsp:attribute>
</t:template>