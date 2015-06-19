package br.ufc.quixada.npi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.Group;
import org.bonitasoft.engine.identity.GroupSearchDescriptor;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserSearchDescriptor;
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
		System.setProperty(Constantes.BONITA_HOME_KEY, "/home/thiago.rosa/Documentos/Workflow Engine/Suite Bonita BPM/BonitaBPMCommunity-6.5.1/workspace/bonita");
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

	// public mantemLogin

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

	public void logout(LoginAPI loginAPI) {
		try {
			this.loginAPI.logout(this.apiSession);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
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

	public static List<Group> listaGrupos(HttpSession session) {

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
		//builder.filter(GroupSearchDescriptor.ID, 1);

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
}
