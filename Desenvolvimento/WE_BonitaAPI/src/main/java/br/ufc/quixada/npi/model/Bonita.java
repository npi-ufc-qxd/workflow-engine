package br.ufc.quixada.npi.model;

import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.session.APISession;

/**
 * Classe responsável pela interação com a API da Bonita Community Edition.
 * <p>Os atributos e métodos podem diferir dependendo da versão da Engine utilizada.
 * 
 * @author 00056726198 - Thiago Pereira
 * @version 1.0
 */
public class Bonita {
	
	/**
	 * LoginAPI permite executar login utilizando a API da Bonita Community Edition.
	 * @see http://documentation.bonitasoft.com/getting-started-bonita-bpm-engine-apis
	 */
	private static LoginAPI loginAPI;
	
	/**
	 * APISession permite guardar informações da sessão após efetuado o login.
	 * @see http://documentation.bonitasoft.com/getting-started-bonita-bpm-engine-apis
	 */
	private static APISession apiSession;
	
	/**
	 * ProcessAPI permite utilizar o mecanismo de manipulação de processos.
	 * @see http://documentation.bonitasoft.com/getting-started-bonita-bpm-engine-apis
	 */
	private static ProcessAPI processAPI;
}
