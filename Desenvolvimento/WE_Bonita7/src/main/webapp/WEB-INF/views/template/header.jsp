<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<title>${site.TITULO}</title>
	<meta name="description" content="${site.DESCRICAO}">
	<meta name="author" content="${site.AUTOR}">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="<c:url value="/resources/css/normalize.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/skeleton.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/font.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/geral.css" />">
	
	<link rel="icon" type="image/png" href="<c:url value="/resources/images/favicon.png" />">
	
	<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
</head>

<body>