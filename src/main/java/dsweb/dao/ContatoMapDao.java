package dsweb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import dsweb.model.Contato;

@Repository
public class ContatoMapDao implements ContatoDao {
	private Map<Integer, Contato> contatos = new HashMap<Integer, Contato>();
	private Integer proximoId = 1;
	
	@Override
	public void adiciona(Contato contato) {
		if (contato.getId() == null) {
			contato.setId(proximoId++);
		}
		contatos.put(contato.getId(), contato);
	}

	@Override
	public List<Contato> getLista() {
		return new ArrayList<Contato>(contatos.values());
	}

	@Override
	public Contato getContato(Integer id) {
		return contatos.get(id);
	}

	@Override
	public void altera(Contato contato) {
		contatos.put(contato.getId(), contato);
	}

	@Override
	public void remove(Contato contato) {
		contatos.remove(contato.getId());
	}

}
