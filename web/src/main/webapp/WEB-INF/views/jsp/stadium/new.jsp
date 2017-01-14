<%-- 
    Author     : Jakub Holy (saintjackie)
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template title="New Stadium">
    <jsp:attribute name="body">
        
        
        <form:form method="post" action="${pageContext.request.contextPath}/stadium/create"
                   modelAttribute="stadiumCreate" cssClass="form-horizontal">
            <h2 align="center">Create new stadium</h2>
            <br>
            <div class="form-group ${city_error?'has-error':''}">
                <form:label path="city" cssClass="col-sm-2 control-label">City</form:label>
                    <div class="col-sm-4">
                    <form:input path="city" cssClass="form-control"/>
                    <form:errors path="city" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${type_error?'has-error':''}">
                <form:label path="type" cssClass="col-sm-2 control-label">Pokemon Type</form:label>
                    <div class="col-sm-4" cssClass="form-control">
                    <form:select path="type" cssClass="form-control">
                        <form:options items="${pokemonTypes}" />
                    </form:select>
                    <form:errors path="type" cssClass="help-block"/>
                </div>
            </div>


            <div class="form-group ${leaderId_error?'has-error':''}">
                <form:label path="leaderId" cssClass="col-sm-2 control-label">Leader</form:label>
                    <div class="col-sm-4">
                    <form:select path="leaderId" cssClass="form-control">
                        <c:forEach items="${trainers}" var="t">
                            <form:option value="${t.id}">${t.firstName} ${t.lastName}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="leaderId" cssClass="help-block"/>
                </div>
            </div>



            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                    <button class="btn btn-default" type="submit">Create stadium</button>
                </div>  
            </div>
            </form:form>
        </jsp:attribute>
    </t:template>