package br.ufc.quixada.npi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.bpm.actor.ActorCriterion;
import org.bonitasoft.engine.bpm.actor.ActorInstance;
import org.bonitasoft.engine.bpm.actor.ActorNotFoundException;
import org.bonitasoft.engine.bpm.flownode.ActivityInstance;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoSearchDescriptor;
import org.bonitasoft.engine.bpm.process.ProcessEnablementException;
import org.bonitasoft.engine.bpm.process.ProcessExecutionException;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.Group;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserSearchDescriptor;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.springframework.stereotype.Controller;

import br.ufc.quixada.npi.model.BonitaUser;
import br.ufc.quixada.npi.util.Constantes;

@Controller
public class BonitaApi {

	private LoginAPI loginAPI;
	private APISession apiSession;
	private ProcessAPI processAPI;

	/**
	 * Definindo a propriedade inicial "bonita.home" Necessária para a
	 * utilização da API da Engine (Obrigatório)
	 * 
	 * Acompanhamento de Logs. Execute os comandos abaixo: cd
	 * /home/thiago.rosa/Documentos/Workflow Engine/Suite Bonita
	 * BPM/BonitaBPMCommunity-6.5.1/workspace/tomcat/logs multitail -c
	 * ../../.metadata/.bak_0.log -s 2 -c ../../.metadata/tomcat.log -c
	 * bonita.2015-06-10.log
	 * 
	 * @see http://documentation.bonitasoft.com/bonita-home
	 * @see http://documentation.bonitasoft.com/jvm-system-properties
	 */
	public BonitaApi() {
		System.setProperty(Constantes.BONITA_HOME_KEY, "/home/thiago.rosa/Documentos/NPI - Eclipse - Workspace/WE_BonitaAPI/src/main/resources/META-INF/");
		System.out.println("--> Bonita.home definido no construtor do controlador BonitaApi.");
	}

	
	/**
	 * Processa login diretamente na engine.
	 * <p>
	 * Ps: A Bonita Engine necessita obrigatoriamente de estar em execução onde
	 * a aplicação estiver executando.
	 * 
	 * @param bonitaUser
	 * @return <code>true</code> Login OK.
	 * @return <code>false</code> Usuário ou Senha inválidos.
	 */
	public boolean login(BonitaUser bonitaUser) {

		// Verificando se a propriedade bonita.home foi definida corretamente.
		this.checkBonitaHome();

		// Solicitando login.
		try {
			this.loginAPI = TenantAPIAccessor.getLoginAPI();
			this.apiSession = loginAPI.login(bonitaUser.getUsuario(), bonitaUser.getSenha());
			this.processAPI = TenantAPIAccessor.getProcessAPI(apiSession);

		} catch (Exception e) {
			System.out.println(e);
			// e.printStackTrace();
			return false;
		}

		return true;
	}

	
	/**
	 * Método que verifica se a propriedade bonita.home foi definida
	 * corretamente. Caso não esteja definida não é possível proceder com a
	 * autenticação na Engine.
	 */
	private void checkBonitaHome() {
		String bonitaHome = System.getProperty(Constantes.BONITA_HOME_KEY);

		if (bonitaHome == null) {
			throw new RuntimeException("The system property bonita.home is not set. Please, set this property with the path to the bonita home folder.\n "
					+ "You can get a bonita home from BonitaBPMCommunity-<bonita.engine.version>-deploy.zip or use the one generated under \n"
					+ "the folder target/home on this project by running the command 'mvn clean install'.");
		}
	}

	
	/**
	 * Finaliza a sessão na Engine.
	 * 
	 * @param loginAPI
	 * @param apiSession
	 */
	public void logout(LoginAPI loginAPI, APISession apiSession) {
		try {
			loginAPI.logout(apiSession);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public APISession getApiSession() {
		return apiSession;
	}

	public LoginAPI getLoginApi() {
		return loginAPI;
	}

	public ProcessAPI getProcessApi() {
		return processAPI;
	}

	
	/**
	 * Lista usuários pelo #ID do grupo.
	 * 
	 * @param session
	 * @param idGrupo
	 * @return
	 */
	public static List<User> listaUsuarios(HttpSession session, int idGrupo) {

		// Recuperando sessão da API
		IdentityAPI identityAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		// Recuperando Identidade da API utilizando o objeto APISession,
		// guardado na sessão do usuário.
		try {
			identityAPI = TenantAPIAccessor.getIdentityAPI(apiSession);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			System.out.println(e);
		}

		SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 100);
		builder.filter(UserSearchDescriptor.GROUP_ID, idGrupo);

		// Realizando busca pelos usuários
		SearchResult<User> uResult = null;
		try {
			// System.out.println(identityAPI.searchUsers(builder.done()).toString());
			uResult = identityAPI.searchUsers(builder.done());
		} catch (SearchException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return uResult.getResult();
	}

	
	/**
	 * Lista todas os grupos (Associações) cadastradas na Engine.
	 * 
	 * @param session
	 * @return
	 */
	public static List<Group> listaGrupos(HttpSession session) {

		// Recuperando sessão da API
		IdentityAPI identityAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		// Recuperando Identidade da API (Logando como Tenant) utilizando o
		// objeto APISession, guardado na sessão do usuário.
		try {
			identityAPI = TenantAPIAccessor.getIdentityAPI(apiSession);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			System.out.println(e);
		}

		SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 100);
		// builder.filter(GroupSearchDescriptor.ID, 1);

		// Realizando busca pelos usuários
		SearchResult<Group> result = null;
		try {
			// System.out.println(identityAPI.searchUsers(builder.done()).toString());
			result = identityAPI.searchGroups(builder.done());
		} catch (SearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return String.valueOf(uResult.getCount());
		return result.getResult();
	}

	
	/**
	 * Lista processos publicados (deployed) na Engine.
	 * 
	 * @param session
	 * @return
	 */
	public static List<ProcessDeploymentInfo> listaProcessos(HttpSession session) {
		// Recuperando sessão da API
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		// Recuperando Identidade da API (Logando como Tenant) utilizando o
		// objeto APISession, guardado na sessão do usuário.
		try {
			// Recuperando dados dos Processos
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			System.out.println(e);
		}

		SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 100);
		builder.sort(ProcessDeploymentInfoSearchDescriptor.DEPLOYMENT_DATE, Order.ASC);

		SearchResult<ProcessDeploymentInfo> result = null;

		try {
			result = processAPI.searchProcessDeploymentInfos(builder.done());
		} catch (SearchException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return result.getResult();
	}

	
	/**
	 * Habilitando um processo a partir de um ID informado.
	 * 
	 * @param session
	 * @param idProcesso
	 */
	public static boolean habilitaProcesso(HttpSession session, Long idProcesso, Long idDefinicaoProcesso) {
		Boolean resultado = false;
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		try {
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		// Habilitando o processo
		try {
			processAPI.enableProcess(idDefinicaoProcesso);
			resultado = true;
		} catch (ProcessDefinitionNotFoundException | ProcessEnablementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	
	/**
	 * Inicializa um processo a partir de um id informado.
	 * 
	 * @param session
	 * @param idProcesso
	 */
	public static boolean inicializaProcesso(HttpSession session, Long idProcesso) {
		Boolean resultado = false;
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));
		ProcessInstance processInstance = null;

		try {
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		try {
			processInstance = processAPI.startProcess(idProcesso);
			System.out.println("Processo #" + idProcesso + " inicializado.");
			resultado = true;
		} catch (ProcessDefinitionNotFoundException | ProcessActivationException | ProcessExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	/**
	 * Exibe detalhes de um processo a partir do ProcessID informado.
	 * @param session
	 * @param idProcesso
	 * @return List<ActivityInstance>
	 */
	public static List<ActivityInstance> exibeDetalhesProcesso(HttpSession session, Long idProcesso) {
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		try {
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);

		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		// System.out.println(processAPI.getActivities(idProcesso, 0,
		// 100).toString());
		// System.out.println(processAPI.getActors(getIDDefinicaoProcesso(session,
		// idProcesso), 0, 100, ActorCriterion.NAME_ASC));

		// System.out.println(processAPI.getCategories(0, 100,
		// CategoryCriterion.NAME_ASC));

		try {
			System.out.println(processAPI.getActorInitiator(getIDDefinicaoProcesso(session, idProcesso)));
		} catch (ActorNotFoundException | ProcessDefinitionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return processAPI.getActivities(idProcesso, 0, 100);
	}

	
	/**
	 * Recupera o ProcessDefinitionID sobre a instancia de um processo depois do
	 * deploy.
	 * 
	 * @param session
	 * @param idProcesso
	 * @return Long ProcessDefinitionID
	 */
	public static Long getIDDefinicaoProcesso(HttpSession session, Long idProcesso) {
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		try {
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);

		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return processAPI.getActivities(idProcesso, 0, 100).get(0).getProcessDefinitionId();
	}

	
	/**
	 * Lista os atores que fazem parte de um processo e consequentemente a
	 * partir do ID da definição do processo.
	 * 
	 * @param session
	 * @param idProcesso
	 * @return List<ActorInstance>
	 */
	public static List<ActorInstance> exibeAtoresEnvolvidos(HttpSession session, Long idProcesso) {
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		try {
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);

		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return processAPI.getActors(getIDDefinicaoProcesso(session, idProcesso), 0, 100, ActorCriterion.NAME_ASC);
	}

	
	/**
	 * Informa o ator inicializador de uma instancia de processo.
	 * 
	 * @param session
	 * @param idProcesso
	 * @return ActorInstance ator
	 */
	public static ActorInstance exibeAtorInicializador(HttpSession session, Long idProcesso) {
		ProcessAPI processAPI = null;
		APISession apiSession = (APISession) session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"));

		try {
			processAPI = TenantAPIAccessor.getProcessAPI(apiSession);
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		try {
			return processAPI.getActorInitiator(getIDDefinicaoProcesso(session, idProcesso));
		} catch (ActorNotFoundException | ProcessDefinitionNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return null;
	}
}
