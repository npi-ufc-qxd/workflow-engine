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
			<h5>Dashboard » Processo <br><small style="font-size: .6em"><b>${fn:toUpperCase(bonita_login)}</b> - #Engine: ${bonita_idUsuario} | Sessão: ${bonita_idSessao}</small></h5>

			<c:if test="${not empty msg}">
				<h6 id="mensagem" class="blue">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>

			<div class="row">
				<div class="twelve columns">
					<div class="nine columns">
						<h7><i class="fa fa-bars"></i> Detalhes do Processo</h7>
						
						<h5><b>#ID: ${id}</b> | ${titulo}</h5>
						<p>
							Ator inicializador: <b>${atorInicializador.name}</b> (# ${atorInicializador.id})
							<br>
							IDProcessDefinition: ${IDdefinicao}
						</p>
					</div>
					
					<div class="three columns" style="text-align: right">
						<a class="button" href="<c:url value="/dashboard/processo/habilita/"></c:url>${id}/${IDdefinicao}">Habilitar Processo</a>
						<a class="button" href="inicializa/${processo.id}">Inicializar Processo</a>
					</div>
				
					<div class="u-cf"></div>
					
					<br>
					<h7><i class="fa fa-exchange"></i> Instâncias <small>(Atividades a serem executadas)</small></h7>
					<table class="u-full-width">
						<thead>
							<tr>
								<th>#</th>
								<th>Nome/Descrição</th>
								<th>Status</th>
								<th>Operacional</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="instancia" items="${instancias}">
								<tr>
									<td>
										${instancia.processDefinitionId}<br>
										<small>ParentID: ${instancia.parentContainerId} - RootID: ${instancia.rootContainerId}</small>
									</td>
									<td>
										${instancia.name} <a href="#" title="Descrição: (${instancia.flownodeDefinitionId}) ${instancia.description}"><i class="fa fa-question-circle"></i></a><br>
										<small>${instancia.displayDescription}</small>
									</td>
									<td>${instancia.state}</td>
									<td>${instancia.stateCategory}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<br>
					<div class="row">
						<div class="five columns">
							<h7><i class="fa fa-users"></i> Atores Envolvidos</h7>
							<table class="u-full-width">
								<thead>
									<tr>
										<th>#</th>
										<th>Username</th>
										<th>Ação</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ator" items="${atores}">
										<tr>
											<td>
												${ator.id}<br>
											</td>
											<td>
												${ator.name}
											</td>
											<td><a href="/WE_BonitaAPI/dashboard/membrosPorGrupo/2" title="Explorar informação"><i class="fa fa-street-view"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						<div class="seven columns">
							<h7><i class="fa fa-fast-forward"></i> Operações <small>(Lista de operações disponíveis)</small></h7>
							<table class="u-full-width">
								<thead>
									<tr>
										<th>#</th>
										<th>Operação</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="instancia" items="${instancias}">
										<tr>
											<td>
												${instancia.processDefinitionId}<br>
											</td>
											<td>
												${instancia.name}
											</td>
											<td>${instancia.state}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
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
<jsp:include page="../template/footer.jsp"></jsp:include>