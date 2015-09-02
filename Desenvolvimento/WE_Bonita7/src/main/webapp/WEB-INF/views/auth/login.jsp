<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${site.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>Login</h5>
			
			<c:if test="${not empty msg}">
				<h6 class="red">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>
			
			<c:set var="appContexto" value="${pageContext.request.contextPath}"></c:set>
			<form:form modelAttribute="BonitaUser" action="${appContexto}/auth/login">
				<div class="row">
					<div class="six columns">
						<label for="usuario">Usuário:</label>
						<form:input path="usuario" cssClass="u-full-width" autofocus="true" />
						<form:errors path="usuario" cssClass="erros"></form:errors>
					</div>
					<div class="six columns">
						<label for="senha">Senha:</label>
						<form:password path="senha" cssClass="u-full-width"/>
						<form:errors path="senha" cssClass="erros"></form:errors>
					</div>
				</div>

				<input class="button-primary" value="Entrar" type="submit" onclick="javascript:document.getElementById('progressoLogin').style.display='block';">
			</form:form>
			
			<div id="progressoLogin" class="eleven columns">
				<img src="<c:url value="/resources/images/loading.gif" />" alt="Carregando..." width="110" /> 
				<p>Aguarde... Processando requisição</p>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../template/footer.jsp"></jsp:include>