package com.ISysCream.Web2.model.entities;
import com.ISysCream.Web2.model.entities.TipoSorvete;
import com.ISysCream.Web2.model.entities.Sabor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Sorvete {
	private int Codigo;
	private Date dataCompra;
	private List<Sabor> sabores;
	private TipoSorvete tipoSorvete;

	public Sorvete(int codigo, Date dataCompra, List<Sabor> sabores, TipoSorvete tipoSorvete) {
		super();
		Codigo = codigo;
		this.dataCompra = dataCompra;
		this.sabores = new ArrayList<>();
		// Adiciona os sabores fornecidos ao construtor à lista de sabores
		this.sabores.addAll(sabores);
	}

	public int getCodigo() {
		return Codigo;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public List<Sabor> getSabores() {
		return sabores;
	}

	public void addSabor(Sabor sabor) {
		// Verificar se o número de sabores não excede o limite permitido para o tipo de sorvete
		if (sabores.size() < tipoSorvete.getQuantBolas()) {
			this.sabores.add(sabor);
		} else {
			// Lançar uma exceção ou tomar outra ação apropriada se exceder o limite
			throw new IllegalArgumentException("Número de sabores excede o limite permitido para o tipo de sorvete.");
		}
	}

	public TipoSorvete getTipoSorvete() {
		return tipoSorvete;
	}

	public void setTipoSorvete(TipoSorvete tipoSorvete) {
		this.tipoSorvete = tipoSorvete;
	}
}
