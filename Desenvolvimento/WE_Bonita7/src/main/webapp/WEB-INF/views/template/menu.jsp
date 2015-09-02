<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<nav class="navbar">
				<div class="container">
					<ul class="navbar-list">
						<li class="navbar-item">
							<a class="navbar-link" href='<c:url value="/"></c:url>'>Home</a>
						</li>
						<li class="navbar-item">
							<a class="navbar-link" href="<c:url value="/dashboard/"></c:url>">Dashboard</a>
						</li>
						<li class="navbar-item">
							<c:if test="${not sessao}">
							<a class="navbar-link" href="<c:url value="/auth/login"></c:url>">Login</a>
							</c:if>
							
							<c:if test="${sessao}">
							<a class="navbar-link" href="<c:url value="/auth/logoff"></c:url>">Logout</a>
							</c:if>
						</li>
						<li class="navbar-item">
							<a class="navbar-link" href="<c:url value="/status"></c:url>">API Status</a>
						</li>
					</ul>
				</div>
			</nav>