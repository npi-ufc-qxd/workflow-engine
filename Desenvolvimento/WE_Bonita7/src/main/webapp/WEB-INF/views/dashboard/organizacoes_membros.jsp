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
				Dashboard / Organizações / Membros <small style="font-size: .6em"><b>${usuario}</b></small>
			</h5>

			<jsp:include page="../template/message.jsp"></jsp:include>
			
			<div class="row">
				<div class="twelve columns">
					<h6><i class="fa fa-users"></i> Membros da Organização</h6>
					<table class="u-full-width">
						<thead>
							<tr>
								<th class="alinhaCentro">#ID</th>
								<th>Login</th>
								<th>Nome completo</th>
								<th class="alinhaCentro">Gerente</th>
								<th class="alinhaCentro">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="usuario" items="${users}">
								<tr>
									<td class="alinhaCentro">${usuario.id}</td>
									<td>${usuario.userName}</td>
									<td>${usuario.firstName} ${usuario.lastName}</td>
									<td class="alinhaCentro">
										<c:if test="${usuario.managerUserId != 0}">
											<a href='<c:url value="#${usuario.managerUserId}"></c:url>' title="Exiber gerente"><i class="fa fa-male"></i></a>
										</c:if>
										<c:if test="${usuario.managerUserId == 0}">
											<i class="fa fa-male"></i>
										</c:if>
									</td>
									<td class="alinhaCentro"><i class="fa fa-user-times"></i></td>
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