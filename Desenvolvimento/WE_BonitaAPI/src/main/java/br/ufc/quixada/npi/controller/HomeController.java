package br.ufc.quixada.npi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.npi.util.Constantes;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		String ip = request.getRemoteAddr();
		System.out.println("--> HomeController executado por: " + ip);


		// Informações gerais do sistema
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("sessao", session.getAttribute("idUsuario").toString());

		return "home";
	}

	
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		
		Map<String, String> sessao = new HashMap<String, String>();
		sessao.put("idSessao", (String) session.getAttribute("idSessao"));
		sessao.put("idUsuario", session.getAttribute("idUsuario").toString());
		sessao.put("login", (String) session.getAttribute("login"));
		sessao.put("senha", (String) session.getAttribute("senha"));
		
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("sessao", sessao);

		return "dashboard";
	}
}
