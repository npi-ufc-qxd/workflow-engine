<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${siteDetalhes.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			
			<c:if test="${not empty msg}">
				<h6 class="red">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>
			
			<c:set var="appContexto" value="${pageContext.request.contextPath}"></c:set>
			<sf:form modelAttribute="BonitaUser" action="${appContexto}/auth/login">
				<div class="row">
					<div class="six columns">
						<label for="usuario">Usuário:</label>
						<sf:input path="usuario" cssClass="u-full-width" />
						<sf:errors path="usuario" cssClass="erros"></sf:errors>
					</div>
					<div class="six columns">
						<label for="senha">Senha:</label>
						<sf:password path="senha" cssClass="u-full-width"/>
						<sf:errors path="senha" cssClass="erros"></sf:errors>
					</div>
				</div>

				<input class="button-primary" value="Entrar" type="submit" onclick="javascript:document.getElementById('progressoLogin').style.display='block';">
			</sf:form>
			
			<div id="progressoLogin" class="eleven columns">
				<img src="<c:url value="/assets/image/loading.gif" />" alt="Carregando..." width="110" /> 
				<p>Aguarde... Processando requisição externamente.</p>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../template/footer.jsp"></jsp:include>