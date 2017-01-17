
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template title="Trade pokemon">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/pokemon/tradepokemon"
                   modelAttribute="tradePokemon" cssClass="form-horizontal">
            <h2 align="center">Trade pokemon</h2>
            <p>You can trade pokemons between trainers.</p>
            <br>

            <div class="form-group">
                <form:label path="pokemon1Id" cssClass="col-sm-1 control-label">Pokemon1</form:label>
                    <div class="col-sm-5">
                    <form:select path="pokemon1Id" cssClass="form-control">
                        <c:forEach items="${pokemons}" var="p">
                                <form:option value="${p.id}">${p.name} (${p.trainer.firstName} ${p.trainer.lastName})</form:option>
                        </c:forEach>
                    </form:select>
                                
                    <form:errors path="pokemon1Id" cssClass="help-block"/>
                </div>
            </div>
            <br>

            <div class="form-group">
                <form:label path="pokemon2Id" cssClass="col-sm-1 control-label">Pokemon2</form:label>
                    <div class="col-sm-5">
                    <form:select path="pokemon2Id" cssClass="form-control">
                        <c:forEach items="${pokemons}" var="p">
                                <form:option value="${p.id}">${p.name} (${p.trainer.firstName} ${p.trainer.lastName})</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="pokemon2Id" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Make trade</button>
        </form:form>
    </jsp:attribute>
</t:template>
