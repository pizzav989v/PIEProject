package com.gestiunemagazin.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gestiunemagazin.dao.DepartamentDAO;
import com.gestiunemagazin.model.Departament;
import com.gestiunemagazin.util.DbUtil;



public class DepartamentDAOImpl implements DepartamentDAO {

	private Connection connection;

	
	public DepartamentDAOImpl() {
		connection = DbUtil.getConnection();
	}

	
	@Override
	public void adaugaDepartament(Departament departament) {
		String sql = "insert into departament(nume_dept) values (?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, departament.getNumeDepartament());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void stergeDepartament(int idDepartament) {
		String sql = "delete from departament where id_departament = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idDepartament);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateDepartament(Departament departament) {
		StringBuilder sql = new StringBuilder("update departament set");
		sql.append(" nume_dept = ?");
		sql.append(" where id_departament = ?");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, departament.getNumeDepartament());
			pstmt.setInt(2, departament.getIdDepartament());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ArrayList<Departament> getAllDepartamente() {
		ArrayList<Departament> departamente = new ArrayList<>();
		String sql = "select * from departament";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Departament departament = new Departament();
					departament.setIdDepartament(rs.getInt("id_departament"));
					departament.setNumeDepartament(rs.getString("nume_dept"));

					departamente.add(departament);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departamente;
	}

	
	@Override
	public Departament getDepartamentById(int idDepartament) {
		Departament departament = new Departament();
		String sql = "select * from departament where id_departament = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, idDepartament);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				departament.setIdDepartament(rs.getInt("id_departament"));
				departament.setNumeDepartament(rs.getString("nume_dept"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departament;
	}

	
	@Override
	public void verificaDepartament(Departament departament) {
		try {
			String sql = "select * from departament where id_departament = ?";
			System.out.println(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, departament.getIdDepartament());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				updateDepartament(departament);
			} else {
				adaugaDepartament(departament);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
