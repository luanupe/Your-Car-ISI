package car.your.model.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jdbchelper.QueryResult;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 12:08:02
 *
 */
public class QuizPergunta {
	
	private int id;
	private String enunciado;
	private List<QuizResposta> respostas;
	
	public QuizPergunta(QueryResult resultado) {
		this.respostas = new ArrayList<QuizResposta>();
		this.id = resultado.getInt("id");
		this.enunciado = resultado.getString("Enunciado");
	}
	
	public void add(QuizResposta resposta) {
		this.respostas.add(resposta);
	}
	
	public Collection<QuizResposta> getRespostas() {
		return this.respostas;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getEnunciado() {
		return this.enunciado;
	}

}
