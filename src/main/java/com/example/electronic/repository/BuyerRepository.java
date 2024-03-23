package com.example.electronic.repository;

import com.example.electronic.model.Buyer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuyerRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private final String user = "root";
    @Value("${spring.datasource.password}")
    private final String password = "root";

    public boolean createBuyer(Buyer buyer){

        String query = "INSERT INTO buyer (name, age, date_of_birthday, registration_date) VALUES (?,?,?,CURRENT_DATE)";

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1,buyer.getName());
            ps.setInt(2,buyer.getAge());
            ps.setDate(3, buyer.getDateOfBirthday());

            int rowsInserted = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Buyer> getBuyers() {
        List<Buyer> buyers = new ArrayList<>();

        String query = "SELECT * FROM buyer";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Buyer buyer = new Buyer();
                buyer.setId(rs.getInt("id"));
                buyer.setName(rs.getString("name"));
                buyer.setAge(rs.getInt("age"));
                buyer.setDateOfBirthday(rs.getDate("date_of_birthday"));
                buyer.setRegistrationDate(rs.getDate("registration_date"));
                buyers.add(buyer);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyers;
    }

    public Buyer findProductById(int id) {
        Buyer buyer = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM buyer WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                buyer = new Buyer();
                buyer.setId(rs.getInt("id"));
                buyer.setName(rs.getString("name"));
                buyer.setAge(rs.getInt("age"));
                buyer.setDateOfBirthday(rs.getDate("date_of_birthday"));
                buyer.setRegistrationDate(rs.getDate("registration_date"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyer;
    }

    public boolean deleteBuyerById(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM buyer WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBuyerInfo(Buyer buyer) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE buyer SET name = ?, age = ?, date_of_birthday = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, buyer.getName());
            ps.setInt(2, buyer.getAge());
            ps.setDate(3, buyer.getDateOfBirthday());
            ps.setInt(4, buyer.getId());

            int rowsAffected = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
