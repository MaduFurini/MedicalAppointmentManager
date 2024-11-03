package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dudaf
 */
public class Conexao {
    
    private static final String DB_NAME = "medicalManager";
    private static final String USER = "Furini";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:mysql://localhost/";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
