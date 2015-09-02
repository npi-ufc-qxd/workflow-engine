<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src='<c:url value="/resources/js/jquery.timeago.js"></c:url>' type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
	$(function(){
		$('.timeago').timeago();
	});
</script>