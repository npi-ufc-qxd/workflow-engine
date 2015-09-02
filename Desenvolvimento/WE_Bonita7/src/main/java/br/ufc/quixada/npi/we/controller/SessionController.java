package br.ufc.quixada.npi.we.controller;


import javax.servlet.http.HttpSession;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.session.APISession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import br.ufc.quixada.npi.we.model.BonitaUser;

/**
 * Session Controller. Controla sessão http do cliente no browser.
 * 
 * @author Thiago Pereira Rosa - thiagor@engineer.com
 */
@Controller
public class SessionController {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	/**
	 * Define userSession na sessão do usuário ativo.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @param bonitaUser {@link Object}
	 */
	public static void setUserSession(HttpSession httpSession, BonitaUser bonitaUser){
		httpSession.setAttribute("ID_SESSION", httpSession.getId());		
		httpSession.setAttribute("USER_SESSION", bonitaUser);
		
		logger.info("userSession definida!");
	}
	
	
	/**
	 * Recupera User Session
	 * @param httpSession {@link HttpSession}
	 * @return {@link BonitaUser}
	 */
	public static BonitaUser getUserSession(HttpSession httpSession){
		return (BonitaUser) httpSession.getAttribute("USER_SESSION");
	}
	
	
	/**
	 * Define apiSession na sessão do usuário ativo.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @param object {@link APISession}
	 */
	public static void setApiSession(HttpSession httpSession, Object object){
		httpSession.setAttribute("API_SESSION", object);
		
		logger.info("apiSession definida!");
	}
	
	
	/**
	 * Recupera APISession registrada na sessão do cliente no browser.
	 * 
	 * @param httpSession
	 * @return {@link APISession}
	 */
	public static APISession getApiSession(HttpSession httpSession){
		return (APISession) httpSession.getAttribute("API_SESSION");
	}
	
	
	/**
	 * Informa o status da sessão para cliente no browser.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @return {@link Boolean} true = sessão ativa, false = sessão inativa.
	 */
	public static Boolean statusSession(HttpSession httpSession){
		if(httpSession.getAttribute("ID_SESSION") != null){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Destroi a sessão do cliente no browser.
	 * 
	 * @param httpSession {@link HttpSession}
	 */
	protected static void destroySession(HttpSession httpSession){
		httpSession.removeAttribute("ID_SESSION");
		httpSession.removeAttribute("USER_SESSION");
		httpSession.removeAttribute("API_SESSION");
		httpSession.removeAttribute("PROCESS_SESSION");
		httpSession.removeAttribute("IDENTITY_SESSION");
	}
	
	
	/**
	 * Define ProcessAPI na sessão do usuário ativo.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @param object {@link ProcessAPI}
	 */
	public static void setApiProcess(HttpSession httpSession, Object object){
		httpSession.setAttribute("PROCESS_SESSION", object);
		
		logger.info("apiProcess definida!");
	}
	
	
	/**
	 * Recupera ProcessAPI registrada na sessão do cliente no browser.
	 * 
	 * @param httpSession
	 * @return {@link ProcessAPI}
	 */
	public static ProcessAPI getApiProcess(HttpSession httpSession){
		return (ProcessAPI) httpSession.getAttribute("PROCESS_SESSION");
	}
	
	
	/**
	 * Define {@link IdentityAPI} na sessão do usuário ativo.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @param object {@link ProcessAPI}
	 */
	public static void setApiIdentity(HttpSession httpSession, Object object){
		httpSession.setAttribute("IDENTITY_SESSION", object);
		
		logger.info("apiIdentity definida!");
	}
	
	
	/**
	 * Recupera {@link IdentityAPI} registrada na sessão do cliente no browser.
	 * 
	 * @param httpSession {@link HttpSession}
	 * @return {@link IdentityAPI}
	 */
	public static IdentityAPI getApiIdentity(HttpSession httpSession){
		return (IdentityAPI) httpSession.getAttribute("IDENTITY_SESSION");
	}
}
