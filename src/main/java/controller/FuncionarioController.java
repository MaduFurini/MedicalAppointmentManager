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
import model.Funcionario;

/**
 *
 * @author dudaf
 */
public class FuncionarioController {

    public static List<Funcionario> index() {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        String searchSql = "SELECT * FROM pessoas WHERE tipo_usuario = 'FUNCIONARIO'";
        
        try (Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(searchSql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = parseInt(rs.getString("id"));
                String crm = rs.getString("crm");
                String cargo = rs.getString("cargo");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String sexo = rs.getString("sexo");
                String dtNascimento = rs.getString("dt_nascimento");
                String tipoUsuario = rs.getString("tipo_usuario");
                
                Funcionario funcionario = new Funcionario(id, crm, cargo, senha, cpf, dtNascimento, nome, email, sexo, tipoUsuario);
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários " + e.getMessage());
            
            return null;
        }
        System.out.println(funcionarios);
        return funcionarios;
    }
    
    public static boolean store(String crm, String cargo, String senha, String cpf, String dtNascimento, String nome, String email, String sexo) {
        System.out.println(sexo);
        String sqlSearch = "SELECT COUNT(*) FROM pessoas WHERE cpf = ? OR email = ?";
        String sqlStore = "INSERT INTO pessoas(crm, cargo, senha, nome, email, cpf, sexo, dt_nascimento, tipo_usuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            
            search.setString(1, cpf);
            search.setString(2, email);
            
            ResultSet rs = search.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Já existe um usuário registrado com este CPF ou email");
                
                return false;
            }

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dtNascimento); 
            String formattedDate = outputFormat.format(date); 
            
            Funcionario newFuncionario = new Funcionario(crm, cargo, senha, cpf, formattedDate, nome, email, sexo, "FUNCIONARIO");

            PreparedStatement store = con.prepareStatement(sqlStore);
            
            store.setString(1, newFuncionario.getCrm());
            store.setString(2, newFuncionario.getCargo());
            store.setString(3, newFuncionario.getSenha());
            store.setString(4, newFuncionario.getNome());
            store.setString(5, newFuncionario.getEmail());
            store.setString(6, newFuncionario.getCpf());
            store.setString(7, newFuncionario.getSexo());
            store.setString(8, newFuncionario.getDtNascimento());
            store.setString(9, newFuncionario.getTipoUsuario());

            if (store.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário");

                return false;
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data: " + e.getMessage());
            
            return false;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar funcionário " + e.getMessage());

            return false;
        }
    }   
    
    public static boolean update(int id, String crm, String cargo, String senha, String cpf, String dtNascimento, String nome, String email, String sexo) {
    
        String sqlSearch = "SELECT COUNT(*) FROM pessoas WHERE (cpf = ? OR email = ?) AND id != ?";
        String sqlUpdate = "UPDATE pessoas SET crm = ?, cargo = ?, senha = ?, nome = ?, email = ?, cpf = ?, sexo = ?, dt_nascimento = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            search.setString(1, cpf);
            search.setString(2, email);
            search.setInt(3, id);

            ResultSet rs = search.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Já existe um funcionário registrado com este CPF ou email");
                return false;
            }

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dtNascimento); 
            String formattedDate = outputFormat.format(date); 

            PreparedStatement update = con.prepareStatement(sqlUpdate);
            update.setString(1, crm);
            update.setString(2, cargo);
            update.setString(3, senha);
            update.setString(4, nome);
            update.setString(5, email);
            update.setString(6, cpf);
            update.setString(7, sexo);
            update.setString(8, formattedDate);
            update.setInt(9, id);

            if (update.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data: " + e.getMessage());
            return false;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean destroy(int id) {
        String sqlDelete = "DELETE FROM pessoas WHERE id = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlDelete);
            search.setInt(1, id);

            if (search.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionário: " + e.getMessage());
            return false;
        }
    }
    
    public static Funcionario show(int id) {
        String searchSql = "SELECT * FROM pessoas WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(searchSql)) {

            stmt.setInt(1, id); 

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String crm = rs.getString("crm");
                String cargo = rs.getString("cargo");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String sexo = rs.getString("sexo");
                String dtNascimento = rs.getString("dt_nascimento");
                String tipoUsuario = rs.getString("tipo_usuario");

                return new Funcionario(crm, cargo, senha, cpf, dtNascimento, nome, email, sexo, tipoUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }

        return null; 
    }
}
