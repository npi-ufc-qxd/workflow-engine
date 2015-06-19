<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<title>${siteDetalhes.TITULO}</title>
	<meta name="description" content="${siteDetalhes.DESCRICAO}">
	<meta name="author" content="${siteDetalhes.AUTOR}">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="<c:url value="/assets/css/normalize.css" />">
	<link rel="stylesheet" href="<c:url value="/assets/css/skeleton.css" />">
	<link rel="stylesheet" href="<c:url value="/assets/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/assets/css/font.css" />">
	<link rel="stylesheet" href="<c:url value="/assets/css/geral.css" />">
	
	<link rel="icon" type="image/png" href="<c:url value="/assets/image/favicon.png" />">
	
	<script src="<c:url value="/assets/js/jquery-2.1.4.min.js" />"></script>
</head>

<body>