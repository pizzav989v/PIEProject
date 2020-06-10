package com.gestiunemagazin.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestiunemagazin.dao.FacturaDAO;
import com.gestiunemagazin.model.Factura;
import com.gestiunemagazin.util.DbUtil;



public class FacturaDAOImpl implements FacturaDAO {

	private Connection connection;

	
	public FacturaDAOImpl() {
		connection = DbUtil.getConnection();
	}

	
	@Override
	public void adaugaFactura(Factura factura) {
		String sql = "insert into factura(valoare, id_angajat, id_client, data) values (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setDouble(1, factura.getValoare());
			pstmt.setInt(2, factura.getIdAngajat());
			pstmt.setInt(3, factura.getIdClient());
			pstmt.setDate(4, new Date(factura.getData().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void stergeFactura(int idFactura) {
		String sql = "delete from factura where id_factura = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idFactura);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateFactura(Factura factura) {
		StringBuilder sql = new StringBuilder("update factura set");
		sql.append(" valoare = ?,");
		sql.append(" id_angajat = ?,");
		sql.append(" id_client = ?,");
		sql.append(" data = ?");
		sql.append(" where id_factura = ?");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setDouble(1, factura.getValoare());
			pstmt.setInt(2, factura.getIdAngajat());
			pstmt.setInt(3, factura.getIdClient());
			pstmt.setDate(4, new Date(factura.getData().getTime()));
			pstmt.setInt(5, factura.getIdFactura());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Factura> getAllFacturi() {
		ArrayList<Factura> facturi = new ArrayList<>();
		String sql = "select * from factura";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Factura factura = new Factura();
					factura.setIdFactura(rs.getInt("id_factura"));
					factura.setValoare(rs.getDouble("valoare"));
					factura.setIdAngajat(rs.getInt("id_angajat"));
					factura.setIdClient(rs.getInt("id_client"));
					factura.setData(rs.getDate("data"));

					facturi.add(factura);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facturi;
	}

	
	@Override
	public Factura getFacturaById(int idFactura) {
		Factura factura = new Factura();
		String sql = "select * from factura where id_factura = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idFactura);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				factura.setIdFactura(rs.getInt("id_factura"));
				factura.setValoare(rs.getDouble("valoare"));
				factura.setIdAngajat(rs.getInt("id_angajat"));
				factura.setIdClient(rs.getInt("id_client"));
				factura.setData(rs.getDate("data"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return factura;
	}

	
	@Override
	public void verificaFactura(Factura factura) {
		try {
			String sql = "select * from factura where id_factura = ?";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, factura.getIdFactura());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				updateFactura(factura);
			} else {
				adaugaFactura(factura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Factura> getAllFacturiByNumeOrPren(String numeAng) {
		ArrayList<Factura> facturi = new ArrayList<>();
		String sql = "select * from factura where id_angajat in (select id_angajat from angajat where lower(nume) like ? or lower(prenume) like ? )";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + numeAng + "%");
			ps.setString(2, "%" + numeAng + "%");
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Factura factura = new Factura();
					factura.setIdFactura(rs.getInt("id_factura"));
					factura.setValoare(rs.getDouble("valoare"));
					factura.setIdAngajat(rs.getInt("id_angajat"));
					factura.setIdClient(rs.getInt("id_client"));
					factura.setData(rs.getDate("data"));

					facturi.add(factura);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facturi;
	}
}
