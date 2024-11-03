package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dudaf
 */
public class Initializer {
    private static final String DB_NAME = "medicalManager";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:mysql://localhost/";
    
    public static void initializeDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = con.createStatement()) {

                stmt.execute("DROP DATABASE IF EXISTS " + DB_NAME);
                stmt.execute("CREATE DATABASE " + DB_NAME);
                System.out.println("Banco de dados criado com sucesso!");
            }

            try (Connection con = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
                 Statement stmt = con.createStatement()) {

                String createPessoasTable = "CREATE TABLE IF NOT EXISTS pessoas (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(100) NOT NULL, " +
                        "senha VARCHAR(50), " +
                        "cpf VARCHAR(14) NOT NULL UNIQUE, " + 
                        "sexo ENUM('MASCULINO', 'FEMININO') NOT NULL, " +  
                        "dt_nascimento DATE NOT NULL, " +  
                        "endereco VARCHAR(255), " +  
                        "crm VARCHAR(20), " +
                        "cargo VARCHAR(255), " +
                        "tipo_usuario ENUM('PACIENTE', 'FUNCIONARIO') NOT NULL" +
                        ")";
                stmt.execute(createPessoasTable);

                String createConsultasTable = "CREATE TABLE IF NOT EXISTS consultas (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "id_paciente INT NOT NULL, " +
                        "id_funcionario INT NOT NULL, " +
                        "procedimento VARCHAR(255) NOT NULL, " +
                        "data DATE NOT NULL, " +
                        "hora VARCHAR(8) NOT NULL, " +
                        "observacao VARCHAR(255), " +
                        "FOREIGN KEY (id_paciente) REFERENCES pessoas (id) ON DELETE CASCADE, " +
                        "FOREIGN KEY (id_funcionario) REFERENCES pessoas (id) ON DELETE CASCADE" +
                        ")";
                stmt.execute(createConsultasTable);

                System.out.println("Tabelas criadas com sucesso!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do banco de dados não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao criar banco ou tabelas: " + e.getMessage());
        }
    }
}
