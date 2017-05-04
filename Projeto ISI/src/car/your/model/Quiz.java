package car.your.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import car.your.model.database.Carro;
import car.your.model.database.Categoria;
import car.your.model.database.QuizPergunta;
import car.your.model.database.QuizResposta;
import car.your.model.jdbc.Database;
import jdbchelper.JdbcException;
import jdbchelper.JdbcHelper;
import jdbchelper.QueryResult;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 11:54:06
 *
 */
public class Quiz {
	
	public static final Database JDBC = new Database(); // Mesmo para todas as instâncias
	
	static { // Bloco chamado apenas 1 vez
		Quiz.JDBC.connect();
	}
	
	/*
	 * Cada usuário tem de receber sua própria instância de QUIZ
	 * para que o resultado do teste de um usuário não influênciar
	 * no resultado de outros usuários, por isso devemos usar o
	 * controle se sessões para que cada usuário tenha seu QUIZ...
	 */

	private Map<Integer, Carro> carros;
	private Map<Integer, Categoria> categorias;
	private Map<Integer, QuizResposta> respostas;
	private Map<Integer, QuizPergunta> perguntas;
	
	public Quiz() {
		this.carros = new HashMap<Integer, Carro>();
		this.categorias = new HashMap<Integer, Categoria>();
		this.respostas = new HashMap<Integer, QuizResposta>();
		this.perguntas = new HashMap<Integer, QuizPergunta>();
	}
	
	/*
	 * Carrega o banco de dados e joga nas ArrayList da classe
	 * na instância atual, nunca esquecer o invoke iniciar()
	 */
	public void iniciar() {
		// Carrega as categorias
		QueryResult resultadoCategorias = Quiz.JDBC.getJdbc().query("SELECT * FROM `categorias`");
		resultadoCategorias.setAutoClose(true);
		while (resultadoCategorias.next()) {
			Categoria categoria = new Categoria(resultadoCategorias);
			this.categorias.put(categoria.getId(), categoria);
		}
		
		// Carrega os carros disponíveis
		QueryResult resultadoCarros = Quiz.JDBC.getJdbc().query("SELECT * FROM `carros`");
		resultadoCarros.setAutoClose(true);
		while (resultadoCarros.next()) {
			Carro carro = new Carro(resultadoCarros);
			this.carros.put(carro.getId(), carro);
			this.categorias.get(resultadoCarros.getInt("CategoriaID")).add(carro);
		}
		
		// Carrega as perguntas do quiz
		QueryResult resultadoQuiz = Quiz.JDBC.getJdbc().query("SELECT * FROM `perguntas`");
		resultadoQuiz.setAutoClose(true);
		while (resultadoQuiz.next()) {
			QuizPergunta pergunta = new QuizPergunta(resultadoQuiz);
			this.perguntas.put(pergunta.getId(), pergunta);
		}
		
		// Carrega as respostas e atribui pra sua pergunta
		QueryResult resultadoQuizRespostas = Quiz.JDBC.getJdbc().query("SELECT * FROM `respostas`");
		resultadoQuizRespostas.setAutoClose(true);
		while (resultadoQuizRespostas.next()) {
			QuizResposta resposta = new QuizResposta(resultadoQuizRespostas);
			this.respostas.put(resposta.getId(), resposta);
			
			// Adiciona a resposta para a pergunta
			QuizPergunta pergunta = this.perguntas.get(resultadoQuizRespostas.getInt("PerguntaID"));
			pergunta.add(resposta);
		}
		
		// Carrega os carros que farão pontos pela resposta do usuário
		QueryResult resultadoQuizRespostasCarros = Quiz.JDBC.getJdbc().query("SELECT * FROM `respostas_carros`");
		resultadoQuizRespostasCarros.setAutoClose(true);
		while (resultadoQuizRespostasCarros.next()) {
			QuizResposta resposta = this.respostas.get(resultadoQuizRespostasCarros.getInt("RespostaID"));
			resposta.add(this.carros.get(resultadoQuizRespostasCarros.getInt("CarroID")));
		}
	}
	
	public void quiz(Map<String, String[]> parameters, String sessionID) {
		JdbcHelper jdbc = Quiz.JDBC.getJdbc();
		jdbc.beginTransaction();
		
		try {
			jdbc.run("INSERT INTO `quiz` ( `SessionID` ) VALUES ( ? )", new Object[] { sessionID });
			long id = jdbc.getLastInsertId();
			
			for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
				for (String idResposta : parameter.getValue()) { // Pega todas as respostas do usuário
					QuizPergunta pergunta = this.getPergunta(Integer.parseInt(parameter.getKey()));
					QuizResposta resposta = this.getResposta(Integer.parseInt(idResposta));
					jdbc.run("INSERT INTO `quiz_respostas` ( `QuizID`, `PerguntaID`, `RespostaID` ) VALUES ( ?, ?, ? )", new Object[] { id, pergunta.getId(), resposta.getId() });
					
					for (Carro carro : resposta.getCarros()) {
						carro.contar(); // Adiciona o contador do carro
					}
				}
			}
			
			Carro carroIdeal = this.getCarro();
			jdbc.run("UPDATE `quiz` SET `CarroID` = ? WHERE ( `id` = ? )", new Object[] { carroIdeal.getId(), id });
		} catch (JdbcException e) {
			jdbc.rollbackTransaction();
		} finally {
			if ((jdbc.isInTransaction())) {
				jdbc.commitTransaction();
			}
		}
	}
	
	public Carro getCarro() { // Seleciona o carro que tem mais haver com o usuário
		Carro escolhido = null;
		
		for (Carro carro : this.getCarros()) {
			if ((escolhido == null)) { // Primeiro carro do loop
				escolhido = carro;
			} else {
				if ((carro.getContador() > escolhido.getContador())) { // Carro teve mais escolhas
					escolhido = carro;
				} else if ((carro.getContador() == escolhido.getContador())) { // Empate
					char carroA = carro.getNome().charAt(0);
					char carroB = escolhido.getNome().charAt(0);
					
					if ((carroA < carroB)) { // Verifica ordem alfabetica
						escolhido = carro;
					}
				}
			}
		}
		
		return escolhido; // Se retornar NULL é erro da leitura do banco de dados...
	}
	
	public Collection<Categoria> getCategorias() {
		return this.categorias.values();
	}
	
	public Categoria getCategorias(int id) {
		return this.categorias.get(id);
	}
	
	public Collection<QuizPergunta> getPerguntas() {
		return this.perguntas.values();
	}
	
	public Collection<Carro> getCarros() {
		return this.carros.values();
	}
	
	public QuizPergunta getPergunta(int id) {
		return this.perguntas.get(id);
	}
	
	public QuizResposta getResposta(int id) {
		return this.respostas.get(id);
	}
	
	public Carro getCarro(int id) {
		return this.carros.get(id);
	}

}
