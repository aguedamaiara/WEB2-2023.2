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

	public Sorvete(int codigo, Date dataCompra) {
		super();
		Codigo = codigo;
		this.dataCompra = dataCompra;
		this.sabores = new ArrayList<>();
		this.tipoSorvete = tipoSorvete;
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
		this.sabores.add(sabor);
	}

	public TipoSorvete getTipoSorvete() {
		return tipoSorvete;
	}

	public void setTipoSorvete(TipoSorvete tipoSorvete) {
		this.tipoSorvete = tipoSorvete;
	}
}
