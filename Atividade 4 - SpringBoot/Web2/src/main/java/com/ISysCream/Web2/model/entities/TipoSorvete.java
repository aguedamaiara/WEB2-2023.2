package com.ISysCream.Web2.model.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoSorvete {

	private int codigo;
    private String tipo;
    private int quantBolas;//LIMITE
    private double peso;
    private String descricao;
    private double valor;
    
    
	public TipoSorvete(int codigo, String tipo, int quantBolas, double peso, String descricao, double valor) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.quantBolas = quantBolas;
		this.peso = peso;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public int getQuantBolas() {
		return quantBolas;
	}
	public double getPeso() {
		return peso;
	}
	public String getDescricao() {
		return descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setQuantBolas(int quantBolas) {
		this.quantBolas = quantBolas;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public static TipoSorvete extractTipoSorveteFromResultSet(ResultSet result) throws SQLException {
		int codigo = result.getInt("codigo");
		String tipo = result.getString("tipo");
		int quantBolas = result.getInt("quantBolas");
		double peso = result.getDouble("peso");
		String descricao = result.getString("descricao");
		double valor = result.getDouble("valor");

		return new TipoSorvete(codigo, tipo, quantBolas, peso, descricao, valor);
	}
}
