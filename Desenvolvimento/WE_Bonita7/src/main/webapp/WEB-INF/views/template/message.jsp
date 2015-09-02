<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<c:if test="${not empty msg}">
				<h6 id="mensagem" class="blue">
					<b>Atenção:</b> ${msg}
				</h6>
			</c:if>