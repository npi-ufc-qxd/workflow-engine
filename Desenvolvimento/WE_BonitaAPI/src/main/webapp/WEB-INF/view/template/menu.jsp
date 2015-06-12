<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<nav class="navbar">
				<div class="container">
					<ul class="navbar-list">
						<li class="navbar-item">
							<a class="navbar-link" href='<c:url value="/"></c:url>'>Home</a>
						</li>
						<li class="navbar-item">
							<a class="navbar-link" href="<c:url value="/dashboard"></c:url>">Dashboard</a>
						</li>
						<li class="navbar-item">
							<c:if test="${empty sessao}">
								<a class="navbar-link" href="<c:url value="/auth/login"></c:url>">Login</a>
							</c:if>
							<c:if test="${not empty sessao}">
								<a class="navbar-link" href="<c:url value="/auth/logout"></c:url>">Logout</a>
							</c:if>
						</li>
						
						<li class="navbar-item">
							<a class="navbar-link" href="<c:url value="/bonitaService/statusAPI"></c:url>">API Status</a>
						</li>
					</ul>
				</div>
			</nav>