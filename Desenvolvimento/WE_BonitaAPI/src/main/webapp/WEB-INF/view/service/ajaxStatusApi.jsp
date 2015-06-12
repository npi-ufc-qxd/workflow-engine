<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${siteDetalhes.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>API & Engine Status</h5>

			<div class="statusEngine">
				<div class="row">
					<div class="eight columns statusEnginePadding">API & Engine ${siteDetalhes.ENGINE} - Status</div>
					<div id="status" class="four columns statusEnginePadding" style="text-align: right;"><b>atualizando...</b></div>
				</div>
			</div>
		</div>
	</div>
</div>

<c:set var="appContexto" value="${pageContext.request.contextPath}"></c:set>
<script>
	$(function(){		
		function atualizaStatusApi(){
			$.get("${appContexto}/bonitaService/statusEngine", function(data, status){				
				if(data == "true"){
					$("#status > b").text("Operacional").removeClass("red").addClass("green");
				} else {
					$("#status > b").text("Fora de Operação").removeClass("green").addClass("red");
				}
			});
		}
		setInterval(function(){atualizaStatusApi()}, 3000);
	});
</script>
<jsp:include page="../template/footer.jsp"></jsp:include>