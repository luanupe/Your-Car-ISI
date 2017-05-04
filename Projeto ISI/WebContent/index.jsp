<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="car.your.model.Quiz" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="template/css/login.css">
		<title>Your Classic &middot; Login</title>
	</head>
	
	<body>
		<%
			if ((session.getAttribute("quiz") == null)) {
				Quiz quiz = new Quiz();
				session.setAttribute("quiz", quiz);
			}
		%>
		
		<div class="content">
			<h1> YOUR CLASSIC </h1>
			<p>Descubra qual carro classico tem mais a ver com você. Faça um pequeno questionario e descubra!</p>
			<a href="main.jsp">Acessar com o Facebook</a>
		</div>
		
		<video autoplay muted loop id="video" class="video">
			<source src="template/videos/CGI_2015_Dodge_Challenger_SRT.mp4" type="video/mp4">
			<source src="template/videos/CGI_2015_Dodge_Challenger_SRT.ovg" type="video/ogg">
			<source src="template/videos/CGI_2015_Dodge_Challenger_SRT.webm" type="video/webm">
		</video>
	</body>
</html>
