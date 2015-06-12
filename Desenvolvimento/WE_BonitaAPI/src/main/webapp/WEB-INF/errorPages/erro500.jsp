<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<title>Erro 500</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="<c:url value="/assets/css/normalize.css" />">
	<link rel="stylesheet" href="<c:url value="/assets/css/skeleton.css" />">
	<link rel="stylesheet" href="<c:url value="/assets/css/font.css" />">
	
	<link rel="icon" type="image/png" href="<c:url value="/assets/image/favicon.png" />">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="u-full-width" style="margin-top: 2%">
				<h4>Erro 500</h4>
				<p>
					<b>Erro interno de servidor.</b>
					<br> O erro 500 indica um
					erro do servidor ao processar a solicitação. Normalmente 
					pode estar relacionado à permissões dos arquivos ou diretórios do
					software ou script que o usuário tenta acessar e não foram
					configuradas no momento da programação/construção da
					aplicação.
				</p>
			</div>
		</div>
	</div>
</body>
</html>