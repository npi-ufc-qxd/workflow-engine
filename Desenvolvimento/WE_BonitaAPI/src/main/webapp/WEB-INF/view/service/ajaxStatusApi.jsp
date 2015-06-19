<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appContexto" value="${pageContext.request.contextPath}"></c:set>
<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${siteDetalhes.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>API & Engine Status</h5>
			<p>
				Este recurso permite a consulta em tempo real do <b>status de execução da Engine</b> da ${siteDetalhes.ENGINE}
				<br><small>1 - Operacional: A Engine está em execução e conectada a esta aplicação.</small>
				<br><small>2 - Fora de Operação: A Engine não está em execução, não possibilitando o consumo de seus recursos.</small>
			</p>

			<div class="statusEngine">
				<div class="row">
					<div class="twelve columns statusEnginePadding">
						API & Engine ${siteDetalhes.ENGINE} - Local
						<span id="status" style="float: right"><b>consultando engine...</b></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

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