<%@page import="it.gestioneautorelibri.dao.AutoreDAO"%>
<%@page import="it.gestioneautorelibri.model.Autore"%>
<%@page import="it.gestioneautorelibri.model.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body background="java.jpg">
<h1 align="center">Visualizza Dati Database</h1>
<table border="5" align="center">
		<thead>
			<tr>
				<td>Titolo Libro</td>
				<td>Nome Autore</td>
				<td>Cognome Autore</td>
				<td>Azione</td>
			</tr>
		</thead>
			<%
			
			List <Libro> listaFormStampa=(List <Libro>) request.getAttribute("listaLibroAttributeName"); 
			for(Libro temp : listaFormStampa){
		

			%>
			<tr>
				<td><%=temp.getTitolo()%></td>
				<td><%=temp.getAutore().getNome()%></td>
				<td><%=temp.getAutore().getCognome()%></td>
				<td><a href="ExecuteDettagliLibroServlet?idDelLibroDaInviareComeParametro=<%=temp.getId_Libro()%>">Dettaglio</a> <a href="ModifyLibroServlet?idDelLibroDaInviareComeParametro=<%=temp.getId_Libro()%>">Modifica</a></td>
			
			</tr>
			<%} %>
			<td>
			<form action="CreateLibroServlet" method="post">
				<input type="submit" value="Inserisci Nuovo Libro">
			</form>
	</td>
		</thead>
	</table>
	<h2><a href="homePage.jsp">Torna indietro</a></h2>

	
</body>

</html>