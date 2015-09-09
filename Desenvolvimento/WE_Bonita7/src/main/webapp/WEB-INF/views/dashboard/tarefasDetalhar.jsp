<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${site.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>
				Dashboard / Tarefas / Detalhes <small style="font-size: .6em"><b>${usuario}</b></small>
			</h5>

			<jsp:include page="../template/message.jsp"></jsp:include>

			<div class="row">
				<div class="nine columns">
					<h6>
						<i class="fa fa-book"></i> ${detalhes.displayName}
						<c:if test="${empty detalhes.displayName}">${detalhes.name}</c:if>
					</h6>
					<p>
						${detalhes.displayDescription}
						<c:if test="${empty detalhes.displayDescription}">${detalhes.description}</c:if>
					</p>
				</div>
					<div class="three columns" style="text-align: right">
					<a class="button button-primary" href='<c:url value="/dashboard/tarefa/executar/${detalhes.id}"></c:url>'>Executar</a>
				</div>
			</div>

			<div class="row">
				<div class="three columns">
					<h6>Instância de processo:</h6>
					<h6>Processo:</h6>
					<h6>Versão do processo:</h6>
					<h6>Status:</h6>
					<h6>Prioridade:</h6>
					<h6>Atribuição:</h6>
					<br>
					<hr>
					<h6>Deadline:</h6>
					<h6>Atualização:</h6>
				</div>
				<div class="nine columns">
					<p>
						${detalhes.parentProcessInstanceId} - <small>(Processo: ${detalhes.processDefinitionId}) - (Fluxo: ${detalhes.flownodeDefinitionId})</small><br>
						<a href='<c:url value="/dashboard/processos"></c:url>'>${processo.name}</a><br>
						${processo.version}<br> ${detalhes.state}<br>
						${detalhes.priority}<br>
						<c:choose>
							<c:when test="${detalhes.assigneeId eq 0}">
								<b>Não atribuído</b>
							</c:when>
							<c:otherwise>
								<b>${atribuido.firstName} ${atribuido.lastName}</b>
							</c:otherwise>
						</c:choose>
						 - <small>(<i>Inicializado por: #${detalhes.actorId}</i>)</small>
					</p>
					<hr>
					<p>
						<time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${detalhes.expectedEndDate}"/>"></time><br>
						<fmt:formatDate type="both" value="${detalhes.lastUpdateDate}"/>
					</p>
				</div>
			</div>
			
			<div class="row">
				<div class="three columns">
					<table class="u-full-width">
						<thead>
							<tr>
								<th><i class="fa fa-users"></i> Atores envolvidos</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ator" items="${atores}" >
							<tr>
								<td>${ator.name}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="nine columns">
					<table class="u-full-width">
						<thead>
							<tr>
								<th><i class="fa fa-comments-o"></i> Comentários</th>
								<th>Data</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="comment" items="${comentarios}" >
							<tr>
								<td>${comment.content}</td>
								<td>${comment.postDate}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../template/scriptTimeAgo.jsp"></jsp:include>
<jsp:include page="../template/scriptMessage.jsp"></jsp:include>
<jsp:include page="../template/footer.jsp"></jsp:include>