package car.your.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import car.your.model.Quiz;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 4 de fev de 2017
 * Time: 19:36:43
 *
 */
public class ResultadoCarro extends HttpServlet {

	private static final long serialVersionUID = 6060962639975913057L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if ((request.getSession().getAttribute("quiz") != null)) {
			Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
			request.setAttribute("ideal", quiz.getCarro());
			request.getRequestDispatcher("resultado.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}

