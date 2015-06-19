<%@page import="org.hibernate.jpa.criteria.expression.function.UpperFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="column">
			<h4>${siteDetalhes.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>
				Dashboard » Associações » Gerente #${id} <br>
				<small style="font-size: .6em"><b>${fn:toUpperCase(bonita_login)}</b> - #Engine: ${bonita_idUsuario} | Sessão: ${bonita_idSessao}</small>
			</h5>

			<c:if test="${not empty msg}">
				<h6 id="mensagem">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>

			<h7><i class="fa fa-bars"></i> Gerentes</h7>
			<table class="u-full-width">
				<thead>
					<tr>
						<th class="alinhaCentro">#</th>
						<th>Login</th>
						<th>Nome completo</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="usuario" items="${usuarios}">
						<tr>
							<td class="alinhaCentro">${usuario.id}</td>
							<td>${usuario.userName}</td>
							<td>${usuario.firstName} ${usuario.lastName}</td>
							<td><i class="fa fa-user-times"></i></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="../template/footer.jsp"></jsp:include>