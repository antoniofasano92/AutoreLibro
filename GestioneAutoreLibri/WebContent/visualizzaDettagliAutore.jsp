<%@page import="java.util.ArrayList"%>
<%@page import="it.gestioneautorelibri.model.Libro"%>
<%@page import="java.util.List"%>
<%@page import="it.gestioneautorelibri.model.Autore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="java.jpg">
<center>

<h1 align="center">Dettagli Autore</h1>
	<% Autore autoreStampa= (Autore) request.getAttribute("idAutoreAttributeName");%>

<table border="5" align="center">	
	<thead>
			<tr>
				<td><h2>ID</h2></td>
				<td><h2>Nome</h2></td>
				<td><h2>Cognome</h2></td>
				<td><h2>Eta</h2></td>
				<td><h2>Casa Editrice</h2></td>
				<td><h2>PartitaIva</h2></td>
				
			</tr>
			<tr>
				<td><h2><%=autoreStampa.getId_Autore()%></h2></td>
				<td><h2><%=autoreStampa.getNome()%></h2></td>
				<td><h2><%=autoreStampa.getCognome()%></h2></td>
				<td><h2><%=autoreStampa.getEta()%></h2></td>
				<td><h2><%=autoreStampa.getCasaEditrice()%></h2></td>
				<td><h2><%=autoreStampa.getPartitaIva()%></h2></td>
			</tr>
			
		</thead>
	</table>
	
	
<h1><strong>Libri collegati</strong></h1>	
	
	
<table border="5" align="center">		
	<thead>
			<tr>
				<td><h2>ID</h2></td>
				<td><h2>Titolo</h2></td>
				<td><h2>Num.Pagine</h2></td>
				<td><h2>Descrizione</h2></td>
				<td><h2>Genere</h2></td>
				<td><h2>Azione</h2></td>
			</tr>
			<%
			List <Libro> listaLibri= new ArrayList <Libro>();
			listaLibri=autoreStampa.getListaLibri(); 
			for(Libro temp : listaLibri){%>
			<tr>
				<td><h2><%=temp.getId_Libro()%></h2></td>
				<td><h2><%=temp.getTitolo()%></h2></td>
				<td><h2><%=temp.getnPagine()%></h2></td>
				<td><h2><%=temp.getDescrizione()%></h2></td>
				<td><h2><%=temp.getGenere()%></h2></td>
				<td><h2><a href="ExecuteDettagliLibroServlet?idDelLibroDaInviareComeParametro=<%=temp.getId_Libro()%>">Dettaglio</a> <a href="ModifyLibroServlet?idDelLibroDaInviareComeParametro=<%=temp.getId_Libro()%>">Modifica</a></h2></td>
			</tr>
			<%} %>
			
		</thead>
	</table>
	
<form action="ExecuteDeleteAutoreLibriServlet" method="post">	
	<input type="submit" value="Cancella Autore e Libri Collegati">	
	<input type="hidden" name="idDaInviareComeParametro" value="<%=autoreStampa.getId_Autore()%>"/> 
</form>

<h2><a href="ricercaAutori.jsp">Torna indietro</a></h2>
	
</center>	
</body>
</html>