<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${site.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>Dashboard <small style="font-size: .6em"><b>${usuario}</b></small></h5>
			
			<jsp:include page="../template/message.jsp"></jsp:include>
			
			<p>
				Utilize as opções de acesso informadas abaixo:
			</p>

			<div class="row alinhaCentro">
				<div class="four columns" >
					<a href='<c:url value="tarefas"></c:url>'><img class="value-img" src='<c:url value="/resources/images/dashboard/watch.svg"></c:url>'></a>
					<br>
					Tarefas
				</div>
				<div class="four columns" >
					<a href='<c:url value="processos"></c:url>'><img class="value-img" src='<c:url value="/resources/images/dashboard/pens.svg"></c:url>'></a>
					<br>
					Processos
				</div>
				<div class="four columns" >
					<a href='<c:url value="organizacoes"></c:url>'><img class="value-img" src='<c:url value="/resources/images/dashboard/feather.svg"></c:url>'></a>
					<br>
					Organizações
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../template/scriptMessage.jsp"></jsp:include>
<jsp:include page="../template/footer.jsp"></jsp:include>