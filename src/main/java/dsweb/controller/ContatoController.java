package dsweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dsweb.dao.ContatoDao;
import dsweb.model.Contato;

@Controller
public class ContatoController {

	@Autowired
	@Qualifier("contatoMapDao")
	private ContatoDao contatoDao;
	
	@RequestMapping("/")
	public String home() {
		return "forward:/lista_contatos";
	}
	
	@RequestMapping("/lista_contatos")
	public String listaContatos(Model model) {
		List<Contato> lista = contatoDao.getLista();
		model.addAttribute("contatos", lista);
		return "lista_contatos";
	}
	
	@RequestMapping("/insere_contato_form")
	public String insereForm(Model model) {
		model.addAttribute("contato", new Contato());
		model.addAttribute("acao", "/add_contato");
		return "insere_contato";
	}

	@PostMapping("/add_contato")
	public String addContato(@Valid Contato contato, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("contato", contato);
			model.addAttribute("acao", "/add_contato");
			return "insere_contato";
		}
		contatoDao.adiciona(contato);
		redirectAttributes.addFlashAttribute("msg", "Contato inserido com sucesso.");
		return "redirect:/lista_contatos";
	}

	@RequestMapping("/altera_contato_form/{id}")
	public String alteraForm(@PathVariable Integer id, Model model) {
		System.out.println("id: " + id);
		Contato c = contatoDao.getContato(id);
		System.out.println("contato: " + c);
		model.addAttribute("contato", c);
		model.addAttribute("acao", "/update_contato");
		return "altera_contato";
	}
	
	@PostMapping("/update_contato")
	public String updateContato(@Valid Contato contato, BindingResult result, 
		Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("contato", contato);
			model.addAttribute("acao", "/update_contato");
			return "altera_contato";
		}
		contatoDao.altera(contato);
		redirectAttributes.addFlashAttribute("msg", "Contato alterado com sucesso.");
		return "redirect:/lista_contatos";
	}
	
	@RequestMapping("/delete_contato/{id}")
	public String deleteContato(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		System.out.println("id: " + id);
		Contato contato = new Contato(id);
		System.out.println("contato: " + contato);
		contatoDao.remove(contato);
		redirectAttributes.addFlashAttribute("msg", "Contato removido com sucesso.");
		return "redirect:/lista_contatos";
	}
	
}
