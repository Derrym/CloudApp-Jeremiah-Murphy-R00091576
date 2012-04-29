package dm.it.cloudapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContactsServlet extends HttpServlet {

	private WebApplicationContext ctx;

	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ContactsStore constore = getContacts(req);
		req.setAttribute("contacts", constore.getContacts());
		doForward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ContactsStore constore = getContacts(req);
		constore.addContacts(insertContact(req));
		req.setAttribute("contacts", constore.getContacts());

		doForward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("contId"));
		ContactsStore constore = getContacts(req);

		req.setAttribute("contacts", constore.getContacts());
		doForward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("contId"));
		ContactsStore constore = getContacts(req);
		if (index > 1) {
			constore.getContacts().remove(index - 1);
		}
		if (index == 1) {
			constore.getContacts().clear();
		}
		if (constore != null)
			req.setAttribute("contacts", constore.getContacts());
		else
			req.setAttribute("contacts", null);
		doForward(req, resp);
	}

	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/WEB-INF/views/contacts.jsp");

		rd.forward(req, resp);
	}

	private ContactsStore getContacts(HttpServletRequest req) {
		return ctx.getBean(ContactsStore.class);
	}

	private Contact insertContact(HttpServletRequest req) {
		Contact contact = new Contact();
		contact.setFname(req.getParameter("first"));
		contact.setSname(req.getParameter("sur"));
		contact.setAddrLn1(req.getParameter("ln1"));
		contact.setAddrLn2(req.getParameter("ln2"));
		contact.setAddrLn3(req.getParameter("ln3"));
		contact.setPhNo(req.getParameter("mobile"));

		return contact;
	}

}
