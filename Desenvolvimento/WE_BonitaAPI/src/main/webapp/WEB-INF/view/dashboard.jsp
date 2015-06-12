<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="column">
			<h4>${siteDetalhes.DESCRICAO}</h4>
			<jsp:include page="template/menu.jsp"></jsp:include>
			<h5>Dashboard | ${fn:toUpperCase(sessao.login)}</h5>

			<c:if test="${not empty msg}">
				<h6 id="mensagem">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>

			<div class="row">
				<div class="seven columns">
					<h6>Processos</h6>
					
					<table class="u-full-width">
						<thead>
							<tr>
								<th># Processo</th>
								<th>Título</th>
								<th>Data</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>...</td>
								<td>...</td>
								<td>...</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="five columns">
					<h6>Usuários</h6>
					
						<table class="u-full-width">
						<thead>
							<tr>
								<th># Usuario</th>
								<th>Login</th>
								<th>Nome</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>...</td>
								<td>...</td>
								<td>...</td>
							</tr>
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