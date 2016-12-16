<%-- 
    Author     : Jakub Holy (saintjackie)
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:attribute name="body">
        
        
        <form:form method="post" action="${pageContext.request.contextPath}/stadium/create"
                   modelAttribute="stadiumCreate" cssClass="form-horizontal">
            <h2 align="center">Create new stadium</h2>
            <br>
            <div class="form-group ${city_error?'has-error':''}">
                <form:label path="city" cssClass="col-sm-2 control-label">City</form:label>
                    <div class="col-sm-6">
                    <form:input path="city" cssClass="form-control"/>
                    <form:errors path="city" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${type_error?'has-error':''}">
                <form:label path="type" cssClass="col-sm-2 control-label">Pokemon Type</form:label>
                    <div class="col-sm-8">
                    <form:select path="type">
                        <form:options items="${pokemonTypes}" />
                    </form:select>
                    <form:errors path="type" cssClass="help-block"/>
                </div>
            </div>


            <div class="form-group ${leader_error?'has-error':''}">
                <form:label path="leader" cssClass="col-sm-2 control-label">Trainer</form:label>
                    <div class="col-sm-6">
                    <form:select path="leader">
                        <form:options items="${trainers}" />
                    </form:select>
                    <form:errors path="leader" cssClass="help-block"/>
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