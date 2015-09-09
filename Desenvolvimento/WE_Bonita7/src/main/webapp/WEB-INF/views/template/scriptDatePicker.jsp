<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src='<c:url value="/resources/js/jquery-ui.min.js"></c:url>' type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$('.campoData').datepicker();
	});
</script>