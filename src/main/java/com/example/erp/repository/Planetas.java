package com.example.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.erp.model.Planeta;

public class Planetas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Planetas() {

	}

	public Planetas(EntityManager manager) {
		this.manager = manager;
	}

	public List<Planeta> pesquisarTodos() {
		return manager.createQuery("from Planeta", Planeta.class).getResultList();
	}

	public List<Planeta> pesquisar(String termo) {
		String jpql = "FROM Planeta WHERE nome like :termo OR id like :termo";

		TypedQuery<Planeta> query = manager.createQuery(jpql, Planeta.class);

		query.setParameter("termo", "%" + termo + "%");

		return query.getResultList();
	}

	public Planeta buscarPorId(Long id) {
		return manager.find(Planeta.class, id);
	}

	public Planeta gravar(Planeta planeta) {
		return manager.merge(planeta);
	}

	public void remover(Planeta planeta) {
		planeta = buscarPorId(planeta.getId());
		manager.remove(planeta);
	}

}
