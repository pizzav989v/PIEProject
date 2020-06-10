package com.gestiunemagazin.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestiunemagazin.dao.AngajatDAO;
import com.gestiunemagazin.model.Angajat;
import com.gestiunemagazin.util.DbUtil;



public class AngajatDAOImpl implements AngajatDAO {

	private Connection connection;

	
	public AngajatDAOImpl() {
		connection = DbUtil.getConnection();
	}

	
	@Override
	public void adaugaAngajat(Angajat angajat) {
		String sql = "insert into angajat (nume, prenume, cnp, telefon, salariu, data_angajarii, functie, id_departament, email) values (?, ?, ?, ?, ?, ?, ?, ? ,?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, angajat.getNume());
			pstmt.setString(2, angajat.getPrenume());
			pstmt.setString(3, angajat.getCnp());
			pstmt.setString(4, angajat.getTelefon());
			pstmt.setDouble(5, angajat.getSalariu());
			pstmt.setDate(6, new Date(angajat.getDataAngajare().getTime()));
			pstmt.setString(7, angajat.getFunctie());
			pstmt.setInt(8, angajat.getIdDepartament());
			pstmt.setString(9, angajat.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void stergeAngajat(int idAngajat) {
		String sql = "delete from angajat where id_angajat = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idAngajat);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateAngajat(Angajat angajat) {
		StringBuilder sql = new StringBuilder("update angajat set");
		sql.append(" nume = ?,");
		sql.append(" prenume = ?,");
		sql.append(" cnp = ?,");
		sql.append(" telefon = ?,");
		sql.append(" salariu = ?,");
		sql.append(" data_angajarii = ?,");
		sql.append(" functie = ?,");
		sql.append(" id_departament = ?,");
		sql.append(" email = ?");
		sql.append(" where id_angajat = ?");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, angajat.getNume());
			pstmt.setString(2, angajat.getPrenume());
			pstmt.setString(3, angajat.getCnp());
			pstmt.setString(4, angajat.getTelefon());
			pstmt.setDouble(5, angajat.getSalariu());
			pstmt.setDate(6, new Date(angajat.getDataAngajare().getTime()));
			pstmt.setString(7, angajat.getFunctie());
			pstmt.setInt(8, angajat.getIdDepartament());
			pstmt.setString(9, angajat.getEmail());
			pstmt.setInt(10, angajat.getIdAngajat());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Angajat> getAllAngajati() {
		ArrayList<Angajat> angajati = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"select id_angajat, nume, prenume, cnp, telefon, salariu, data_angajarii, functie, a.id_departament, nume_dept, email")
						.append(" from angajat a, departament d").append(" where a.id_departament  = d.id_departament");
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql.toString());
			if (rs != null) {
				while (rs.next()) {
					Angajat angajat = new Angajat();
					angajat.setIdAngajat(rs.getInt("id_angajat"));
					angajat.setNume(rs.getString("nume"));
					angajat.setPrenume(rs.getString("prenume"));
					angajat.setCnp(rs.getString("cnp"));
					angajat.setTelefon(rs.getString("telefon"));
					angajat.setSalariu(rs.getDouble("salariu"));
					angajat.setDataAngajare(rs.getDate("data_angajarii"));
					angajat.setFunctie(rs.getString("functie"));
					angajat.setIdDepartament(rs.getInt("a.id_departament"));
					angajat.setNumeDepartament(rs.getString("nume_dept"));
					angajat.setEmail(rs.getString("email"));

					angajati.add(angajat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return angajati;
	}

	
	@Override
	public Angajat getAngajatById(int idAngajat) {
		Angajat angajat = new Angajat();
		String sql = "select * from angajat where id_angajat = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idAngajat);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				angajat.setIdAngajat(rs.getInt("id_angajat"));
				angajat.setNume(rs.getString("nume"));
				angajat.setPrenume(rs.getString("prenume"));
				angajat.setCnp(rs.getString("cnp"));
				angajat.setTelefon(rs.getString("telefon"));
				angajat.setSalariu(rs.getDouble("salariu"));
				angajat.setDataAngajare(rs.getDate("data_angajarii"));
				angajat.setFunctie(rs.getString("functie"));
				angajat.setIdDepartament(rs.getInt("id_departament"));
				angajat.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return angajat;
	}

	
	@Override
	public void verificaAngajat(Angajat angajat) {
		try {
			String sql = "select * from angajat where id_angajat = ?";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, angajat.getIdAngajat());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				updateAngajat(angajat);
			} else {
				adaugaAngajat(angajat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Angajat> getAllAngajatiByNumeOrPren(String nume) {
		ArrayList<Angajat> angajati = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"select distinct id_angajat, nume, prenume, cnp, telefon, salariu, data_angajarii, functie, a.id_departament, nume_dept, email")
						.append(" from angajat a, departament d").append(" where a.id_departament  = d.id_departament")
						.append(" and (lower(nume) like ? or lower(prenume) like ?)");
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%" + nume + "%");
			ps.setString(2, "%" + nume + "%");
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Angajat angajat = new Angajat();
					angajat.setIdAngajat(rs.getInt("id_angajat"));
					angajat.setNume(rs.getString("nume"));
					angajat.setPrenume(rs.getString("prenume"));
					angajat.setCnp(rs.getString("cnp"));
					angajat.setTelefon(rs.getString("telefon"));
					angajat.setSalariu(rs.getDouble("salariu"));
					angajat.setDataAngajare(rs.getDate("data_angajarii"));
					angajat.setFunctie(rs.getString("functie"));
					angajat.setIdDepartament(rs.getInt("a.id_departament"));
					angajat.setNumeDepartament(rs.getString("nume_dept"));
					angajat.setEmail(rs.getString("email"));

					angajati.add(angajat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return angajati;
	}

	
	@Override
	public ArrayList<Angajat> getAllAngajatiBetweenDate() {
		ArrayList<Angajat> angajati = new ArrayList<>();
		String sql = "select * from angajat where MONTH(DATE_FORMAT(data_angajarii, '%Y%d%m')) = MONTH(CURRENT_DATE())";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Angajat angajat = new Angajat();
					angajat.setIdAngajat(rs.getInt("id_angajat"));
					angajat.setNume(rs.getString("nume"));
					angajat.setPrenume(rs.getString("prenume"));
					angajat.setDataAngajare(rs.getDate("data_angajarii"));
					angajat.setFunctie(rs.getString("functie"));
					angajat.setEmail(rs.getString("email"));

					angajati.add(angajat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return angajati;
	}
}
