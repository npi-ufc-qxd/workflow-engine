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
				Uma <b>workflow engine</b> ou <b>motor de fluxo de trabalho</b> é
				uma aplicação de software que gerencia os processos de negócios. <br>Um
				motor de fluxo de trabalho gerencia e monitora o estado de
				atividades em um fluxo, tais como o processamento e aprovação de
				pedidos genéricos, e determina qual a nova atividade para a
				transição para acordo com processos definidos (fluxos de trabalho).
				<br> <em>- Um motor de fluxo de trabalho facilita o fluxo
					de informações, tarefas e eventos.</em>
			</p>

			<h5>Workflow Engine possui três principais funções:</h5>
			<ul>
				<li><b>Verificação do status atual:</b> Verifica se o comando é
					válido na execução de uma tarefa.</li>
				<li><b>Determinar a autoridade dos usuários:</b> Verifica se o
					usuário atual tem permissão para executar a tarefa.</li>
				<li><b>Executar script condicional:</b> Depois de passar pelas
					duas etapas anteriores, o motor começa a avaliar o roteiro
					condicional no qual os processos são realizados, se a condição for
					verdadeira, o motor de fluxo de trabalho continua a executar a
					tarefa, e se a execução foi concluída com sucesso, ele retorna o
					sucesso, se não, ele relata o erro para provocar e reverter a
					mudança.</li>
			</ul>

			<h5>Hierarquia simulada</h5>
			<img src='<c:url value="/resources/images/hierarquia.jpg"></c:url>' alt="" />
		</div>
	</div>
</div>
<jsp:include page="template/scriptMessage.jsp"></jsp:include>
<jsp:include page="template/footer.jsp"></jsp:include>