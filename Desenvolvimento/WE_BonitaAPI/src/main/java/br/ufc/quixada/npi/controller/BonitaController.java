package br.ufc.quixada.npi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.bonitasoft.engine.exception.BonitaException;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import br.ufc.quixada.npi.util.Constantes;

@Controller
@RequestMapping("/bonita")
public class BonitaController {
	/**
	 * Propriedade de logs.
	 */
	protected final Log log = LogFactory.getLog(this.getClass());

	/**
	 * Tornando o contexto acessível a todos os métodos.
	 */
	private HttpContext httpContext;

	private HttpClient httpClient;

	@RequestMapping("/login")
	public void login(HttpServletRequest request) {
		CookieStore cookieStore = new BasicCookieStore();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		String urlLogin = "http://localhost:8081/bonita/loginservice?username={username}&password={password}&redirect={redirect}";

		MultiValueMap<String, String> parametros = new LinkedMultiValueMap<String, String>();
		parametros.add("username", "thiago");
		parametros.add("password", "bpm");
		parametros.add("redirect", "false");

		// Teste de Post
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(restTemplate.getForObject(urlLogin, String.class, parametros));
		// restTemplate.postForObject(urlLogin, request, String.class,
		// parametros);

		this.loginAs("thiago", "bpm");
	}

	//
	// Teste API
	//
	private static void checkBonitaHome() {
		String bonitaHome = System.getProperty(Constantes.BONITA_HOME_KEY);
		if (bonitaHome == null) {
			throw new RuntimeException("The system property bonita.home is not set. Please, set this property with the path to the bonita home folder.\n "
					+ "You can get a bonita home from BonitaBPMCommunity-<bonita.engine.version>-deploy.zip or use the one generated under \n"
					+ "the folder target/home on this project by running the command 'mvn clean install'.");
		}
	}

	public void loginAs(String username, String password) {

		try {

			CookieStore cookieStore = new BasicCookieStore();
			httpContext = new BasicHttpContext();
			httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

			String loginURL = "http://localhost:8081/bonita/loginservice";

			// If you misspell a parameter you will get a HTTP 500 error
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("username", username));
			urlParameters.add(new BasicNameValuePair("password", password));
			urlParameters.add(new BasicNameValuePair("redirect", "false"));

			// UTF-8 is mandatory otherwise you get a NPE
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(urlParameters, "utf-8");
			executePostRequest(loginURL, entity);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private int executePostRequest(String apiURI, UrlEncodedFormEntity entity) {
		try {
			HttpPost postRequest = new HttpPost("http://localhost:8081/bonita/" + apiURI);

			postRequest.setEntity(entity);

			HttpResponse response = httpClient.execute(postRequest, httpContext);

			return consumeResponse(response, true);

		} catch (HttpHostConnectException e) {
			throw new RuntimeException("Bonita bundle may not have been started, or the URL is invalid. Please verify hostname and port number. URL used is: http://localhost:8081/bonita/", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	private int consumeResponse(HttpResponse response, boolean printResponse) {

		String responseAsString = consumeResponseIfNecessary(response);
		if(printResponse) {
			System.out.println(responseAsString);
		}

		return ensureStatusOk(response);
	}
	
	private String consumeResponseIfNecessary(HttpResponse response) {
		if (response.getEntity() != null) {
			BufferedReader rd;
			try {
				rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				return result.toString();
			} catch (Exception e) {
				throw new RuntimeException("Failed to consume response.", e);
			}
		} else {
			return "";
		}
	}
	
	private int ensureStatusOk(HttpResponse response) {
		if (response.getStatusLine().getStatusCode() != 201 && response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode() + " : "
					+ response.getStatusLine().getReasonPhrase());
		}
		return response.getStatusLine().getStatusCode();
	}

}
