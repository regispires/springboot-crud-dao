package dsweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dsweb.model.Contato;

@Repository
public class ContatoJdbcDao implements ContatoDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Contato> rowMapper = new RowMapper<Contato>() {
		@Override
		public Contato mapRow(ResultSet rs, int rowNum) throws SQLException {
			Contato contato = new Contato(rs.getInt("id"), rs.getString("nome"), 
					rs.getString("email"), rs.getString("endereco"));
			return contato;
		}
	};
	
	@Override
	@Transactional(readOnly=true)
	public List<Contato> getLista() {
		String sql = "select * from contatos";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	@Transactional(readOnly=true)
	public Contato getContato(Integer id) {
		String sql = "select * from contatos where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
	}
	
	@Override
	@Transactional
	public void adiciona(Contato contato) {
		String sql = "insert into contatos " + 
				"(nome, email, endereco)" + 
				" values (?, ?, ?)";
		jdbcTemplate.update(sql, 
				new Object[] { contato.getNome(), contato.getEmail(), 
						contato.getEndereco()});
	}

	@Override
	@Transactional
	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?," + "endereco=? where id=?";
		jdbcTemplate.update(sql, 
				new Object[] { contato.getNome(), contato.getEmail(), 
						contato.getEndereco(), contato.getId()});
	}

	@Override
	@Transactional
	public void remove(Contato contato) {
		String sql = "delete from contatos where id=?";
		jdbcTemplate.update(sql, new Object[] { contato.getId()});
	}

}
