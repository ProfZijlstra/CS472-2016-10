/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs472.w1d5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mzijlstra
 */
public class UserDao {

    public boolean addUser(Connection con, String name, String token) throws SQLException {
        String sql = "INSERT INTO user VALUES(NULL, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, token);
            if (ps.executeUpdate() == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean existsUser(Connection con, String name) throws SQLException {
        String sql = "SELECT * FROM user WHERE name = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getToken(Connection con, String name) throws SQLException {
        String sql = "SELECT pass FROM user WHERE name = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("pass");
                }
            }
        }
        return null;
    }

}
