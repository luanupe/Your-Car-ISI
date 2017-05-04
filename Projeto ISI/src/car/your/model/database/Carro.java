package car.your.model.database;

import jdbchelper.QueryResult;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 12:07:44
 *
 */
public class Carro {
	
	private int contador;
	private int id;
	private String nome;
	private String thumb;
	private String descricao;
	
	public Carro(QueryResult resultado) {
		this.contador = 0;
		this.id = resultado.getInt("id");
		this.nome = resultado.getString("Nome");
		this.thumb = resultado.getString("Thumb");
		this.descricao = resultado.getString("Descricao");
	}
	
	public int getContador() {
		return this.contador;
	}
	
	public void contar() {
		++this.contador;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getThumb() {
		return this.thumb;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
