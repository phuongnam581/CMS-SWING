package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import object.Member;

public class ModelMember {
	public Member getUser(String email) throws SQLException {
		Connection conect = DBConnect.getConnection();
		String sql = "SELECT * FROM member WHERE email=?";
		PreparedStatement ps = conect.prepareCall(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		Member member = new Member();
		while (rs.next()) {
			member.setId(rs.getInt("id"));
			member.setFirstname(rs.getString("firstname"));
			member.setLastname(rs.getString("lastname"));
			member.setPassword(rs.getString("password"));
			member.setPhone(rs.getString("phone"));
			member.setEmail(rs.getString("email"));
			member.setDescription(rs.getString("description"));
			member.setCreatedDate(rs.getDate("createdDate"));
			member.setUpdateTime(rs.getDate("updateTime"));
		}
		return member;
	}

	public boolean updateMember(Member member, int id) throws SQLException {
		Connection connect = DBConnect.getConnection();
		try {
			String sql = "UPDATE member SET firstname = ?,  lastname = ?, email=?,phone=?,description=?,updateTime=? WHERE id=?";
			PreparedStatement ps = connect.prepareCall(sql);
			ps.setString(1, member.getFirstname());
			ps.setString(2, member.getLastname());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getDescription());
			ps.setDate(6, new java.sql.Date((new Date()).getTime()));
			ps.setInt(7, id);
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public int insertMember(Member member) {
		Connection connection;
		connection = DBConnect.getConnection();
		String sql_insert = "insert into cms.member (`username`, `email`, `Password`, `createddate`) values ( '"
				+ member.getUsername() + "','" + member.getEmail() + "','" + member.getPassword() + "','"
				+ member.getCreatedDate() + "')";
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			return statement.executeUpdate(sql_insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public boolean checkExistEmail(String email) {
		Connection connection;
		connection = DBConnect.getConnection();
		String sql = "select * from cms.member where email = '" +email + "'";
		
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			
			 ResultSet resultSet= statement.executeQuery(sql);
			 if (resultSet.first()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	public boolean checkExistUsername(String username) {
		Connection connection;
		connection = DBConnect.getConnection();
		String sql = "select * from cms.member where email = '" +username + "'";
		
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			
			 ResultSet resultSet= statement.executeQuery(sql);
			 if (resultSet.first()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	public boolean checkLogin(String email,String pass) {
		Connection connection;
		connection = DBConnect.getConnection();
		String sql = "select password from cms.member where email = '" + email
		+ "'and password = '" + pass + "'";
		
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			
			 ResultSet resultSet= statement.executeQuery(sql);
			 if (resultSet.first()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
