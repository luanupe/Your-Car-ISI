<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="car.your.model.Quiz" %>
<%@ page import="car.your.model.database.Carro" %>

<html>
	<%
		Carro carro = (Carro) request.getAttribute("ideal");
	%>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="template/css/principal.css"/>
		<title>Eu tirei <%= carro.getNome() %> e você?</title>
		
		<meta property="og:url"           content="http://localhost:8080/Projeto_ISI/" />
		<meta property="og:type"          content="website" />
		<meta property="og:title"         content="Eu tirei <%= carro.getNome() %> e você?" />
		<meta property="og:description"   content="<%= carro.getDescricao().substring(0, 100) %>" />
		<meta property="og:image"         content="<%= carro.getThumb() %>" />
  
		  <div id="fb-root"></div>
		  <script>(function(d, s, id) {
		    var js, fjs = d.getElementsByTagName(s)[0];
		    if (d.getElementById(id)) return;
		    js = d.createElement(s); js.id = id;
		    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8";
		    fjs.parentNode.insertBefore(js, fjs);
		  }(document, 'script', 'facebook-jssdk'));</script>
		
		<style>
			.background {
				background-image: url(<%= carro.getThumb() %>);
				background-size: cover;
				background-repeat: no-repeat;
				background-position: right top;
				background-attachment: fixed;
			}
		</style>
	</head>
	
	<body class="background">
		<div class="sep">&nbsp;</div>
		
		<div id="home-info">
			<p><a href="./" id="enviar" style="padding-top:15px;">Faça seu teste</a></p>
			<div class="sep">&nbsp;</div><br />
			
			<h1><%= carro.getNome() %></h1>
			<div class="sep-small">&nbsp;</div>
			<h3><%= carro.getDescricao() %></h3>
			
			<div class="fb-share-button" 
    			data-href="http://www.yourcar.cf:8080/Projeto ISI/carro.do?id=<%= carro.getId() %>" 
    			data-layout="button_count">
  			</div>
		</div>
	</body>
</html>