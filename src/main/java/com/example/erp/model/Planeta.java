package com.example.erp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "planets")
public class Planeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1)
	@Column(nullable = false, length = 80)
	private String nome;

	@NotNull
	@Size(min = 1)
	@Column(nullable = false, length = 80)
	private String clima;

	@NotNull
	@Size(min = 1)
	@Column(nullable = false, length = 80)
	private String terreno;

	@NotNull
	@Column(nullable = false)
	private int qntdFilmes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Planets [id=" + id + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getQntdFilmes() {
		return qntdFilmes;
	}

	public void setQntdFilmes(int qntdFilmes) {
		this.qntdFilmes = qntdFilmes;
	}
}
