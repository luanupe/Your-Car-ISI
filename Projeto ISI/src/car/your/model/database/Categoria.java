package car.your.model.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jdbchelper.QueryResult;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 14:23:10
 *
 */
public class Categoria {
	
	private int id;
	private String nome;
	private String thumb;
	private List<Carro> carros;
	
	public Categoria(QueryResult resultado) {
		this.carros = new ArrayList<Carro>();
		this.id = resultado.getInt("id");
		this.nome = resultado.getString("Nome");
		this.thumb = resultado.getString("Thumb");
	}
	
	public void add(Carro carro) {
		this.carros.add(carro);
	}
	
	public Collection<Carro> getCarros() {
		return this.carros;
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

}
