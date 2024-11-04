package controller;
import connection.Conexao;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.sql.Connection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Paciente;

/**
 *
 * @author dudaf
 */
public class PacienteController {
    
    public static List<Paciente> index() {
        List<Paciente> pacientes = new ArrayList<>();
        
        String searchSql = "SELECT * FROM pessoas WHERE tipo_usuario = 'PACIENTE'";
        
        try (Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(searchSql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = parseInt(rs.getString("id"));
                String endereco = rs.getString("endereco");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String sexo = rs.getString("sexo");
                String dtNascimento = rs.getString("dt_nascimento");
                String tipoUsuario = rs.getString("tipo_usuario");
                

                Paciente paciente = new Paciente(id, endereco, cpf, dtNascimento, nome, email, sexo, tipoUsuario);
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes " + e.getMessage());
            
            return null;
        }
        return pacientes;
    }
    
    public static boolean store(String rua, int numero, String bairro, String cidade, 
                                String estado, String cpf, String nome, String email, String sexo, String dt_nascimento) {
        
        String sqlSearch = "SELECT COUNT(*) FROM pessoas WHERE cpf = ? OR email = ?";
        String sqlStore = "INSERT INTO pessoas(nome, email, cpf, sexo, endereco, dt_nascimento, tipo_usuario) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            
            search.setString(1, cpf);
            search.setString(2, email);
            
            ResultSet rs = search.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Já existe um paciente registrado com este CPF ou email");
                
                return false;
            }
            
            String endereco = rua + ", " + numero + ", " + bairro + ", " + cidade + ", " + estado;
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dt_nascimento); 
            String formattedDate = outputFormat.format(date); 
            
            Paciente newPaciente = new Paciente(endereco, cpf, formattedDate, nome, email, sexo, "PACIENTE");
            
            PreparedStatement store = con.prepareStatement(sqlStore);
            
            store.setString(1, newPaciente.getNome());
            store.setString(2, newPaciente.getEmail());
            store.setString(3, newPaciente.getCpf());
            store.setString(4, newPaciente.getSexo());
            store.setString(5, newPaciente.getEndereco());
            store.setString(6, newPaciente.getDtNascimento());
            store.setString(7, newPaciente.getTipoUsuario());

            if (store.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente");

                return false;
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data: " + e.getMessage());
            
            return false;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar paciente" + e.getMessage());

            return false;
        }
    }   
    
    public static boolean update(int id, String rua, int numero, String bairro, String cidade, 
                                 String estado, String cpf, String nome, String email, String sexo, String dt_nascimento) {
    
        String sqlSearch = "SELECT COUNT(*) FROM pessoas WHERE (cpf = ? OR email = ?) AND id != ?";
        String sqlUpdate = "UPDATE pessoas SET nome = ?, email = ?, cpf = ?, sexo = ?, endereco = ?, dt_nascimento = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            search.setString(1, cpf);
            search.setString(2, email);
            search.setInt(3, id);

            ResultSet rs = search.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Já existe um paciente registrado com este CPF ou email");
                return false;
            }

            String endereco = rua + ", " + numero + ", " + bairro + ", " + cidade + ", " + estado;

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dt_nascimento); 
            String formattedDate = outputFormat.format(date); 

            PreparedStatement update = con.prepareStatement(sqlUpdate);
            update.setString(1, nome);
            update.setString(2, email);
            update.setString(3, cpf);
            update.setString(4, sexo);
            update.setString(5, endereco);
            update.setString(6, formattedDate);
            update.setInt(7, id);

            if (update.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data: " + e.getMessage());
            return false;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean destroy(int id) {
        String sqlDelete = "DELETE FROM pessoas WHERE id = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlDelete);
            search.setInt(1, id);

            if (search.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir paciente");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente: " + e.getMessage());
            return false;
        }
    }
    
    public static Paciente show(int id) {
        String searchSql = "SELECT * FROM pessoas WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(searchSql)) {

            stmt.setInt(1, id); 

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String endereco = rs.getString("endereco");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String sexo = rs.getString("sexo");
                String dtNascimento = rs.getString("dt_nascimento");
                String tipoUsuario = rs.getString("tipo_usuario");
                
                return new Paciente(endereco, cpf, dtNascimento, nome, email, sexo , tipoUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }

        return null; 
    }
}
