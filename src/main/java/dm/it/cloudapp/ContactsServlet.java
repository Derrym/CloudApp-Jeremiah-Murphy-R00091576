package dm.it.cloudapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ContactsServlet extends HttpServlet {
   protected void doGet(HttpServletRequest req,HttpServletResponse resp)
    throws ServletException, IOException{
    		
    		ContactsStore constore = getContacts(req);
    		req.setAttribute("contacts", constore.getContacts());
    		doForward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	ContactsStore constore = getContacts(req);
	Contact contact = new Contact();
	 contact.setFname(req.getParameter("first"));
	 contact.setSname(req.getParameter("sur"));
	 contact.setAddrLn1(req.getParameter("ln1"));
	 contact.setAddrLn2(req.getParameter("ln2"));
	 contact.setAddrLn3(req.getParameter("ln3"));
	 contact.setPhNo(req.getParameter("mobile"));

	 constore.addContacts(contact);
req.setAttribute("contacts", constore.getContacts());

doForward(req, resp);
}

private void doForward(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	RequestDispatcher rd =getServletContext().getRequestDispatcher("/main.jsp");
	

	rd.forward(req, resp);
}

private ContactsStore getContacts(HttpServletRequest req) {
	HttpSession session = req.getSession(true);
	ContactsStore cont = (ContactsStore)session.getAttribute("cont");
	if (cont==null){
		cont = new ContactsStore();
		session.setAttribute("cont", cont);
	}

	return cont;
}	
    	
    
   
   
   
}
