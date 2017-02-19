<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Autore</title>

<script>

function scriptDiControllo(){
	var nomeAutore=document.getElementById("nomeControl").value;
	var cognomeAutore=document.getElementById("cognomeControl").value;
	var etaAutore=document.getElementById("etaControl").value;
	var casaEditriceAutore=document.getElementById("casaEditriceControl").value;
	var partitaIvaAutore=document.getElementById("partitaIvaControl").value;
	
	if(nomeAutore==""){
		alert("Non hai inserito il nome");
		return false;
		
	}
	if(cognomeAutore==""){
		alert("Non hai inserito il cognome");
		return false;
		
	}
	if(etaAutore==""){
		alert("Non hai inserito l'età");
		return false;
		
	}
	if(casaEditriceAutore==""){
		alert("Non hai inserito la Casa Editrice");
		return false;
		
	}
	if(partitaIvaAutore==""){
		alert("Non hai inserito la PartitaIva");
		return false;
		
	}
}


</script>





</head>
<body background="java.jpg">

	<h1 align="center"> Inserisci l'Autore da Aggiungere </h1>
	
	<form action="ExecuteCreateAutoreServlet" method="post">
	
	<h2>
		Nome:
		<p>
		<input type="text" id="nomeControl" name="nomeInputCreateForm">
		</p>
		<p>
		Cognome:
		</p>
		<p>
		<input type="text" id="cognomeControl" name="cognomeInputCreateForm">
		</p>
		<p>
		Eta':
		</p>
		<p>
		<input type="text" id="etaControl" name="etaInputCreateForm">
		</p>
		<p>
		Casa Editrice:
		</p>
		<p>
		<input type="text" id="casaEditriceControl" name="casaEditriceInputCreateForm"> 
		</p>
		<p>
		PartitaIva :
		</p>
		<p>
		<input type="text" id="partitaIvaControl" name="partitaIvaInputCreateForm"> 

		<input type="submit" value="Aggiungi Autore" onClick="return scriptDiControllo();" />
	</h2>
		
	</form>
	<a href="homePage.jsp">Torna indietro</a>
	
	

</body>
</html>