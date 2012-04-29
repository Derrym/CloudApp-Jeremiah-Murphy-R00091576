package dm.it.cloudapp.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dm.it.cloudapp.Contact;
import dm.it.cloudapp.ContactsStore;
import dm.it.cloudapp.JdbcContactsStore;

@RequestMapping("contacts")
@Controller
public class ContactsController {

	@Autowired
	private JdbcContactsStore contstore;

	@RequestMapping(method = RequestMethod.GET)
	public void listContacts(Model model) {
		model.addAttribute("contacts", contstore.getAll());

	}

	@RequestMapping(method = RequestMethod.POST)
	public void createContact(Model model, @RequestParam String first,
			String sur, String ln1, String ln2, String ln3, String mobile) {
		Contact contact = new Contact();
		contact.setFname(first);
		contact.setSname(sur);
		contact.setAddrLn1(ln1);
		contact.setAddrLn2(ln2);
		contact.setAddrLn3(ln3);
		contact.setPhNo(mobile);
		contstore.save(contact);
		model.addAttribute("contacts", contstore.getAll());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteContact(Model model, @RequestParam int contId) {

		contstore.delete(contId);

		model.addAttribute("contacts", contstore.getAll());

	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateContact(Model model, @RequestParam int contId,
			String first, String sur, String ln1, String ln2, String ln3,
			String mobile) {

		Contact contact = new Contact();

		contact.setFname(first);
		contact.setSname(sur);
		contact.setAddrLn1(ln1);
		contact.setAddrLn2(ln2);
		contact.setAddrLn3(ln3);
		contact.setPhNo(mobile);

		contstore.update(contact, contId);
		model.addAttribute("contacts", contstore.getAll());
	}

}
