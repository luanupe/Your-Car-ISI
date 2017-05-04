<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.Collection" %>
<%@ page import="car.your.model.Quiz" %>
<%@ page import="car.your.model.database.Categoria" %>
<%@ page import="car.your.model.database.QuizPergunta" %>
<%@ page import="car.your.model.database.QuizResposta" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="template/css/principal.css"/>
		<title>Your Classic &middot; Info</title>
	</head>
	
	<body>
		<%
			if ((session.getAttribute("quiz") == null)) {
				out.print("<script>window.location.href = \"index.jsp\";</script>");
			} else {
				Quiz quiz = (Quiz) session.getAttribute("quiz");
				quiz.iniciar();
		%>
		
		<nav>
			<a id="home" href="#homeSection"> HOME </a>
			<a id="car" href="#carSection"> CLASSICS CARS </a>
			<a id="quest" href="#questSection"> QUIZ </a>
		</nav>
		
		<div id="homeSection" class="sectOne">
			<div class="sep-large">&nbsp;</div>
			
			<div id="home-info">
				<h1> ABOUT US </h1>
				<br /> <br />
				<h3>
					Além de bacharelandos em Sistemas de Informação da UPE, somos apaixonados por carros, pricipalmentes os classicos. Aproveitando o a necessidade de fazer um projeto de um site, para uma de nossas disciplinas, decidimos fazer um site que por meio de um questionário indicasse o carro classico que melhor lhe represente.
				</h3>
			</div>
		</div>
		
		<div id="carSection" class="sectTwo">
			<h1 class="sep">CLASSICS CARS</h1>
			<div class="sep-small">&nbsp; </div>
			
			<div id="sectTwoContent">
				<%
					for (Categoria categoria : quiz.getCategorias()) { // Costroí os cards-design
						out.print("<a href=\"categoria.do?cat=" + categoria.getId() + "\" target=\"_blank\">");
						out.print("<div class=\"container\"><div class=\"img\">");
						out.print("<img src=\"" + categoria.getThumb() + "\" width=\"400\" height=\"250\" />");
						out.print("</div><div class=\"desc\">" + categoria.getNome() + "</div></div></a>");
					}
				%>
			</div>
		</div>
		
		<div id="questSection" class="sectThree">
			<div class="sep">&nbsp; </div>
			<h1>REPONDA A ESSE PEQUENO QUIZ E DESCUBRA QUAL CARRO COMBINA COM VOCÊ</h1>
			<div class="sep-small">&nbsp; </div>
			
			<form method="POST" action="carro.do">
				<%
					for (QuizPergunta pergunta : quiz.getPerguntas()) {
						out.print("<div class=\"quiz\">");
						out.print("<h3>" + pergunta.getEnunciado().toUpperCase() + "</h3>");
						Collection<QuizResposta> respostas = pergunta.getRespostas();
						
						for (QuizResposta resposta : respostas) {
							out.print("<label><input type=\"radio\" name=\"" + pergunta.getId() + "\" value=\"" + resposta.getId() + "\" />");
							out.print(resposta.getDescricao() + "</label><br />");
						}
						out.print("</div>");
					}
				%>
				
				<input type="submit" id="enviar" value="Enviar Respostas" />
			</form>
		</div>
		
		<% } %>
		<script src="template/js/vendor/modernizr-2.7.1.min.js"></script>
		<script src="template/js/framework/jquery-3.1.1.min.js"></script>
		<script src="template/js/framework/anima.js"></script>
		<script src="template/js/jquery.min.js"></script>
		<script src="template/js/imagesloaded.js"></script>
		<script src="template/js/skrollr.js"></script>
		<script src="template/js/_main-exemplo.js"></script>
	</body>
</html>