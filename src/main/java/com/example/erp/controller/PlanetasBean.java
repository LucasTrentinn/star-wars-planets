package com.example.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;

import com.example.erp.model.Planeta;
import com.example.erp.repository.Planetas;
import com.example.erp.service.PlanetasService;

@Named
@ViewScoped
public class PlanetasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Planetas planetas;

	private Planeta planeta;

	@Inject
	private PlanetasService planetasService;

	private List<Planeta> listaPlanetas;

	private String termoPesquisa;

	public void pesquisarTodos() {
		listaPlanetas = planetas.pesquisarTodos();
		termoPesquisa = "";
	}

	public void pesquisarPlanetas() {
		listaPlanetas = planetas.pesquisar(termoPesquisa);
	}

	public void prepararNovoPlaneta() {
		planeta = new Planeta();
	}

	public void salvarPlaneta() throws Exception {
		tratarAparicoesFilme(planeta.getNome());
		planetasService.salvar(planeta);
		this.closeDialog();
	}

	public void excluirPlaneta() {
		planetasService.excluir(planeta);
		planeta = null;
	}

	public void viewDialog() {
		DialogFrameworkOptions options = DialogFrameworkOptions.builder().resizable(false).draggable(false).modal(true)
				.closeOnEscape(true).responsive(true).resizeObserver(true).resizeObserverCenter(true).build();
		PrimeFaces.current().dialog().openDynamic("viewCadastroPlanetas", options, null);
	}

	public void closeDialog() {
		PrimeFaces.current().dialog().closeDynamic("viewCadastroPlanetas");
	}

	public boolean planetaSelecionado() {
		return planeta != null && planeta.getId() != null;
	}

	public void tratarAparicoesFilme(String nomePlaneta) throws Exception {
		int qtdFilmes = planetasService.getFilmAppearences(nomePlaneta);
		planeta.setQntdFilmes(qtdFilmes);
	}

	public Planeta getPlaneta() {
		return planeta;
	}

	public void setPlaneta(Planeta planeta) {
		this.planeta = planeta;
	}

	public List<Planeta> getListaPlanetas() {
		return listaPlanetas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
}
