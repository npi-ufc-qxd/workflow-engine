package br.ufc.quixada.npi.we.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.npi.we.model.BonitaUser;
import br.ufc.quixada.npi.we.util.Constantes;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private BonitaEngineController bonitaEC = null;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("site", Constantes.SITE);

		// Add model BonitaUser
		model.addAttribute("BonitaUser", new BonitaUser());

		return "auth/login";
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processaLogin(@Valid @ModelAttribute("BonitaUser") BonitaUser bonitaUser, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession, Model model) {
		model.addAttribute("site", Constantes.SITE);

		// Validação
		if (bindingResult.hasErrors()) {
			return "auth/login";
		}

		// Efetuando login pela Bonita Engine
		bonitaEC = new BonitaEngineController();

		try {
			if (bonitaEC.login(bonitaUser.getUsuario(), bonitaUser.getSenha(), httpSession)) {
				// Registrando HTTPSession
				SessionController.setUserSession(httpSession, bonitaUser);
				
				redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Olá, "+ WordUtils.capitalize(bonitaUser.getUsuario()) +" seja bem vindo(a)!");
				return "redirect:/dashboard/";
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Usuário ou Senha estão incorretos.<br>"
				+ "<small>(A autenticação é realizada através da plataforma de BPM, verifique se a hierarquia de organizações foi publicada na Engine e o <a href='../status'>Status da API</a>.)</small>");
		return "redirect:/auth/login";
	}
	
	
	@RequestMapping("/logoff")
	public String logoff(HttpSession httpSession, RedirectAttributes redirectAttributes){
		// Efetuando logoff na engine
		bonitaEC.logoff(httpSession);
		
		// Limpando a sessão definida para o cliente.
		SessionController.destroySession(httpSession);
		
		redirectAttributes.addFlashAttribute(Constantes.FLASH_MESSAGE, "Sessão finalizada.");
		return "redirect:/";
	}
}
