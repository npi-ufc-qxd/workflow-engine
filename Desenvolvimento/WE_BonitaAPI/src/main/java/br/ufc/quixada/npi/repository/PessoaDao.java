package br.ufc.quixada.npi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.ufc.quixada.npi.model.Pessoa;

@Repository
public class PessoaDao {

	/**
	 * Injeção de dependencia do Entity Manager Factory
	 */
	@PersistenceContext
	private EntityManager emanager;
	
	
	@Transactional
	public void adiciona(Pessoa pessoa){
		emanager.persist(pessoa);
	}
	
	
	public void altera(Pessoa pessoa){
		emanager.merge(pessoa);
	}
	
	
	public List<Pessoa> lista(){
		return emanager.createQuery("from Pessoa").getResultList();
	}
	
	
	public Pessoa buscaPorId(int id){
		return emanager.find(Pessoa.class, id);
	}
}
