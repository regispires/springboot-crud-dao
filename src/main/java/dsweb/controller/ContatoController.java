package dsweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dsweb.dao.ContatoDao;
import dsweb.model.Contato;

@Controller
public class ContatoController {

	@Autowired
	@Qualifier("contatoJpaDao") // opções: contatoMapDao, contatoJdbcDao, contatoJpaDao
	private ContatoDao contatoDao;
	
	@GetMapping("/")
	public String home() {
		return "forward:/contatos";
	}
	
	@GetMapping("/contatos")
	public String listaContatos(Model model) {
		List<Contato> lista = contatoDao.getLista();
		model.addAttribute("contatos", lista);
		return "lista_contatos";
	}
	
	@GetMapping("/contatos/add")
	public String insereForm(Model model) {
		model.addAttribute("contato", new Contato());
		model.addAttribute("acao", "/contatos");
		return "insere_contato";
	}
	
	@GetMapping("/contatos/{id}/update")
	public String alteraForm(@PathVariable Integer id, Model model) {
		System.out.println("id: " + id);
		Contato c = contatoDao.getContato(id);
		System.out.println("contato: " + c);
		model.addAttribute("contato", c);
		model.addAttribute("acao", "/contatos");
		return "altera_contato";
	}

	@PostMapping("/contatos")
	public String addContato(@Valid Contato contato, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			model.addAttribute("contato", contato);
			model.addAttribute("acao", "/contatos");
			if (contato.getId() == null) {
				return "insere_contato";
			} else {
				return "altera_contato";
			}
		}
		
		if (contato.getId() == null) {
			contatoDao.adiciona(contato);
			redirectAttributes.addFlashAttribute("msg", "Contato inserido com sucesso.");
		} else {
			contatoDao.altera(contato);
			redirectAttributes.addFlashAttribute("msg", "Contato atualizado com sucesso.");
		}
		return "redirect:/contatos";
	}

	@GetMapping("/contatos/{id}/delete")
	public String deleteContato(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		System.out.println("id: " + id);
		Contato contato = new Contato(id);
		System.out.println("contato: " + contato);
		contatoDao.remove(contato);
		redirectAttributes.addFlashAttribute("msg", "Contato removido com sucesso.");
		return "redirect:/contatos";
	}
	
}
