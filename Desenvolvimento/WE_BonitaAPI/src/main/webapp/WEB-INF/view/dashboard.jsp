<%@page import="org.hibernate.jpa.criteria.expression.function.UpperFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="column">
			<h4>${siteDetalhes.DESCRICAO}</h4>
			<jsp:include page="template/menu.jsp"></jsp:include>
			<h5>Dashboard <br><small style="font-size: .6em"><b>${fn:toUpperCase(bonita_login)}</b> - #Engine: ${bonita_idUsuario} | Sessão: ${bonita_idSessao}</small></h5>

			<c:if test="${not empty msg}">
				<h6 id="mensagem">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>

			<div class="row">
				<div class="seven columns">
					<h7><i class="fa fa-bars"></i> Processos</h7>
					
					<table class="u-full-width">
						<thead>
							<tr>
								<th>#Processo</th>
								<th>Título</th>
								<th>Data</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>

				<div class="five columns">
					<h7><i class="fa fa-bars"></i> Grupos</h7>
					<table class="u-full-width">
						<thead>
							<tr>
								<th class="alinhaCentro">#</th>
								<th>Descrição</th>
								<th>Ação</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="grupo" items="${grupos}">
								<tr>
									<td class="alinhaCentro">${grupo.id}</td>
									<td>${grupo.name}</td>
									<td class="alinhaCentro">
										<a href='<c:url value="/dashboard/membrosPorGrupo/${grupo.id}"></c:url>' title="Exibir membros: ${grupo.description}"><i class="fa fa-users"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</div>
			</div>

		</div>
	</div>
</div>

<script>
	$(function(){
		setInterval(function(){
			$('#mensagem').hide("fast");
		}, 2000);
	});
</script>
<jsp:include page="template/footer.jsp"></jsp:include>