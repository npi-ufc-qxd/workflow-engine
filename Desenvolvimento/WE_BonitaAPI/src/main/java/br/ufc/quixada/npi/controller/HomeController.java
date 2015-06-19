package br.ufc.quixada.npi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.util.Constantes;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		String ip = request.getRemoteAddr();
		System.out.println("--> HomeController executado por: " + ip);

		// Informações gerais do sistema
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		
		//Verificando se a sessão não é nula para adicionar ao modelo
		if(AuthController.sessaoEstaAtiva(session)){
			model.addAttribute("sessao", session);
		}
		
		return "home";
	}

	
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session, RedirectAttributes attributes) {		
		if(AuthController.sessaoEstaAtiva(session)){
			// Sessão está ativa
			model.addAttribute("sessao", session);
		} else {
			attributes.addFlashAttribute("msg", "Para visualizar o <b>Dashboard</b> é necessário estar logado no sistema.");
			return "redirect:/auth/login";
		}
		
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("usuarios", BonitaApi.listaUsuarios(session, 2));
		model.addAttribute("grupos", BonitaApi.listaGrupos(session));

		return "dashboard";
	}
}
