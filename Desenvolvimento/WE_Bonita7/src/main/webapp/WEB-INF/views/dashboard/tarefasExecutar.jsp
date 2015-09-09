<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${site.DESCRICAO}</h4>
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<h5>
				Dashboard / Tarefas / Detalhar / Executar <small style="font-size: .6em"><b>${usuario}</b></small>
			</h5>

			<jsp:include page="../template/message.jsp"></jsp:include>

			<h6>
				<i class="fa fa-book"></i> ${detalhes.displayName}
				<c:if test="${empty detalhes.displayName}">${detalhes.name}</c:if>
			</h6>
			<p>
				${detalhes.displayDescription}<c:if test="${empty detalhes.displayDescription}">${detalhes.description}</c:if>
			</p>
			

			<form:form>
				<div class="row">
					<div class="six columns">
						<h6>- Activity Data Definitions (var local) -</h6>
						<hr>
						
						<c:forEach var="i" items="${add}">
							<c:choose>
								<c:when test="${fn:contains(i.className, 'Boolean')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input type="checkbox" name="${i.name}" value="" />
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'String')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width" name="${i.name}" type="text">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'Integer')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width" name="${i.name}" type="number">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'Long')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width" name="${i.name}" type="number">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'Date')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width campoData" name="${i.name}" type="text">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'List')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<select class="u-full-width" name="${i.defaultValueExpression.name}">
											<c:forEach var="c" items="${i.defaultValueExpression.content}">
												<option value="${fn:replace(c, '"', '')}">${fn:replace(c, '"', '')}</option>
											</c:forEach>
										</select>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</div>
					
					<div class="six columns">
						<h6>- Process Data Definitions -</h6>
						<hr>
						
						<c:forEach var="i" items="${pdd}">
							<c:choose>
								<c:when test="${fn:contains(i.className, 'Boolean')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input type="checkbox" name="${i.name}" value="" />
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'String')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width" name="${i.name}" type="text">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'Integer')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width" name="${i.name}" type="number">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'Long')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width" name="${i.name}" type="number">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'Date')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<input class="u-full-width campoData" name="${i.name}" type="text">
									</div>
								</c:when>
								<c:when test="${fn:contains(i.className, 'List')}">
									<div class="twelve columns">
										<label for="${i.name}">${i.name}</label>
										<select class="u-full-width" name="${i.defaultValueExpression.name}">
											<c:forEach var="c" items="${i.defaultValueExpression.content}">
												<option value="${fn:replace(c, '"', '')}">${fn:replace(c, '"', '')}</option>
											</c:forEach>
										</select>
									</div>
								</c:when>
							</c:choose>
						</c:forEach>
					</div>
					
					<hr class="u-cf">
					
					<input type="submit" value="Realizar esta tarefa" class="button-primary">
					<a class="button" href='<c:url value="/dashboard/tarefa/detalhar/${taskId}"></c:url>'>Cancelar</a>
				</div>
			</form:form>


		</div>
	</div>
</div>

<%-- 
Process Activities
<pre><code>
${fni}
</code></pre>

Process Design
<pre><code>
${pd}
</code></pre>

Activity Data Definitions
<pre><code>
${add}
</code></pre> --%>

<jsp:include page="../template/scriptDatePicker.jsp"></jsp:include>
<jsp:include page="../template/scriptTimeAgo.jsp"></jsp:include>
<jsp:include page="../template/scriptMessage.jsp"></jsp:include>
<jsp:include page="../template/footer.jsp"></jsp:include>