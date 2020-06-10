package com.gestiunemagazin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestiunemagazin.dao.ClientDAO;
import com.gestiunemagazin.model.Client;
import com.gestiunemagazin.util.DbUtil;



public class ClientDAOImpl implements ClientDAO {

	private Connection connection;

	
	public ClientDAOImpl() {
		connection = DbUtil.getConnection();
	}

	
	@Override
	public void adaugaClient(Client client) {
		String sql = "insert into client (nume_cl, prenume_cl, cnp, adresa, telefon, email) values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, client.getNume());
			pstmt.setString(2, client.getPrenume());
			pstmt.setString(3, client.getCnp());
			pstmt.setString(4, client.getAdresa());
			pstmt.setString(5, client.getTelefon());
			pstmt.setString(6, client.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void stergeClient(int idClient) {
		String sql = "delete from client where id_client = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idClient);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateClient(Client client) {
		StringBuilder sql = new StringBuilder("update client set");
		sql.append(" nume_cl = ?,");
		sql.append(" prenume_cl = ?,");
		sql.append(" cnp = ?,");
		sql.append(" adresa = ?,");
		sql.append(" telefon = ?,");
		sql.append(" email = ?");
		sql.append(" where id_client = ?");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, client.getNume());
			pstmt.setString(2, client.getPrenume());
			pstmt.setString(3, client.getCnp());
			pstmt.setString(4, client.getAdresa());
			pstmt.setString(5, client.getTelefon());
			pstmt.setString(6, client.getEmail());
			pstmt.setInt(7, client.getIdClient());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Client> getAllClienti() {
		ArrayList<Client> clienti = new ArrayList<>();
		String sql = "select * from client";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Client client = new Client();
					client.setIdClient(rs.getInt("id_client"));
					client.setNume(rs.getString("nume_cl"));
					client.setPrenume(rs.getString("prenume_cl"));
					client.setCnp(rs.getString("cnp"));
					client.setAdresa(rs.getString("adresa"));
					client.setTelefon(rs.getString("telefon"));
					client.setEmail(rs.getString("email"));

					clienti.add(client);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clienti;
	}

	
	@Override
	public Client getClientById(int idClient) {
		Client client = new Client();
		String sql = "select * from client where id_client = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idClient);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				client.setIdClient(rs.getInt("id_client"));
				client.setNume(rs.getString("nume_cl"));
				client.setPrenume(rs.getString("prenume_cl"));
				client.setCnp(rs.getString("cnp"));
				client.setAdresa(rs.getString("adresa"));
				client.setTelefon(rs.getString("telefon"));
				client.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	
	@Override
	public void verificaClient(Client client) {
		try {
			String sql = "select * from client where id_client = ?";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, client.getIdClient());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				updateClient(client);
			} else {
				adaugaClient(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Client> getAllClientiByNumeOrPren(String nume) {
		ArrayList<Client> clienti = new ArrayList<>();
		String sql = "select * from client where lower(nume_cl) like ? or lower(prenume_cl) like ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + nume + "%");
			ps.setString(2, "%" + nume + "%");
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Client client = new Client();
					client.setIdClient(rs.getInt("id_client"));
					client.setNume(rs.getString("nume_cl"));
					client.setPrenume(rs.getString("prenume_cl"));
					client.setCnp(rs.getString("cnp"));
					client.setAdresa(rs.getString("adresa"));
					client.setTelefon(rs.getString("telefon"));
					client.setEmail(rs.getString("email"));

					clienti.add(client);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clienti;
	}
}
