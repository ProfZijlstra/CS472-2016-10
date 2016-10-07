/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs472.w1d5.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mzijlstra
 */
public class UserDao  {

    public boolean addUser(Connection con, String name, String pass) throws SQLException {
        String sql = "INSERT INTO vuln VALUES(NULL, '" + name + "', '" + pass + "')";
        try (Statement stmt = con.createStatement()) {
            if (stmt.executeUpdate(sql) == 0) {
                return false;
            }
            return true;
        }
    }

    public boolean existsUser(Connection con, String name) throws SQLException {
        String sql = "SELECT * FROM vuln WHERE name = '" + name + "'";
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return true;
                }

            }
        }
        return false;

    }

    public String login(Connection con, String name, String pass) throws SQLException {
        String sql = "SELECT name FROM vuln WHERE name = '" + name + 
                "' AND pass = '" + pass + "'" ;
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        }
        return null;
    }

}
