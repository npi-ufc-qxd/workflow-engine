package br.ufc.quixada.npi.we.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.npi.we.util.Constantes;

/**
 * Home Controller.
 * 
 * @author Thiago Pereira Rosa - thiagor@engineer.com
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	
	
	@RequestMapping("/")
	public String principal(HttpSession httpSession, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		return "home";
	}
	
	
	/**
	 * Exibe página com informações sobre o status da Engine.
	 * @param model
	 * @return String status.jsp
	 */
	@RequestMapping("/status")
	public String statusApi(HttpSession httpSession, Model model){
		model.addAttribute("site", Constantes.SITE);
		model.addAttribute("sessao", SessionController.statusSession(httpSession));
		
		return "status";
	}

	
	/**
	 * Exibe o status da Engine. Permitindo consulta via Ajax.
	 * @param model
	 * @return {@link Boolean} true = Ativa, false = Inativa
	 */
	@RequestMapping("/ajax/statusEngine")
	public String statusEngine(Model model){
		Socket socket = new Socket();
		SocketAddress address = new InetSocketAddress(Constantes.BONITA.get("URL"), Integer.parseInt(Constantes.BONITA.get("PORT")));
		boolean online = true;

		try {
			socket.connect(address, 4000);
		} catch (SocketTimeoutException stex) {
			online = false;
		} catch (IOException e) {
			System.out.println(e + " - (Verifique se a Engine está em execução)");
			online = false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		model.addAttribute("statusEngine", online);
		return "ajax/statusApi";
	}
}
