<%-- 
    Author     : Martin Golomb (goolomb)
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:attribute name="body">
        <form:form method="post" action="${actionUrl}/trainer/create"
                   modelAttribute="trainerCreate" cssClass="form-horizontal">
    <div class="form-group">
        <form:label path="firstname" cssClass="col-sm-1 control-label">Firstname</form:label>
        <div class="col-sm-5">
            <form:input path="firstname" cssClass="form-control"/>
            <form:errors path="firstname" cssClass="help-block"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="lasttname" cssClass="col-sm-1 control-label">Lastname</form:label>
        <div class="col-sm-5">
            <form:input path="lastname" cssClass="form-control"/>
            <form:errors path="lastname" cssClass="help-block"/>
        </div>
    </div>
            <div class="form-group">
                <form:label path="birthdate" cssClass="col-sm-1 control-label">Birthdate</form:label>
                <div class="col-sm-5">
                    <form:input path="birthdate" cssClass="form-control"/>
                    <form:errors path="birthdate" cssClass="help-block"/>
                </div>
            </div>
        <div class="form-group">
            <form:label path="pokemons" cssClass="col-sm-1 control-label">Pokemons</form:label>
            <div class="col-sm-5">
                    <form:select path="pokemons" cssClass="form-control">
                        <c:forEach items="${pokemons}" var="pokemon">
                            <form:option value="${pokemon}">${pokemon}</form:option>
                        </c:forEach>
                    </form:select>
                <form:errors path="pokemon" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="badges" cssClass="col-sm-1 control-label">Badges</form:label>
            <div class="col-sm-5">
                    <form:select path="badges" cssClass="form-control">
                        <c:forEach items="${badges}" var="badge">
                            <form:option value="${badge}">${badge}</form:option>
                        </c:forEach>
                    </form:select>
                <form:errors path="badge" cssClass="help-block"/>
            </div>
        </div>

            <button class="btn btn-primary" type="submit">Create Trainer</button>
        </form:form>
    </jsp:attribute>
</t:template>
