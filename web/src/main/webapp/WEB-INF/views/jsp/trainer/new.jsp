<%-- 
    Author     : Martin Golomb (goolomb)
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/trainer/create"
                   modelAttribute="trainerCreate" cssClass="form-horizontal">
            
            <div class="form-group">
                <form:label path="firstName" cssClass="col-sm-1 control-label">FirstName</form:label>
                    <div class="col-sm-5">
                    <form:input path="firstName" cssClass="form-control"/>
                    <form:errors path="firstName" cssClass="help-block"/>
                </div>
            </div>
            
            <div class="form-group">
                <form:label path="lastName" cssClass="col-sm-1 control-label">LastName</form:label>
                    <div class="col-sm-5">
                    <form:input path="lastName" cssClass="form-control"/>
                    <form:errors path="lastName" cssClass="help-block"/>
                </div>
            </div>
            
            <div class="form-group">
                <form:label path="birthDate" cssClass="col-sm-1 control-label">BirthDate</form:label>
                    <div class="col-sm-5">
                    <form:input path="birthDate" type="date" value="1993-12-08" required="true" cssClass="form-control"/>
                    <form:errors path="birthDate" cssClass="help-block"/>
                </div>
            </div>
            
            <%--div class="form-group">
                <form:label path="pokemons" cssClass="col-sm-1 control-label">Pokemons</form:label>
                    <div class="col-sm-5">
                    <form:select path="pokemons" cssClass="form-control">
                        <c:forEach items="${pokemons}" var="pokemon">
                            <form:option value="${pokemon}">${pokemon.name} ${pokemon.nickname}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="pokemon" cssClass="help-block"/>
                </div>
            </div>
            --%>
            
            <%--div class="form-group">
                <form:label path="badges" cssClass="col-sm-1 control-label">Badges</form:label>
                    <div class="col-sm-5">
                    <form:select path="badges" cssClass="form-control">
                        <c:forEach items="${badges}" var="badge">
                            <form:option value="${badge}">${badge.origin.city}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="badge" cssClass="help-block"/>
                </div>
            </div>
            --%>

            <button class="btn btn-primary" type="submit">Create Trainer</button>
        </form:form>
    </jsp:attribute>
</t:template>
