<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ page import="dm.it.cloudapp.Contact" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="cont" class="dm.it.cloudapp.ContactsStore" scope="session"></jsp:useBean>
<head>


<link rel="stylesheet" href = "styles/style.css" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Derry's Contacts</title>
</head>

<body>
<h1>Contacts List model 2</h1>
<h2>List of my contacts</h2>
<c:choose>

<c:when test="${ !empty param.first||!empty param.sur}">

		
			hello
			</c:when>
			<c:otherwise>
		
			 	""
			</c:otherwise>

</c:choose>


<c:forEach items="${contacts}" var ="contact" varStatus ="row">


${contact.fname}
${contact.sname}


${contact.addrLn1}
${contact.addrLn2}
${contact.addrLn3}
${contact.phNo}

		<form method="post">
			<input name="_method" type="hidden" value="put"> 
			<input name="contId" type="hidden" value="${row.count}"> 
			<input type="submit" value="Update">
		</form>
		<form method="post">
			<input name="_method" type="hidden" value="delete"> 
			<input name="contId" type="hidden" value="${row.count}"> 
			<input type="submit" value="Delete">
		</form>

</c:forEach>


<h2>Create New Contact</h2>
<form method="post" >


First Name:<input name="first">
SurName:<input name="sur">

Address Ln1:<input name="ln1">
Address Ln2:<input name="ln2">
Address Ln3:<input name="ln3">

Mobile No:<input name="mobile">


<input type ="submit">

</form>





</body>
</html>