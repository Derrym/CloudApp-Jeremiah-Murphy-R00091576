package dm.it.cloudapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_USER")
public class JdbcContactsStore {
	private JdbcTemplate jdbcTemplate;

	public JdbcContactsStore(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcContactsStore(){
		
	}

	public void save(Contact contact) {
		jdbcTemplate
				.update("insert into CONTACT (first, sur, ln1,ln2,ln3,mobile,owner)  values(?,?,?,?,?,?,?)",
						contact.getFname(), contact.getSname(),
						contact.getAddrLn1(), contact.getAddrLn2(),
						contact.getAddrLn3(), contact.getPhNo(), getCurrentUser());
	}

	public Contact get(int id) {
		return jdbcTemplate
				.queryForObject(
						"select id, first, sur, ln1,ln2,ln3,mobile from CONTACT where id=?and owner=?", 
						new ContactMapper(), id,getCurrentUser());
	}

	public List<Contact> getAll() {
		return jdbcTemplate.query(
				"select id, first, sur, ln1,ln2,ln3,mobile from CONTACT where owner=?",
				new ContactMapper(),getCurrentUser());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from CONTACT where id=? and owner=?",  id,getCurrentUser());
	}
	public void update(Contact contact, int id) {
		this.jdbcTemplate
				.update("update CONTACT set first =?, sur=?,ln1=?,ln2=?,ln3=?,mobile=?  where id=?and owner=?",
						contact.getFname(), contact.getSname(),
						contact.getAddrLn1(), contact.getAddrLn2(),
						contact.getAddrLn3(), contact.getPhNo(), id,getCurrentUser());
	}
	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}

class ContactMapper implements RowMapper<Contact> {

	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getInt("id"));
		contact.setFname(rs.getString("first"));
		contact.setSname(rs.getString("sur"));
		contact.setAddrLn1(rs.getString("ln1"));
		contact.setAddrLn2(rs.getString("ln2"));
		contact.setAddrLn3(rs.getString("ln3"));
		contact.setPhNo(rs.getString("mobile"));

		return contact;
	}

}
