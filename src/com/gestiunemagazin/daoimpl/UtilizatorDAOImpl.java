package com.gestiunemagazin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestiunemagazin.dao.UtilizatorDAO;
import com.gestiunemagazin.model.Utilizator;
import com.gestiunemagazin.util.DbUtil;



public class UtilizatorDAOImpl implements UtilizatorDAO {

	private Connection connection;

	
	public UtilizatorDAOImpl() {
		connection = DbUtil.getConnection();
	}

	
	@Override
	public void adaugaUtilizator(Utilizator utilizator) {
		String sql = "insert into utilizator (email, parola, tip_utilizator) values (?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, utilizator.getEmail());
			pstmt.setString(2, utilizator.getParola());
			pstmt.setInt(3, utilizator.getTipUtilizator());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void stergeUtilizator(int idUtilizator) {
		String sql = "delete from utilizator where id_utilizator = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idUtilizator);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateUtilizator(Utilizator utilizator) {
		StringBuilder sql = new StringBuilder("update utilizator set");
		sql.append(" email = ?,");
		sql.append(" parola = ?,");
		sql.append(" tip_utilizator = ?");
		sql.append(" where id_utilizator = ?");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, utilizator.getEmail());
			pstmt.setString(2, utilizator.getParola());
			pstmt.setInt(3, utilizator.getTipUtilizator());
			pstmt.setInt(4, utilizator.getIdUtilizator());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Utilizator> getAllUtilizatori() {
		ArrayList<Utilizator> utilizatori = new ArrayList<>();
		String sql = "select * from utilizator";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Utilizator utilizator = new Utilizator();
					utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
					utilizator.setEmail(rs.getString("email"));
					utilizator.setParola(rs.getString("parola"));
					utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));

					utilizatori.add(utilizator);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizatori;
	}

	
	@Override
	public Utilizator getUtilizatorById(int idUtilizator) {
		Utilizator utilizator = new Utilizator();
		String sql = "select * from utilizator where id_utilizator = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idUtilizator);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
				utilizator.setEmail(rs.getString("email"));
				utilizator.setParola(rs.getString("parola"));
				utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizator;
	}

	
	@Override
	public void verificaUtilizator(Utilizator utilizator) {
		try {
			String sql = "select * from utilizator where id_utilizator = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, utilizator.getIdUtilizator());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				updateUtilizator(utilizator);
			} else {
				adaugaUtilizator(utilizator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public Utilizator getUtilizatorByEmailAndPass(String email, String parola) {
		Utilizator utilizator = null;
		try {
			String sql = "select * from utilizator where email = ? and parola = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, parola);
			ResultSet rs = ps.executeQuery();

			if (rs != null && rs.next()) {
				utilizator = new Utilizator();
				utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
				utilizator.setEmail(rs.getString("email"));
				utilizator.setParola(rs.getString("parola"));
				utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizator;
	}

	
	@Override
	public Utilizator getUtilizatorByEmail(String email) {
		Utilizator utilizator = null;
		try {
			String sql = "select * from utilizator where email = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs != null && rs.next()) {
				utilizator = new Utilizator();
				utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
				utilizator.setEmail(rs.getString("email"));
				utilizator.setParola(rs.getString("parola"));
				utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizator;
	}

	
	@Override
	public ArrayList<Utilizator> getAllUtilizatoriByEmail(String email) {
		ArrayList<Utilizator> utilizatori = new ArrayList<>();
		try {
			String sql = "select * from utilizator where lower(email) like ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + email + "%");
			ResultSet rs = ps.executeQuery();

			while (rs != null && rs.next()) {
				Utilizator utilizator = new Utilizator();
				utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
				utilizator.setEmail(rs.getString("email"));
				utilizator.setParola(rs.getString("parola"));
				utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));

				utilizatori.add(utilizator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizatori;
	}
	public ArrayList<Utilizator> getAllUtilizatoriByTipUser(String tipUser) {
		ArrayList<Utilizator> utilizatori = new ArrayList<>();
		try {
			String sql = "select * from utilizator where tip_utilizator like ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + tipUser + "%");
			ResultSet rs = ps.executeQuery();

			while (rs != null && rs.next()) {
				Utilizator utilizator = new Utilizator();
				utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
				utilizator.setEmail(rs.getString("email"));
				utilizator.setParola(rs.getString("parola"));
				utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));

				utilizatori.add(utilizator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizatori;
	}
	public ArrayList<Utilizator> getAllUtilizatoriByEmailAndTipUser(String email,String tipUser) {
		ArrayList<Utilizator> utilizatori = new ArrayList<>();
		try {
			String sql = "select * from utilizator where lower(email) like ? and tip_utilizator like ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + email + "%");
			ps.setString(2, "%" + tipUser + "%");
			ResultSet rs = ps.executeQuery();

			while (rs != null && rs.next()) {
				Utilizator utilizator = new Utilizator();
				utilizator.setIdUtilizator(rs.getInt("id_utilizator"));
				utilizator.setEmail(rs.getString("email"));
				utilizator.setParola(rs.getString("parola"));
				utilizator.setTipUtilizator(rs.getInt("tip_utilizator"));

				utilizatori.add(utilizator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizatori;
	}
}
