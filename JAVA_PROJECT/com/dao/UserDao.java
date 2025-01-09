/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;
import java.sql.SQLException;
/**
 *
 * @author mehakklunkar
 */
public class UserDao {

	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean register(User u) {
		boolean f = false;

		try {
			String sql = "insert into User_details(full_name,email,password) values(?,?,?) ";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getFullName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} 
                catch (SQLException e) {
			e.printStackTrace();
		}

		return f;
        }
        public User login(String em, String psw) {
		User u = null;

		try {
                    System.out.println("Attempting to log in with Email: [" + em + "], Password: [" + psw + "]");
                    
                    String sql = "SELECT * FROM user_details WHERE TRIM(email) = TRIM(?) AND TRIM(password) = TRIM(?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, em.trim());
                    ps.setString(2, psw.trim());

                    System.out.println("Executing Query: " + ps); // Logs the query and parameters
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) { // Use `if` to fetch a single record
                        u = new User();
                        u.setId(rs.getInt("id")); // Ensure column names match
                        u.setFullName(rs.getString("full_name"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(rs.getString("password"));

                        System.out.println("User Found: " + u.getEmail());
                    } else {
                        System.out.println("No user found for email: " + em + " and password: " + psw);
                    }
                } catch (SQLException e) {
                    System.out.println("SQL Exception: " + e.getMessage());
                    e.printStackTrace();
                }

                return u;
            }
            public boolean checkOldPassword(int userid, String oldPassword) {
		boolean f = false;

		try {
			String sql = "select * from user_dtls where id=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, oldPassword);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public boolean changePassword(int userid, String newPassword) {
		boolean f = false;

		try {
			String sql = "update user_dtls set password=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, userid);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
}