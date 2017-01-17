
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="body">
        <h2 class="tiles-header">Choose the section to manage</h2>
        </br>
        <div class="row tiles">
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/badge/index" class="btn btn-primary btn-lg">Badges</a>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/pokemon/list" class="btn btn-primary btn-lg">Pokemons</a>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/trainer/list" class="btn btn-primary btn-lg">Trainers</a>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/stadium/list" class="btn btn-primary btn-lg">Stadiums</a>
            </div>            
        </div>
    </jsp:attribute>
</t:template>