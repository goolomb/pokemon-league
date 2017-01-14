<%-- 
    Document   : edit
    Created on : 14.1.2017, 12:38:24
    Author     : Martin Golomb (@goolomb)
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<t:template title="Edit Trainer">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/trainer/edit"
                   modelAttribute="trainer" cssClass="form-horizontal">
            <form:hidden path="id" value="${trainer.id}"/>
            <div class="form-group ${firstName_error?'has-error':''}">
                <form:label path="firstName" cssClass="col-sm-2 control-label">First Name</form:label>
                    <div class="col-sm-4">
                    <form:input path="firstName" required="true"/>
                    <form:errors path="firstName"/>
                </div>
            </div>
            <div class="form-group ${lastName_error?'has-error':''}">
                <form:label path="lastName" cssClass="col-sm-2 control-label">Last Name</form:label>
                    <div class="col-sm-4">
                    <form:input path="lastName" required="true"/>
                    <form:errors path="lastName"/>
                </div>
            </div>
            <div class="form-group ${birthDate_error?'has-error':''}">
                <form:label path="birthDate" cssClass="col-sm-2 control-label">Birth date</form:label>
                    <div class="col-sm-4">
                    <form:input path="birthDate" type="date" required="true" />
                    <form:errors path="birthDate"/>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Edit trainer</button>
        </form:form>
    </jsp:attribute>
</t:template>