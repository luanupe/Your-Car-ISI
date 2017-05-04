<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="car.your.model.database.*" %>
<%@ page import="java.util.Collection" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Your Classic &middot; Info</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <link rel="stylesheet" href="template/css/normalize.css">
        <link rel="stylesheet" href="template/css/main.css">
	</head>
	<body class="loading">
		
		<%
			if ((request.getAttribute("error") != null)) {
				out.println((String) request.getAttribute("error"));
			} else if ((request.getAttribute("carros") != null)) {
				Collection<Carro> carros = (Collection<Carro>) request.getAttribute("carros");
				for (Carro carro : carros) {
					%>
					<section id="slide-<%= carro.getId() %>" class="homeSlide">
					<div class="bcg"
						data-center="background-position: 50% 0px;"
						data-top-bottom="background-position: 50% -100px;"
						>
						<div class="hsContainer">
							<div class="hsContent"
								data-center="background-position: 50% 0px;"
								data-bottom-top="background-position: 50% 100px;"
								>
								
								<h2><%= carro.getNome() %></h2>
								<p><%= carro.getDescricao() %></p>
							</div>
						</div>
					</div>
				</section>
					
					<%
				}
			}
		%>
		
        <script src="template/js/vendor/modernizr-2.7.1.min.js"></script>
		<script src="template/js/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="template/js/imagesloaded.js"></script>
        <script src="template/js/skrollr.js"></script>
        <script src="template/js/_main-exemplo.js"></script>
	</body>
</html>