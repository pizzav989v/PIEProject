package com.gestiunemagazin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestiunemagazin.dao.ProdusDAO;
import com.gestiunemagazin.model.Produs;
import com.gestiunemagazin.util.DbUtil;



public class ProdusDAOImpl implements ProdusDAO {

	private Connection connection;

	
	public ProdusDAOImpl() {
		connection = DbUtil.getConnection();
	}

	
	@Override
	public void adaugaProdus(Produs produs) {
		String sql = "insert into produs(denumire, pret, stoc, descriere) values (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, produs.getDenumire());
			pstmt.setDouble(2, produs.getPret());
			pstmt.setInt(3, produs.getStoc());
			pstmt.setString(4, produs.getDescriere());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void stergeProdus(int idProdus) {
		String sql = "delete from Produs where id_produs = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idProdus);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateProdus(Produs produs) {
		StringBuilder sql = new StringBuilder("update produs set");
		sql.append(" denumire = ?,");
		sql.append(" pret = ?,");
		sql.append(" stoc = ?,");
		sql.append(" descriere = ?");
		sql.append(" where id_produs = ?");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, produs.getDenumire());
			pstmt.setDouble(2, produs.getPret());
			pstmt.setInt(3, produs.getStoc());
			pstmt.setString(4, produs.getDescriere());
			pstmt.setInt(5, produs.getIdProdus());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Produs> getAllProduse() {
		ArrayList<Produs> Produse = new ArrayList<>();
		String sql = "select * from produs";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Produs produs = new Produs();
					produs.setIdProdus(rs.getInt("id_produs"));
					produs.setDenumire(rs.getString("denumire"));
					produs.setPret(rs.getDouble("pret"));
					produs.setStoc(rs.getInt("stoc"));
					produs.setDescriere(rs.getString("descriere"));

					Produse.add(produs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Produse;
	}

	
	@Override
	public Produs getProdusById(int idProdus) {
		Produs produs = new Produs();
		String sql = "select * from Produs where id_Produs = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idProdus);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				produs.setIdProdus(rs.getInt("id_produs"));
				produs.setDenumire(rs.getString("denumire"));
				produs.setPret(rs.getDouble("pret"));
				produs.setStoc(rs.getInt("stoc"));
				produs.setDescriere(rs.getString("descriere"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produs;
	}

	
	@Override
	public void verificaProdus(Produs produs) {
		try {
			String sql = "select * from Produs where id_produs = ?";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, produs.getIdProdus());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				updateProdus(produs);
			} else {
				adaugaProdus(produs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Produs> getAllProduseByDenumire(String denumire) {
		ArrayList<Produs> Produse = new ArrayList<>();
		String sql = "select * from Produs where lower(denumire) like ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%" + denumire + "%");
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Produs produs = new Produs();
					produs.setIdProdus(rs.getInt("id_produs"));
					produs.setDenumire(rs.getString("denumire"));
					produs.setPret(rs.getDouble("pret"));
					produs.setStoc(rs.getInt("stoc"));
					produs.setDescriere(rs.getString("descriere"));

					Produse.add(produs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Produse;
	}
}
