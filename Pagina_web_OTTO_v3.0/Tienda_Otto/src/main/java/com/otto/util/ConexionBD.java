package com.otto.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tu_basededatos", 
                "usuario", 
                "contraseña"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
