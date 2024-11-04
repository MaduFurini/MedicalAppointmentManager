package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static String dbName; 
    private static String user;
    private static String password; 
    private static final String URL = "jdbc:mysql://localhost/";

    public static void initialize(String dbNameInput, String userInput, String passwordInput) {
        dbName = dbNameInput;
        user = userInput;
        password = passwordInput;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL + dbName, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static void initializeDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, user, password);
                 Statement stmt = con.createStatement()) {

                stmt.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
                System.out.println("Banco de dados criado com sucesso!");
            }

            try (Connection con = DriverManager.getConnection(URL + dbName, user, password);
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
                
                String createAdminUser = "INSERT INTO pessoas (nome, email, senha, cpf, dt_nascimento, tipo_usuario) " +
                        "VALUES ('Usuário Admin', 'user@admin.com.br', '123', '397.603.968-45', '2003-12-03', 'FUNCIONARIO')";
                stmt.execute(createAdminUser);

                System.out.println("Tabelas criadas com sucesso!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver do banco de dados não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao criar banco ou tabelas: " + e.getMessage());
        }
    }
}
