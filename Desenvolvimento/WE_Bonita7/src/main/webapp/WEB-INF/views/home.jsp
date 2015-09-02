<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="template/header.jsp"></jsp:include>

<div class="container" style="padding-top: 1.6%;">
	<div class="row">
		<div class="columns">
			<h4>${site.DESCRICAO}</h4>
			<jsp:include page="template/menu.jsp"></jsp:include>
			<h5>Fundamentos do Workflow Engine</h5>
			
			<jsp:include page="template/message.jsp"></jsp:include>
			
			<p>
				Uma <b>workflow engine</b> ou <b>motor de fluxo de trabalho</b> �
				uma aplica��o de software que gerencia os processos de neg�cios. <br>Um
				motor de fluxo de trabalho gerencia e monitora o estado de
				atividades em um fluxo, tais como o processamento e aprova��o de
				pedidos gen�ricos, e determina qual a nova atividade para a
				transi��o para acordo com processos definidos (fluxos de trabalho).
				<br> <em>- Um motor de fluxo de trabalho facilita o fluxo
					de informa��es, tarefas e eventos.</em>
			</p>

			<h5>Workflow Engine possui tr�s principais fun��es:</h5>
			<ul>
				<li><b>Verifica��o do status atual:</b> Verifica se o comando �
					v�lido na execu��o de uma tarefa.</li>
				<li><b>Determinar a autoridade dos usu�rios:</b> Verifica se o
					usu�rio atual tem permiss�o para executar a tarefa.</li>
				<li><b>Executar script condicional:</b> Depois de passar pelas
					duas etapas anteriores, o motor come�a a avaliar o roteiro
					condicional no qual os processos s�o realizados, se a condi��o for
					verdadeira, o motor de fluxo de trabalho continua a executar a
					tarefa, e se a execu��o foi conclu�da com sucesso, ele retorna o
					sucesso, se n�o, ele relata o erro para provocar e reverter a
					mudan�a.</li>
			</ul>

			<h5>Hierarquia simulada</h5>
			<img src='<c:url value="/resources/images/hierarquia.jpg"></c:url>' alt="" />
		</div>
	</div>
</div>
<jsp:include page="template/scriptMessage.jsp"></jsp:include>
<jsp:include page="template/footer.jsp"></jsp:include>