<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dm.it.cloudapp.Contact"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


<link rel="stylesheet" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Derry's Contacts</title>
</head>

<body>
	<h1>Welcome to Derry's Contacts</h1>
	<h2>This is a cloud application project</h2>
	<h2>It Shows some of the concepts that are used for deploying an
		application using Spring, Maven and Cloudfoundry -- This data is just
		test data</h2>

	<div></div>
	<div></div>




	<div class="est">
		<h2>Create New Contact</h2>
		<form method="post">

			First Name: <input name="first"> SurName: <input name="sur"><br>
			Address Ln1: <input name="ln1"><br> Address Ln2: <input
				name="ln2"><br> Address Ln3: <input name="ln3"><br>
			Mobile No: <input name="mobile"><br> <input
				type="submit">

		</form>


	</div>

	<div class="ex">


		<div></div>
		<div></div>

		<h2>List of my contacts</h2>
		<c:forEach var="contact" items="${contacts}" varStatus="row">


			<div class="e">
				<form method="post">

					First Name: <input name="first" value="${contact.fname}">
					SurName: <input name="sur" value="${contact.sname}"><br>
					Address Ln1: <input name="ln1" value="${contact.addrLn1}"><br>
					Address Ln2: <input name="ln2" value="${contact.addrLn2}"><br>
					Address Ln3: <input name="ln3" value="${contact.addrLn3}"><br>
					Mobile No:<input name="mobile" value="${contact.phNo}"> <input
						name="_method" type="hidden" value="put"> <input
						name="contId" type="hidden" value="${contact.id}"> <input
						type="submit" value="Update">

				</form>
				<br> <br>
				<form method="post">
					<input name="_method" type="hidden" value="delete"> <input
						name="contId" type="hidden" value="${contact.id}"> <input
						type="submit" value="Delete">
				</form>
			</div>
		</c:forEach>
	</div>




</body>
</html>