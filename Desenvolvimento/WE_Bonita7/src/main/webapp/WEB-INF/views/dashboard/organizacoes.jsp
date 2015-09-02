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
				Dashboard / Organizações <small style="font-size: .6em"><b>${usuario}</b></small>
			</h5>

			<jsp:include page="../template/message.jsp"></jsp:include>
			
			<div class="row">
				<div class="twelve columns">
					<h6><i class="fa fa-building-o"></i> Organizações <small>(deployed associations)</small></h6>
					<table class="u-full-width">
						<thead>
							<tr>
								<th class="alinhaCentro">#ID</th>
								<th>Descrição</th>
								<th class="alinhaCentro">Ação</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="org" items="${organizacoes}">
								<tr>
									<td class="alinhaCentro">${org.id}</td>
									<td>${org.description} <c:if test="${empty org.description}">${org.name}</c:if></td>
									<td class="alinhaCentro">
										<a href='<c:url value="organizacoes/membros/${org.id}"></c:url>' title="Exibir membros: ${org.description}"><i class="fa fa-users"></i></a>
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

<jsp:include page="../template/scriptMessage.jsp"></jsp:include>
<jsp:include page="../template/footer.jsp"></jsp:include>