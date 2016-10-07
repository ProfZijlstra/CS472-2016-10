/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mzijlstra
 */
public class TestJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(getStreet(100));
    }

    public static List<String> getStreet(int id) {
        List<String> streets = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs544", "root", "root");
                PreparedStatement ps = createPreparedStatement(con, id); 
                ResultSet rs = ps.executeQuery()) {

             while(rs.next()) {
                 streets.add(rs.getString("Street"));
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streets;
    }

    private static PreparedStatement createPreparedStatement(Connection con, int userId) throws SQLException {
        String sql = "SELECT street FROM address WHERE number = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps;
    }
}
