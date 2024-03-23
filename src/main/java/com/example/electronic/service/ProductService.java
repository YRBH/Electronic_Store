package com.example.electronic.service;


import com.example.electronic.model.Product;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final String url = "jdbc:mysql://localhost:3306/electronic_store";
    private final String user = "root";
    private final String password = "root";

    public boolean createProduct(String name, int count, double price, int grade) {

        String query = "INSERT INTO products (name, count, price, grade, date) VALUES (?,?,?,?,CURRENT_DATE)";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, count);
            ps.setDouble(3, price);
            ps.setInt(4, grade);

            int rowsInserted = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCount(rs.getInt("count"));
                product.setPrice(rs.getDouble("price"));
                product.setGrade(rs.getInt("grade"));
                product.setDate(rs.getDate("date"));
                products.add(product);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product findProductById(int id) {
        Product product = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM products WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCount(rs.getInt("count"));
                product.setPrice(rs.getDouble("price"));
                product.setGrade(rs.getInt("grade"));
                product.setDate(rs.getDate("date"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean deleteProductById(int id) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM products WHERE id = ?";
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

    public boolean updateProductInfo(int id ,String name, int count, double price, int grade){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String query = "UPDATE products SET name = ?, count = ?,price = ?,grade = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setInt(2,count);
            ps.setDouble(3,price);
            ps.setInt(4,grade);
            ps.setInt(5,id);

            int rowsAffected = ps.executeUpdate();
            ps.close();
            conn.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
