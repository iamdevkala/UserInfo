package com.vastika.userinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.vastika.userinfo.model.User;
import com.vastika.userinfo.util.DbConnection;

public class UserDaoImpl implements UserDao {
	final String INSERT_SQL = "insert into user_tbl(user_name, password , email , gender,  hobbies, nationality ,dob) values(?,?,?,?,?,?,?)";
	final String SELECT_SQL = "select * from user_tbl";
	public static final String DELETE_SQL = "delete from user_dbl where id = ?";
	public static final String UPDATE_SQL = "update from user_dbl where id = ?";
	@Override

	public int saveUser(User user) {

		int saved = 0;
		try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_SQL);

		) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getGender());
			ps.setString(5, user.getHobbies());
			ps.setString(6, user.getNationality());
			ps.setDate(7, new Date(user.getDob().getTime()));
			saved = ps.executeUpdate();
			ps.get

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return saved;
	}

	@Override
	public List<User> getAllUserInfo() {
		List<User> userList= new ArrayList<>();
		
		try(Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_SQL);){
			ResultSet rs =ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setHobbies(rs.getString("hobbies"));
				user.setEmail(rs.getString("email"));
				user.setNationality(rs.getString ("nationality"));
				user.setDob(rs.getDate("dob"));
				userList.add(user);
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public void deleteUserInfo(int id) {
		try(
				Connection con = DbConnection.getConnection();
				PreparedStatement ps = con.PrepareStatement(DELETE_SQL);
				) {
			try {
				ps.setInt(1, id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

}
