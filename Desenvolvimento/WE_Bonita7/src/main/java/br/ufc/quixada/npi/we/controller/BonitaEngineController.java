package br.ufc.quixada.npi.we.controller;

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
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.LogoutException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.springframework.stereotype.Controller;

import br.ufc.quixada.npi.we.util.Constantes;

/**
 * Bonita Engine Controller.
 * Controller que efetua comunicação direta com a Engine de BPM.
 * 
 * @author Thiago Pereira Rosa - thiagor@engineer.com
 */
@Controller
public class BonitaEngineController {

	private LoginAPI loginAPI;
	private APISession apiSession;
	private ProcessAPI processAPI;
	private IdentityAPI identityAPI;
	
	
	public BonitaEngineController() {
		// Setup bonita.home sys properties.
		System.setProperty("bonita.home", "/home/thiago.rosa/Documentos/NPI - Eclipse - Workspace/WE_Bonita7/src/main/resources/META-INF");
	}
	
	
	/**
	 * Consulta dados informados para efetuar login na Engine de BPM.
	 * 
	 * @param usuario {@link String}
	 * @param senha {@link String}
	 * @param httpSession {@link HttpSession}
	 * @return true = Usuário/Senha ok, false = Usuário/Senha erro {@link Boolean}
	 */
	public boolean login(String usuario, String senha, HttpSession httpSession){
		try {
			loginAPI = TenantAPIAccessor.getLoginAPI();
			
			apiSession = loginAPI.login(usuario, senha);
			
			// Registra apiSession na HttpSession
			SessionController.setApiSession(httpSession, apiSession);
			
			// Define identityAPI
			setIdentityApi(httpSession);
			
			// Define processAPI
			setProcessApi(httpSession);
			
			return true;
		} catch (BonitaHomeNotSetException | ServerAPIException | LoginException | UnknownAPITypeException e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	/**
	 * Efetua logoff na Engine de BPM.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @return {@link Boolean} true = logoff, false = erro ao efetuar logoff.
	 */
	public void logoff(HttpSession httpSession){
		try {			
			// Efetuando logoff na plataforma
			loginAPI.logout(SessionController.getApiSession(httpSession));
			
		} catch (SessionNotFoundException | LogoutException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Define {@link ProcessAPI} na sessão do cliente.
	 * @param httpSession {@link HttpSession}
	 */
	private void setProcessApi(HttpSession httpSession){
		try {
			processAPI = TenantAPIAccessor.getProcessAPI(SessionController.getApiSession(httpSession));
			
			// Registra processApi na HTTPSession
			SessionController.setApiProcess(httpSession, processAPI);
			
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Define {@link IdentityAPI} na sessão do cliente.
	 * @param httpSession {@link HttpSession}
	 */
	private void setIdentityApi(HttpSession httpSession){
		try {
			identityAPI = TenantAPIAccessor.getIdentityAPI(SessionController.getApiSession(httpSession));
			
			// Registra identityApi na HttpSession
			SessionController.setApiIdentity(httpSession, identityAPI);
			
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Lista os grupos (Associações) cadastradas na Engine.
	 * 
	 * @param session
	 * @return
	 */
	public List<Group> getListaGrupos(HttpSession httpSession) {
		try {
			identityAPI = TenantAPIAccessor.getIdentityAPI(SessionController.getApiSession(httpSession));
		} catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
			System.out.println(e);
		}

		SearchOptionsBuilder builder = new SearchOptionsBuilder(Constantes.SEARCHBUILDER_START_INDEX, Constantes.SEARCHBUILDER_PAGE_SIZE);
		SearchResult<Group> result = null;
		
		try {
			result = identityAPI.searchGroups(builder.done());
		} catch (SearchException e) {
			System.out.println(e);
		}

		return result.getResult();
	}
}
