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
				Dashboard / Tarefas 
				<small style="font-size: .6em"><b>${usuario}</b></small>
			</h5>

			<jsp:include page="../template/message.jsp"></jsp:include>

			<h6><i class="fa fa-calendar-check-o"></i> Minhas tarefas:</h6>
			<table class="u-full-width">
				<thead>
					<tr>
						<th>#PI-ID</th>
						<th>Descrição</th>
						<th>Status</th>
						<th class="alinhaCentro">+</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ta" items="${tarefasAtribuidas}">
					<tr>
						<td>
							<a href='<c:url value="processos"></c:url>'>${ta.parentProcessInstanceId}</a>
						</td>
						<td>
							${ta.displayName}<c:if test="${empty ta.displayName}">${ta.name}</c:if>
							<small>
							<c:forEach var="pdi" items="${processos}">
								<c:if test="${ta.processDefinitionId eq pdi.processId}">
									(${pdi.displayName}<c:if test="${empty pdi.displayName}">${pdi.name}</c:if> | v: ${pdi.version})
								</c:if>
							</c:forEach>
							</small>
							<br>
							<small>
								${ta.displayDescription}<c:if test="${empty ta.displayDescription}">${ta.description}</c:if>
							</small>
						</td>
						<td>${ta.stateCategory}</td>
						<td class="alinhaCentro">
							<a class="button button" href='<c:url value="tarefa/detalhar/${ta.id}"></c:url>'>Info</a>
							<a class="button button-primary" href='<c:url value="tarefa/executar/${ta.id}"></c:url>'>Exe</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<hr>
			
			<h6><i class="fa fa-list-alt"></i> Tarefas Realizadas:</h6>
			<table class="u-full-width">
				<thead>
					<tr>
						<th>#PI-ID</th>
						<th>Descrição</th>
						<th>Status</th>
						<th class="alinhaCentro">+</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tr" items="${tarefasRealizadas}">
					<tr>
						<td><a href='<c:url value="processos"></c:url>'>${tr.processInstanceId}</a></td>
						<td>
							${tr.displayName}<c:if test="${empty tr.displayName}">${tr.name}</c:if>
							<small>
							<c:forEach var="pdi" items="${processos}">
								<c:if test="${tr.processDefinitionId eq pdi.processId}">
									(${pdi.displayName}<c:if test="${empty pdi.displayName}">${pdi.name}</c:if> | v: ${pdi.version})
								</c:if>
							</c:forEach>
							</small>
							<br>
							<small>
								${tr.displayDescription}<c:if test="${empty tr.displayDescription}">${tr.description}</c:if>
							</small>
						</td>
						<td>
							<small>
							Iniciada: <time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${tr.expectedEndDate}"/>"></time><br>
							Atualizada: <time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${tr.lastUpdateDate}"/>"></time><br>
							Arquivada: <time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${tr.archiveDate}"/>"></time>
							</small>
						</td>
						<td class="alinhaCentro"><a class="button button" href='<c:url value="tarefa/detalhar/${tr.id}"></c:url>'>Info</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<hr>
			
			<h6><i class="fa fa-tasks"></i> Tarefas disponíveis:</h6>
			<table class="u-full-width">
				<thead>
					<tr>
						<th>#PI-ID</th>
						<th>Descrição</th>
						<th>Status</th>
						<th>Previsão</th>
						<th class="alinhaCentro">+</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tp" items="${tarefasPendentes}">
					<tr>
						<td>
							<a href='<c:url value="processos"></c:url>'>${tp.parentProcessInstanceId}</a>
						</td>
						<td>
							${tp.displayName}<c:if test="${empty tp.displayName}">${tp.name}</c:if>
							<small>
							<c:forEach var="pdi" items="${processos}">
								<c:if test="${tp.processDefinitionId eq pdi.processId}">
									(${pdi.displayName}<c:if test="${empty pdi.displayName}">${pdi.name}</c:if> | v: ${pdi.version})
								</c:if>
							</c:forEach>
							</small>
							<br>
							<small>
								${tp.displayDescription}<c:if test="${empty tp.displayDescription}">${tp.description}</c:if>
							</small>
						</td>
						<td>${tp.stateCategory}</td>
						<td><time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${tp.expectedEndDate}"/>"></time></td>
						<td class="alinhaCentro"><a class="button" href='<c:url value="tarefa/pegar/${tp.id}"></c:url>'>Pegar</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>

<jsp:include page="../template/scriptTimeAgo.jsp"></jsp:include>
<jsp:include page="../template/scriptMessage.jsp"></jsp:include>
<jsp:include page="../template/footer.jsp"></jsp:include>