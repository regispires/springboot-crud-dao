package dsweb.dao;

import java.util.List;

import dsweb.model.Contato;

public interface ContatoDao {

	public void adiciona(Contato contato);

	public List<Contato> getLista();

	public Contato getContato(Integer id);

	public void altera(Contato contato);

	public void remove(Contato contato);

}