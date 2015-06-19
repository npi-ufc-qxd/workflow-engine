package br.ufc.quixada.npi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.bonitasoft.engine.api.LoginAPI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.model.BonitaUser;
import br.ufc.quixada.npi.util.Constantes;

@Controller
@RequestMapping("/auth")
public class AuthController {

	protected final RestTemplate restTemplate = null;
	private LoginAPI loginApi;

	@RequestMapping("/loginREST")
	public String loginREST(Model model, HttpServletRequest request) {
		Map<String, String> vars = new HashMap<String, String>();

		// Configurações do site
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);

		// REST
		RestTemplate rest = new RestTemplate();
		String url = "http://localhost:8081/bonita/loginservice?username=thiago&password=bpm&redirect=false";

		System.out.println(rest.getForEntity(url, String.class).toString());
		System.out.println(rest.getForObject(url, String.class, vars));

		// Adicionando parametros POST
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("username", "thiago");
		map.add("password", "bpm");
		map.add("redirect", "false");

		System.out.println(rest.postForObject("http://localhost:8081/bonita/loginservice", map, String.class));

		model.addAttribute("vars", vars);
		model.addAttribute("rest", rest);

		// Caso tenha conseguido logar
		model.addAttribute("redireciona", true);

		// Adicionando ao modelo
		// model.addAttribute("teste", teste);

		// Sessão
		HttpSession session = request.getSession();
		session.setAttribute("var", vars);
		session.setAttribute("map", map);

		model.addAttribute("session", session);

		Cookie[] cookie = request.getCookies();

		System.out.println(cookie);

		for (Cookie c : cookie) {
			System.out.println("Cookie: " + c.getValue());
		}

		return "auth/login";
	}

	
	@RequestMapping("/login")
	public String login(Model model) {
		// Configurações do site.
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);

		// Adicionando a classe BonitaUser para realizar validação.
		model.addAttribute("BonitaUser", new BonitaUser());

		return "auth/login";
	}

	
	/**
	 * Método que recebe o input do usuário com os dados para efetuar login. A
	 * variavel model deve estar no final dos parametros.
	 * 
	 * @param bonita
	 * @param BindingResult
	 * @param model
	 * @return {@link String}
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processaLogin(@Valid @ModelAttribute("BonitaUser") BonitaUser bonita, BindingResult BindingResult, RedirectAttributes attributes, HttpSession session, Model model) {

		if (BindingResult.hasErrors()) {
			// Configurações do site
			model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);

			return "auth/login";
		}

		// Preparando login na Plataforma
		BonitaApi bonitaApi = new BonitaApi();
		this.loginApi = bonitaApi.getLoginApi();

		// Verificando o resultado do processo de login
		if (bonitaApi.login(bonita)) {
			// Registra sessão
			this.registraSessao(bonita, session, bonitaApi);

			attributes.addFlashAttribute("msg", "Login efetuado com sucesso.");
			return "redirect:/dashboard";
		} else {
			attributes.addFlashAttribute("msg", "Usuário ou Senha estão incorretos.<br>"
					+ "<small>(A autenticação é realizada pela plataforma de BPM, verifique se a hierarquia de organizações foi publicada na Engine e o <a href='../bonitaService/statusAPI'>Status da API</a>.)</small>");
			return "redirect:/auth/login";
		}
	}

	
	/**
	 * Registra a sessão do usuário num objeto HttpSession.
	 * 
	 * @param bonitaUser
	 * @param session
	 * @param idUsuario
	 */
	private void registraSessao(BonitaUser bonitaUser, HttpSession session, BonitaApi bonitaApi) {
		// Salvando dados na sessão
		session.setAttribute(Constantes.ITENS_SESSAO.get("ID_SESSAO"), session.getId());
		session.setAttribute(Constantes.ITENS_SESSAO.get("ID_USUARIO"), bonitaApi.getApiSession().getUserId());
		session.setAttribute(Constantes.ITENS_SESSAO.get("LOGIN"), bonitaUser.getUsuario());
		session.setAttribute(Constantes.ITENS_SESSAO.get("SENHA"), bonitaUser.getSenha());
		session.setAttribute(Constantes.ITENS_SESSAO.get("BONITA_API"), bonitaApi);
		session.setAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION"), bonitaApi.getApiSession());
	}

	
	@RequestMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes attributes, BonitaApi bonitaApi) {

		// bonitaApi.logout((LoginAPI)
		// session.getAttribute(Constantes.ITENS_SESSAO.get("BONITA_SESSION")));
		System.out.println("Limpa sessão...");
		session.invalidate();

		return "redirect:/auth/login";
	}

	
	/**
	 * Informa de maneira statica se a sessão está ativa pelo usuário logado.
	 * 
	 * @param session
	 * @return true = sessão ativa
	 * @return false = sessão inativa
	 */
	public static boolean sessaoEstaAtiva(HttpSession session) {
		if (session.getAttribute(Constantes.ITENS_SESSAO.get("ID_USUARIO")) != null) {
			return true;
		} else {
			return false;
		}
	}
}
