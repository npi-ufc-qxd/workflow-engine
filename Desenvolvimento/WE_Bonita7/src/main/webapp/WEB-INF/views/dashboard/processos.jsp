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
				Dashboard / Processos 
				<small style="font-size: .6em"><b>${usuario}</b></small>
			</h5>

			<jsp:include page="../template/message.jsp"></jsp:include>

			<div class="row">
				<div class="twelve columns">
					<h6><i class="fa fa-cogs"></i> Processos em Deploy</h6>
					<table class="u-full-width">
						<thead>
							<tr>
								<th>#PID / Data</th>
								<th>Título</th>
								<th>Status</th>
								<th>Deployed</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ap" items="${deployProcess}">
							<tr>
								<td>
									<fmt:formatDate type="both" value="${ap.deploymentDate}"/> <br>
									<small>#${ap.processId}</small>
								</td>
								<td>
									${ap.displayName}<c:if test="${empty ap.displayName}">${ap.name}</c:if>, <i>(versão: ${ap.version})</i><br>
									<small>${ap.displayDescription} <c:if test="${empty ap.displayDescription}">${ap.description}</c:if></small>
								</td>
								<td>${ap.activationState}</td>
								<td class="alinhaCentro"><a href="#" title="#ID User: ${ap.deployedBy}"><i class="fa fa-user"></i></a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
				
			<hr>
			
			<div class="row">
				<div class="six columns">
					<h6><i class="fa fa-folder-open-o"></i> Instâncias de Processos em aberto</h6>
					<table class="u-full-width">
						<thead>
							<tr>
								<th>#IID</th>
								<th>Título</th>
								<th>Inicializado</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="op" items="${openProcess}">
							<tr>
								<td>${op.id}</td>
								<td>
									${op.name} <br>
									<small>${op.state} por: <a href="#" title="#IDUser: ${op.startedBy}"><i class="fa fa-user"></i></a></small>
								</td>
								<td><time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${op.startDate}"/>"></time></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="six columns">
					<h6><i class="fa fa-folder-o"></i> Instâncias de Processos finalizados</h6>
					<table class="u-full-width">
						<thead>
							<tr>
								<th>#IID</th>
								<th>Título</th>
								<th>Finalizado</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ap" items="${archivedProcess}">
							<tr>
								<td>${ap.id}</td>
								<td>
									${ap.name} <br>
									<small>${ap.state} por: <a href="#" title="#IDUser: ${ap.startedBy}"><i class="fa fa-user"></i></a></small>
								</td>
								<td><time class="timeago" datetime="<fmt:formatDate pattern="yyyy-MM-dd H:m:s" value="${ap.startDate}"/>"></time></td>
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