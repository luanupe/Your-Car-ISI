package car.your.model.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jdbchelper.QueryResult;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 12:08:17
 *
 */
public class QuizResposta {
	
	private int id;
	private String descricao;
	private List<Carro> carros;
	
	public QuizResposta(QueryResult resultado) {
		this.carros = new ArrayList<Carro>();
		this.id = resultado.getInt("id");
		this.descricao = resultado.getString("Descricao");
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
	
	public String getDescricao() {
		return this.descricao;
	}

}
