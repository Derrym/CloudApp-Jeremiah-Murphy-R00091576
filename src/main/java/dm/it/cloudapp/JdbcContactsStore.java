package dm.it.cloudapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcContactsStore {
	private JdbcTemplate jdbcTemplate;

	public JdbcContactsStore(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcContactsStore(){
		
	}

	public void save(Contact contact) {
		jdbcTemplate
				.update("insert into CONTACT (first, sur, ln1,ln2,ln3,mobile) values(?,?,?,?,?,?)",
						contact.getFname(), contact.getSname(),
						contact.getAddrLn1(), contact.getAddrLn2(),
						contact.getAddrLn3(), contact.getPhNo());
	}

	public Contact get(int id) {
		return jdbcTemplate
				.queryForObject(
						"select id, first, sur, ln1,ln2,ln3,mobile from CONTACT where id=?",
						new ContactMapper(), id);
	}

	public List<Contact> getAll() {
		return jdbcTemplate.query(
				"select id, first, sur, ln1,ln2,ln3,mobile from CONTACT",
				new ContactMapper());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from CONTACT where id=?", id);
	}

	public void update(Contact contact, int id) {
		this.jdbcTemplate
				.update("update CONTACT set first =?, sur=?,ln1=?,ln2=?,ln3=?,mobile=?  where id=?",
						contact.getFname(), contact.getSname(),
						contact.getAddrLn1(), contact.getAddrLn2(),
						contact.getAddrLn3(), contact.getPhNo(), id);
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
