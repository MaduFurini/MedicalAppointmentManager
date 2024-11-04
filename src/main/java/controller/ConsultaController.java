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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Consulta;

/**
 *
 * @author dudaf
 */
public class ConsultaController {
  
    public static List<Consulta> index() {
        List<Consulta> consultas = new ArrayList<>();

        String searchSql = "SELECT consultas.id AS consulta_id, " +
                           "paciente.nome AS paciente_nome, " +
                           "paciente.cpf AS paciente_cpf, " +
                           "funcionario.nome AS funcionario_nome, " +
                           "consultas.procedimento, " +
                           "consultas.data, " +
                           "consultas.hora, " +
                           "consultas.observacao " +
                           "FROM consultas " +
                           "JOIN pessoas AS paciente ON consultas.id_paciente = paciente.id " +
                           "JOIN pessoas AS funcionario ON consultas.id_funcionario = funcionario.id";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(searchSql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("consulta_id");
                String nomePaciente = rs.getString("paciente_nome");
                String cpfPaciente = rs.getString("paciente_cpf");
                String nomeFuncionario = rs.getString("funcionario_nome");
                String procedimento = rs.getString("procedimento");
                String data = rs.getString("data");
                String hora = rs.getString("hora");
                String observacao = rs.getString("observacao");

                Consulta consulta = new Consulta(id, nomePaciente, cpfPaciente, nomeFuncionario, procedimento, data, hora, observacao);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas " + e.getMessage());
            
            return null;
        }
        return consultas;
    }

    
    public static boolean store(String paciente, String funcionario, String procedimento, String data, String hora, String observacao) {
        String sqlSearch = "SELECT COUNT(*) FROM consultas WHERE data = ? AND hora = ?"; 

        String sqlSearchPaciente = "SELECT id FROM pessoas WHERE cpf = ?";
        String sqlSearchFuncionario = "SELECT id FROM pessoas WHERE cpf = ?";
        String sqlStore = "INSERT INTO consultas(id_paciente, id_funcionario, procedimento, data, hora, observacao) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection()) {
            String[] partesPaciente = paciente.split(" - ");
            if (partesPaciente.length < 2) {
                JOptionPane.showMessageDialog(null, "Formato de paciente inválido. Certifique-se de que contém 'Nome - CPF'.");
                return false;
            }
            String cpfPaciente = partesPaciente[1];

            String[] partesFuncionario = funcionario.split(" - ");
            if (partesFuncionario.length < 2) {
                JOptionPane.showMessageDialog(null, "Formato de funcionário inválido. Certifique-se de que contém 'Nome - CPF'.");
                return false;
            }
            String cpfFuncionario = partesFuncionario[1];

            int idPaciente = getIdByCpf(con, sqlSearchPaciente, cpfPaciente);
            int idFuncionario = getIdByCpf(con, sqlSearchFuncionario, cpfFuncionario);

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(data);
            String formattedDate = outputFormat.format(date);

                        
            try (PreparedStatement search = con.prepareStatement(sqlSearch)) {
                search.setString(1, formattedDate);
                search.setString(2, hora);

                ResultSet rs = search.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Já existe uma consulta registrada nesta data e hora");
                    return false;
                }
            }
            
            try (PreparedStatement store = con.prepareStatement(sqlStore)) {
                store.setInt(1, idPaciente);
                store.setInt(2, idFuncionario);
                store.setString(3, procedimento);
                store.setString(4, formattedDate);
                store.setString(5, hora);
                store.setString(6, observacao);

                if (store.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta");
                    return false;
                }
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data ou hora: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar consulta: " + e.getMessage());
            return false;
        }
    }

    public static boolean update(int id, String paciente, String funcionario, String procedimento, String data, String hora, String observacao) {
    
        String sqlUpdate = "UPDATE consultas SET id_paciente = ?, id_funcionario = ?, procedimento = ?, data = ?, hora = ?, observacao = ? WHERE id = ?";
        String sqlSearch = "SELECT COUNT(*) FROM consultas WHERE data = ? AND hora = ? AND id <> ?"; 

        try (Connection con = Conexao.getConnection()) {
            String[] partesPaciente = paciente.split(" - ");
            if (partesPaciente.length < 2) {
                JOptionPane.showMessageDialog(null, "Formato de paciente inválido. Certifique-se de que contém 'Nome - CPF'.");
                return false;
            }
            String cpfPaciente = partesPaciente[1];

            String[] partesFuncionario = funcionario.split(" - ");
            if (partesFuncionario.length < 2) {
                JOptionPane.showMessageDialog(null, "Formato de funcionário inválido. Certifique-se de que contém 'Nome - CPF'.");
                return false;
            }
            String cpfFuncionario = partesFuncionario[1];

            int idPaciente = getIdByCpf(con, "SELECT id FROM pessoas WHERE cpf = ?", cpfPaciente);
            int idFuncionario = getIdByCpf(con, "SELECT id FROM pessoas WHERE cpf = ?", cpfFuncionario);

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(data);
            String formattedDate = outputFormat.format(date);
            
            try (PreparedStatement search = con.prepareStatement(sqlSearch)) {
                search.setString(1, formattedDate);
                search.setString(2, hora);
                search.setInt(3, id);

                ResultSet rs = search.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Já existe uma consulta registrada nesta data e hora");
                    return false;
                }
            }

            try (PreparedStatement update = con.prepareStatement(sqlUpdate)) {
                update.setInt(1, idPaciente);
                update.setInt(2, idFuncionario);
                update.setString(3, procedimento);
                update.setString(4, formattedDate);
                update.setString(5, hora);
                update.setString(6, observacao);
                update.setInt(7, id);

                if (update.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Consulta atualizada com sucesso");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar consulta");
                    return false;
                }
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data ou hora: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar consulta: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean destroy(int id) {
        String sqlDelete = "DELETE FROM consultas WHERE id = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlDelete);
            search.setInt(1, id);

            if (search.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Consulta excluída com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir consulta");
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir consulta: " + e.getMessage());
            return false;
        }
    }
    
    public static Consulta show(int id) {
        String searchSql = "SELECT * FROM consultas WHERE id = ?";

    try (Connection con = Conexao.getConnection();
         PreparedStatement stmt = con.prepareStatement(searchSql)) {
        
        stmt.setInt(1, id); 

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id_paciente = rs.getInt("id_paciente");
            int id_funcionario = rs.getInt("id_funcionario");
            String procedimento = rs.getString("procedimento");
            String data = rs.getString("data");
            String hora = rs.getString("hora");
            String observacao = rs.getString("observacao");

            return new Consulta(id_paciente, id_funcionario, procedimento, data, hora, observacao);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar consulta: " + e.getMessage());
    }

    return null; 
}

    
    private static int getIdByCpf(Connection con, String sql, String cpf) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return 0; 
    }
}