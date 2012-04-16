<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ page import="dm.it.cloudapp.Contact" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:useBean id="cont" class="dm.it.cloudapp.ContactsStore" scope="application"></jsp:useBean>
<head>
<link rel="stylesheet" href = "styles/style.css" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Derry's Contacts</title>
</head>

<body>
<h1>Contacts List</h1>
<h2>List of my contacts</h2>




<c:if test="${ !empty param.first||!empty param.sur}">
<% 
 Contact contact = new Contact();
 contact.setFname(request.getParameter("first"));
 contact.setSname(request.getParameter("sur"));
 contact.setAddrLn1(request.getParameter("ln1"));
 contact.setAddrLn2(request.getParameter("ln2"));
 contact.setAddrLn3(request.getParameter("ln3"));
 contact.setPhNo(request.getParameter("mobile"));
 cont.addContacts(contact);

%>
</c:if>



<c:forEach items="${cont.contacts}" var ="contact">


${contact.fname}
${contact.sname}


${contact.addrLn1}
${contact.addrLn2}
${contact.addrLn3}
${contact.phNo}

</c:forEach>


<h2>Create New Contact</h2>
<form>


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