package br.ufc.quixada.npi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.util.Constantes;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	
	
	@RequestMapping("/membrosPorGrupo/{id}")
	public String exibeMembrosPorGrupo(HttpSession session, @PathVariable("id") int idGrupo, RedirectAttributes attributes, Model model){
		// Verificando sessão do usuário
		if(AuthController.sessaoEstaAtiva(session)){
			// Sessão está ativa
			model.addAttribute("sessao", session);
		} else {
			attributes.addFlashAttribute("msg", "Para visualizar o <b>Dashboard</b> é necessário estar logado no sistema.");
			return "redirect:/auth/login";
		}
		
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("usuarios", BonitaApi.listaUsuarios(session, idGrupo));
		
		return "dashboard/exibeMembrosPorGrupo";
	}
	
	
	@RequestMapping("/gerente/{id}")
	public String exibeGerente(HttpSession session, @PathVariable("id") int idGerente, RedirectAttributes attributes, Model model){
		// Verificando sessão do usuário
		if(AuthController.sessaoEstaAtiva(session)){
			// Sessão está ativa
			model.addAttribute("sessao", session);
		} else {
			attributes.addFlashAttribute("msg", "Para visualizar o <b>Dashboard</b> é necessário estar logado no sistema.");
			return "redirect:/auth/login";
		}
		
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("usuarios", BonitaApi.listaUsuarios(session, idGerente));
		
		return "dashboard/exibeGerente";
	}
	
	
	@RequestMapping("/processo/{id}")
	public String exibeProcesso(HttpSession session, @PathVariable("id") int idProcesso, RedirectAttributes attributes, Model model){
		// Verificando sessão do usuário
		if(AuthController.sessaoEstaAtiva(session)){
			// Sessão está ativa
			model.addAttribute("sessao", session);
		} else {
			attributes.addFlashAttribute("msg", "Para visualizar o <b>Dashboard</b> é necessário estar logado no sistema.");
			return "redirect:/auth/login";
		}
		
		BonitaApi.exibeDetalhesProcesso(session, (long) idProcesso);
		
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("instancias", BonitaApi.exibeDetalhesProcesso(session, (long) idProcesso));
		
		return "dashboard/processo";
	}
	
	
	@RequestMapping("/processo/habilita/{id}")
	public String habilitaProcesso(HttpSession session, @PathVariable("id") int idProcesso, RedirectAttributes attributes){
		if(BonitaApi.habilitaProcesso(session, (long) idProcesso)){
			attributes.addFlashAttribute("msg", "Processo de <b>#ID "+ idProcesso +"</b> foi habilitado com sucesso");
		} else {
			attributes.addFlashAttribute("msg", "Ocorreu um erro ao habilitar o processo de <b>#ID "+ idProcesso +"</b>");
		}
		
		return "redirect:/dashboard";
	}
	
	
	@RequestMapping("/processo/inicializa/{id}")
	public String inicializaProcesso(HttpSession session, @PathVariable("id") int idProcesso, RedirectAttributes attributes){
		if(BonitaApi.inicializaProcesso(session, (long) idProcesso)){
			attributes.addFlashAttribute("msg", "Processo de <b>#ID "+ idProcesso +"</b> foi inicializado com sucesso");
		} else {
			attributes.addFlashAttribute("msg", "Ocorreu um erro na inicialização do processo de <b>#ID "+ idProcesso +"</b>");
		}
		
		return "redirect:/dashboard";
	}
}
