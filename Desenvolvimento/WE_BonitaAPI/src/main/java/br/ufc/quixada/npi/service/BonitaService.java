package br.ufc.quixada.npi.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.npi.util.Constantes;

@Service
@RequestMapping("/bonitaService")
public class BonitaService {

	@RequestMapping("/statusAPI")
	public String teste(Model model, HttpSession session) {
		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("sessao", session.getAttribute("idUsuario").toString());

		return "service/ajaxStatusApi";
	}

	
	@RequestMapping("/statusEngine")
	public String statusEngine(Model model) {
		Socket socket = new Socket();
		SocketAddress address = new InetSocketAddress(Constantes.BONITA_URL, Constantes.BONITA_PORT);
		boolean online = true;

		try {
			socket.connect(address, 4000);
		} catch (SocketTimeoutException stex) {
			online = false;
		} catch (IOException e) {
			System.out.println(e);
			online = false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		model.addAttribute("siteDetalhes", Constantes.SITE_DETALHES);
		model.addAttribute("statusEngine", online);

		return "service/ajaxStatusEngine";
	}
}
