package br.ufc.quixada.npi.we.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.text.WordUtils;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.actor.ActorCriterion;
import org.bonitasoft.engine.bpm.flownode.ActivityInstanceCriterion;
import org.bonitasoft.engine.bpm.flownode.ActivityInstanceNotFoundException;
import org.bonitasoft.engine.bpm.flownode.ArchivedHumanTaskInstance;
import org.bonitasoft.engine.bpm.flownode.ArchivedHumanTaskInstanceSearchDescriptor;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ArchivedProcessInstancesSearchDescriptor;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoSearchDescriptor;
import org.bonitasoft.engine.bpm.process.ProcessInstanceSearchDescriptor;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.UpdateException;
import org.bonitasoft.engine.identity.UserSearchDescriptor;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.we.util.Constantes;

/**
 * Workflow engine. Dashboard controller.
 * 
 * @author Thiago Pereira Rosa - thiagor@engineer.com
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@RequestMapping("/")
	public String home(HttpSession httpSession, RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));

		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));
			return "dashboard/home";
		} else {
			redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Efetue login para acessar o Dashboard.");
			return "redirect:/auth/login";
		}
	}

	@RequestMapping("/tarefas")
	public String tarefas(HttpSession httpSession, Model model) {
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));

		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));

			ProcessAPI processAPI = SessionController.getApiProcess(httpSession);

			// Listando tarefas pendentes
			List<HumanTaskInstance> tarefasPendentes = null;
			
			//tarefasPendentes = processAPI. getPossibleUsersOfHumanTask(processDefinitionId, humanTaskName, startIndex, maxResults);
			tarefasPendentes = processAPI.getPendingHumanTaskInstances(
					SessionController.getApiSession(httpSession).getUserId(),
					Constantes.SEARCHBUILDER_START_INDEX,
					Constantes.SEARCHBUILDER_PAGE_SIZE,
					ActivityInstanceCriterion.LAST_UPDATE_ASC);
			
			model.addAttribute("tarefasPendentes", tarefasPendentes);
			
			
			// Recuperando informações do processos + Construtor de Busca
			SearchOptionsBuilder optionsBuilder = new SearchOptionsBuilder(Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE);
			optionsBuilder.sort(ProcessDeploymentInfoSearchDescriptor.NAME, Order.ASC);
			try {
				model.addAttribute("processos", processAPI.searchProcessDeploymentInfos(optionsBuilder.done()).getResult());
			} catch (SearchException e) {
				System.out.println(e);
			}
						
			
			// Listando tarefas atribuídas ao usuário
			List<HumanTaskInstance> tarefasAtribuidas = null;
			tarefasAtribuidas = processAPI.getAssignedHumanTaskInstances(
					SessionController.getApiSession(httpSession).getUserId(),
					Constantes.SEARCHBUILDER_START_INDEX,
					Constantes.SEARCHBUILDER_PAGE_SIZE,
					ActivityInstanceCriterion.DEFAULT);
			
			model.addAttribute("tarefasAtribuidas", tarefasAtribuidas);
			
			
			// Listando tarefas realizadas pelo usuário
			List<ArchivedHumanTaskInstance> tarefasRealizadas = new ArrayList<ArchivedHumanTaskInstance>();
			optionsBuilder.sort(ArchivedHumanTaskInstanceSearchDescriptor.NAME, Order.ASC);
			try {
				tarefasRealizadas = processAPI.searchArchivedHumanTasksManagedBy(
						SessionController.getApiSession(httpSession).getUserId(),
						optionsBuilder.done()).getResult();
				
			} catch (SearchException e) {
				System.out.println(e);
			}
			
			model.addAttribute("tarefasRealizadas", tarefasRealizadas);
			
			logger.info("UserId: " + SessionController.getApiSession(httpSession).getUserId() + " - Name: " + SessionController.getApiSession(httpSession).getUserName());
			
			return "dashboard/tarefas";
		} else {
			return "redirect:/auth/login";
		}
	}

	@RequestMapping("/processos")
	public String processos(HttpSession httpSession, Model model) {
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));

		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));

			// Configurando construtor de busca.
			SearchOptionsBuilder optionsBuilder = new SearchOptionsBuilder(Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE);

			ProcessAPI processAPI = SessionController.getApiProcess(httpSession);

			try {
				// Processos em aberto
				optionsBuilder.sort(ProcessInstanceSearchDescriptor.ID, Order.ASC);
				model.addAttribute("openProcess", processAPI.searchProcessInstances(optionsBuilder.done()).getResult());

				// Processos arquivados
				optionsBuilder.sort(ArchivedProcessInstancesSearchDescriptor.ID, Order.ASC);
				model.addAttribute("archivedProcess", processAPI.searchArchivedProcessInstances(optionsBuilder.done()).getResult());
				
				// Processos em deploy
				optionsBuilder.sort(ProcessDeploymentInfoSearchDescriptor.DEPLOYMENT_DATE, Order.DESC);
				model.addAttribute("deployProcess", processAPI.searchProcessDeploymentInfos(optionsBuilder.done()).getResult());
				
			} catch (SearchException e) {
				System.out.println(e);
				e.printStackTrace();
				
			}

			return "dashboard/processos";
		} else {
			return "redirect:/auth/login";
		}
	}
	
	
	@RequestMapping("/tarefa/pegar/{taskId}")
	public String pegarTarefa(HttpSession httpSession, RedirectAttributes redirectAttributes ,@PathVariable("taskId") Long taskid, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		if (autenticacaoAtiva(httpSession)) {
			ProcessAPI processAPI = SessionController.getApiProcess(httpSession);
			
			try {
				processAPI.assignUserTask(taskid, SessionController.getApiSession(httpSession).getUserId());
			} catch (UpdateException e) {
				System.out.println(e);
			}
			
			redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Tarefa atribuída com sucesso.");
			return "redirect:/dashboard/tarefas";
		} else {
			return "redirect:/auth/login";
		}
	}
	
	
	@RequestMapping("/tarefa/executar/{taskId}")
	public String executarTarefa(HttpSession httpSession, RedirectAttributes redirectAttributes ,@PathVariable("taskId") Long taskid, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));
			
			ProcessAPI processAPI = SessionController.getApiProcess(httpSession);
			
			try {
				HumanTaskInstance ht = processAPI.getHumanTaskInstance(taskid);
				ProcessDefinition pd = processAPI.getProcessDefinition(ht.getProcessDefinitionId());
				
				model.addAttribute("detalhes", ht);
				
				// Process Data Definitions
				model.addAttribute("pdd", processAPI.getProcessDataDefinitions(ht.getProcessDefinitionId(), Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE));
				
				// Process Data Instances
				model.addAttribute("pdi", processAPI.getProcessDataInstances(ht.getParentProcessInstanceId(), Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE));
				
				// Process Activities
				model.addAttribute("pa", processAPI.getActivities(ht.getParentProcessInstanceId(), Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE));
				
				// Process Design
				model.addAttribute("pd", processAPI.getDesignProcessDefinition(ht.getProcessDefinitionId()));
				
				// Parameter Instances
				//model.addAttribute("pi", processAPI.getParameterInstances(ht.getProcessDefinitionId(), Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE, ParameterCriterion.NAME_ASC));
				
				// Transient Data Instances
				//model.addAttribute("tdi", processAPI.getActivityTransientDataInstances(taskid, Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE));
				
				
				
				
				// Executando a tarefa
				//processAPI.executeFlowNode(taskid);
				
			} catch (/*FlowNodeExecutionException |*/ActivityInstanceNotFoundException | ProcessDefinitionNotFoundException e) {
				System.out.println(e);
			}
			
			redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Tarefa executada com sucesso.");
			return "/dashboard/tarefasExecutar";
		} else {
			return "redirect:/auth/login";
		}
	}
	
	
	@RequestMapping("/tarefa/detalhar/{taskId}")
	public String detalharTarefa(HttpSession httpSession, RedirectAttributes redirectAttributes ,@PathVariable("taskId") Long taskid, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));
			
			ProcessAPI processAPI = SessionController.getApiProcess(httpSession);
			
			try {
				HumanTaskInstance ht = processAPI.getHumanTaskInstance(taskid);
				ProcessDefinition pd = processAPI.getProcessDefinition(ht.getProcessDefinitionId());
				
				model.addAttribute("detalhes", ht);
				model.addAttribute("processo", pd);
				model.addAttribute("comentarios", processAPI.getComments(ht.getParentProcessInstanceId()));
				model.addAttribute("atores", processAPI.getActors(ht.getProcessDefinitionId(), Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE, ActorCriterion.NAME_ASC));
								
			} catch (ActivityInstanceNotFoundException | ProcessDefinitionNotFoundException e) {
				System.out.println(e);
			}
			
			redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Tarefa atribuída com sucesso.");
			return "/dashboard/tarefasDetalhar";
		} else {
			return "redirect:/auth/login";
		}
	}
	
	
	@RequestMapping("/organizacoes")
	public String organizacoes(HttpSession httpSession, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));
			
			IdentityAPI identityAPI = SessionController.getApiIdentity(httpSession);
			
			SearchOptionsBuilder builder = new SearchOptionsBuilder(Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE);
			
			try {
				model.addAttribute("organizacoes", identityAPI.searchGroups(builder.done()).getResult());
			} catch (SearchException e) {
				System.out.println(e);
			}
			
			return "/dashboard/organizacoes";
		} else {
			return "redirect:/auth/login";
		}
	}
	
	
	@RequestMapping("/organizacoes/membros/{id}")
	public String organizacoesMembros(HttpSession httpSession, @PathVariable("id") long idOrganizacao, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		if (autenticacaoAtiva(httpSession)) {
			model.addAttribute("usuario", WordUtils.capitalize(SessionController.getUserSession(httpSession).getUsuario()));
			
			IdentityAPI identityAPI = SessionController.getApiIdentity(httpSession);
			
			SearchOptionsBuilder builder = new SearchOptionsBuilder(Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE);
			builder.filter(UserSearchDescriptor.GROUP_ID, idOrganizacao);
			
			try {
				model.addAttribute("users", identityAPI.searchUsers(builder.done()).getResult());
			} catch (SearchException e) {
				System.out.println(e);
			}
			
			return "/dashboard/organizacoes_membros";
		} else {
			return "redirect:/auth/login";
		}
	}

	
	/**
	 * Checa autenticação para o dashboard.
	 * 
	 * @param httpSession
	 *            {@link HttpSession}
	 * @return {@link Boolean} true = sessão ativa, false = sessão inativa.
	 */
	private Boolean autenticacaoAtiva(HttpSession httpSession) {
		// logger.info("Verificando sessão do cliente no dashboard");
		return SessionController.statusSession(httpSession) ? true : false;
	}
}
