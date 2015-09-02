package br.ufc.quixada.npi.we.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Constantes para utilizar no contexto da aplicação.
 * 
 * @author Thiago Pereira Rosa - thiagor@engineer.com
 */
public class Constantes {

	/**
	 * Constante com informações gerais do site.
	 */
	public static final Map<String, String> SITE = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;

		{
			put("TITULO", "Workflow Engine & BPM");
			put("DESCRICAO", "App Workflow Engine & BPM");
			put("AUTOR", "Thiago Pereira - @kamihouse");
			put("ENGINE", "Bonita BPM Community 7.0");
		}
	};
	
	
	public static final Map<String, String> BONITA = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;

		{
			put("URL", "localhost");
			put("PORT", "8081");
		}
	};
	
	public static final String FLASH_MESSAGE = "msg";
	
	public static final Integer SEARCHBUILDER_START_INDEX = 0;
	public static final Integer SEARCHBUILDER_PAGE_SIZE = 9999;
}
