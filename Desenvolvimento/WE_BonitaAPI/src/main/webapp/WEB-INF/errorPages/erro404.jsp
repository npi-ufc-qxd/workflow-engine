<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<title>Erro 404</title>
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
				<h4>Erro 404</h4>
				<p>
					<b>Arquivo ou diretório não encontrado.</b>
					<br> O erro 404 é
					um código de resposta HTTP que indica que o cliente pôde se comunicar
					com o servidor, ou o servidor não pôde encontrar o que foi
					pedido, ou foi configurado para não cumprir o pedido e não revelar
					a razão, ou a página não existe mais.
				</p>
			</div>
		</div>
	</div>
</body>
</html>