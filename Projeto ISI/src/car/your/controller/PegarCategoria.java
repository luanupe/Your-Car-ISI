package car.your.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.your.model.Quiz;
import car.your.model.database.Categoria;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 6 de fev de 2017
 * Time: 15:53:35
 *
 */
public class PegarCategoria extends HttpServlet {

	private static final long serialVersionUID = 6060962639975913057L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("cat");
		try {
			Quiz quiz = new Quiz();
			quiz.iniciar();
			
			Categoria categoria = quiz.getCategorias(Integer.parseInt(id));
			request.setAttribute("carros", categoria.getCarros());
			request.getRequestDispatcher("categorias.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
