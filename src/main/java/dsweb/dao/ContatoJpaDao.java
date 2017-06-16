package dsweb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dsweb.model.Contato;

@Repository
@Primary
public class ContatoJpaDao implements ContatoDao {

	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional
	public void adiciona(Contato contato) {
		em.persist(contato);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Contato> getLista() {
		List<Contato> result = em.createQuery("from contatos", 
				Contato.class).getResultList();
		return result;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Contato getContato(Integer id) {
		return em.find(Contato.class, id);
	}
	
	@Override
	@Transactional
	public void altera(Contato contato) {
		em.merge(contato);
	}
	
	@Override
	@Transactional
	public void remove(Contato contato) {
		em.remove(em.merge(contato));
	}

}
