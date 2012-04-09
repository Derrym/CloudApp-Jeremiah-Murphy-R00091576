package dm.it.cloudapp;

import  java.util.ArrayList;
import  java.util.List;

public class ContactsStore {
	private List<Contact> contacts = new ArrayList <Contact> ();

	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void addContacts(Contact contact)
	{
		contacts.add(contact);
	}
	
	
	
	
	
	

}
