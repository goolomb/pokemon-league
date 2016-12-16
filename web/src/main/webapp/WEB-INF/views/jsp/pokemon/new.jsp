<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/pokemon/create"
                   modelAttribute="pokemonCreate" cssClass="form-horizontal">
    <div class="form-group">
        <form:label path="name" cssClass="col-sm-1 control-label">Name</form:label>
        <div class="col-sm-5">
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="nickname" cssClass="col-sm-1 control-label">Nickname</form:label>
        <div class="col-sm-5">
            <form:input path="nickname" cssClass="form-control"/>
            <form:errors path="nickname" cssClass="help-block"/>
        </div>
    </div>
            <div class="form-group">
                <form:label path="level" cssClass="col-sm-1 control-label">Level</form:label>
                <div class="col-sm-5">
                    <form:input path="level" cssClass="form-control"/>
                    <form:errors path="level" cssClass="help-block"/>
                </div>
            </div>
        <div class="form-group">
            <form:label path="type" cssClass="col-sm-1 control-label">Type</form:label>
            <div class="col-sm-5">
                    <form:select path="type" cssClass="form-control">
                        <c:forEach items="${types}" var="type">
                            <form:option value="${type}">${type}</form:option>
                        </c:forEach>
                    </form:select>
                <form:errors path="type" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="trainer" cssClass="col-sm-1 control-label">Trainer</form:label>
            <div class="col-sm-5">
                    <form:select path="trainer" cssClass="form-control">
                        <c:forEach items="${trainers}" var="trainer">
                            <form:option value="${trainer}">${trainer.firstName} ${trainer.lastName}</form:option>
                        </c:forEach>
                    </form:select>
                <form:errors path="trainer" cssClass="help-block"/>
            </div>
        </div>

            <button class="btn btn-primary" type="submit">Create pokemon</button>
        </form:form>
    </jsp:attribute>
</t:template>