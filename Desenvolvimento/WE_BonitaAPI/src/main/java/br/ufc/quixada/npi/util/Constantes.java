package br.ufc.quixada.npi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que concentra as constantes utilizadas ao longo da aplicação.
 * 
 * @author 00056726198
 */
public class Constantes {

	/**
	 * Propriedades que definem as constantes utilizadas para detalhar o sistema.
	 */
	public static final Map<String, String> SITE_DETALHES	= new HashMap<String, String>() {
		{
			put("TITULO", "Workflow Engine & BPM");
			put("DESCRICAO", "App Workflow Engine & BPM");
			put("AUTOR", "Thiago Pereira - @kamihouse");
			put("ENGINE", "Bonita BPM Community 6.5");
		}
	};

	
	/**
	 * Bonita BPM.
	 * Configuração inicial da API Bonita para versão Community edition.
	 */
	public static final String BONITA_HOME_KEY				= "bonita.home";
	public static final String BONITA_HOME_PATH				= "/home/thiago.rosa/Documentos/Workflow Engine/Suite Bonita BPM/BonitaBPMCommunity-6.5.1/workspace/bonita";
	public static final String BONITA_URI					= "http://localhost:8081/bonita/";
	public static final String BONITA_URL					= "localhost";
	public static final int BONITA_PORT						= 8081;
	
	public static final String BONITA_USERNAME				= "thiago";
	public static final String BONITA_PASSWORD				= "bpm";
	public static final String TECHNICAL_USER_NAME			= "install";
	public static final String TECHNICAL_PASSWORD			= "install";
	public static final String ACTOR_NAME					= "MyActor";
	public static final int PAGE_SIZE						= 5;
	
	
	/**
	 * Propriedades para setar a acessar os itens que persistem a sessão do usuário.
	 */
	public static final  Map<String, String> ITENS_SESSAO	= new HashMap<String, String>(){
		{
			put("ID_SESSAO", "bonita_idSessao");
			put("ID_USUARIO", "bonita_idUsuario");
			put("LOGIN", "bonita_login");
			put("SENHA", "bonita_senha");
			put("BONITA_API", "bonita_bonitaAPI");
			put("BONITA_SESSION", "bonita_bonitaSession");
		}
	};
}
