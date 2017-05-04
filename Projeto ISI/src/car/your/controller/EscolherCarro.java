package car.your.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import car.your.model.Quiz;
import car.your.model.database.Carro;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 18:22:47
 *
 */
public class EscolherCarro extends HttpServlet {

	private static final long serialVersionUID = -5242538184108965788L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if ((request.getSession().getAttribute("quiz") != null)) {
			Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
			quiz.iniciar(); // Reincia os contadores...
			
			Map<String, String[]> parameters = request.getParameterMap();
			if ((parameters.size() != quiz.getPerguntas().size())) { // Verifica se todas as perguntas foram respondidas
				request.getRequestDispatcher("error.html").forward(request, response);
			} else {
				quiz.quiz(parameters, request.getSession().getId());
				request.setAttribute("ideal", quiz.getCarro());
				request.getRequestDispatcher("resultado.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Quiz quiz = new Quiz();
			quiz.iniciar();

			Carro carroIdeal = quiz.getCarro(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("ideal", carroIdeal);
			request.getRequestDispatcher("resultado.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
 
}
