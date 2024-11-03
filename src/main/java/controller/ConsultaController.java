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
//    PacienteController pacienteController = new PacienteController();
//    FuncionarioController funcionarioController = new FuncionarioController();
//    
//    ArrayList<Paciente> pacientes = pacienteController.getPacientes();
//    ArrayList<Funcionario> funcionarios = funcionarioController.getFuncionarios();
//    public ArrayList<Consulta> consultas = new ArrayList<>();
//    
//    public String show(Object identificador) {
//        int id = Integer.parseInt(identificador.toString());
//        String nome_paciente = null;
//        String nome_funcionario = null;
//
//        // procura a consulta pelo id passado
//        for(int i = 0; i <= consultas.size(); i++){
//            if(consultas.get(i).getId() == id){
//                Consulta consulta = consultas.get(i);
//
//                // procurando o paciente da consulta
//                for(int p = 0; p <= pacientes.size(); p++){
//                    if(pacientes.get(p).getId() == consulta.getId_paciente()){
//                        nome_paciente = pacientes.get(p).getNome();
//
//                        // se achou o paciente procura o funcionario
//                        for(int f = 0; f <= funcionarios.size(); f++){
//                            if(funcionarios.get(f).getId() == consulta.getId_funcionario()){
//                                nome_funcionario = funcionarios.get(p).getNome();
//                            }
//                            else{
//                                return "Funcionario responsável pela consulta não foi encontrado";
//                            }
//                        }
//                    }
//                    else{
//                        return "Paciente dessa consulta não foi encontrado";
//                    }
//                }
//                return "Paciente: " + nome_paciente + 
//                        "Funcionário: " + nome_funcionario +
//                        "Procedimento: " + consulta.getProcedimento() +
//                        "Data: " + consulta.getData() + 
//                        "Hora: " + consulta.getHora() + 
//                        "Observação: " + consulta.getObservacao();
//            }
//        }
//        return "Consulta não encontrada";
//    }
//
//    public boolean store(int id_paciente, int id_funcionario, String procedimento, String data, String hora, String observacao) {
//        try{
//            Consulta newConsulta = new Consulta(id_paciente, id_funcionario, procedimento, data, hora, observacao);
//            consultas.add(newConsulta);
//            return true;
//        }
//        catch (Exception e){
//            return false;
//        }
//    }
//    
//    public boolean update(int id, int id_paciente, int id_funcionario, String procedimento, String data, String hora, String observacao) {
//        for(int i = 0; i <= consultas.size(); i++){
//            if(consultas.get(i).getId() == id){
//                try{
//                    Consulta updateConsulta = new Consulta(id, id_paciente, id_funcionario, procedimento, data, hora, observacao);
//                    consultas.set(i, updateConsulta);
//                    System.out.println("Consulta atualizada");
//                    return true;
//                }
//                catch (Exception e){
//                    System.out.println("Não foi possivel atualizar essa consulta");
//                    return false;
//                }
//            }
//        }
//        System.out.println("Consulta não encontrada");
//        return false;
//    }
//    
//    public boolean destroy(int id){
//        for(int i = 0; i <= consultas.size(); i++){
//            if(consultas.get(i).getId() == id){
//                try{
//                    consultas.remove(i);
//                    System.out.println("Consulta apagada");
//                    return true;
//                }
//                catch (Exception e){
//                    System.out.println("Não foi possivel apagar essa consulta");
//                    return false;
//                }
//            }
//        }
//        System.out.println("Consulta não encontrada");
//        return false;
//    }
    
    public static List<Consulta> index() {
        List<Consulta> consultas = new ArrayList<>();
        
        String searchSql = "SELECT * FROM consultas";
        
        try (Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(searchSql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = parseInt(rs.getString("id"));
                int id_paciente = parseInt(rs.getString("id_paciente"));
                int id_funcionario = parseInt(rs.getString("id_funcionario"));
                String procedimento = rs.getString("procedimento");
                String data = rs.getString("data");
                String hora = rs.getString("hora");
                String observacao = rs.getString("observacao");
                
                Consulta consulta = new Consulta(id, id_paciente, id_funcionario, procedimento, data, hora, observacao);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas " + e.getMessage());
            
            return null;
        }
        return consultas;
    }
    
    public static boolean store(int id_paciente, int id_funcionario, String procedimento, String data, String hora, String observacao) {
        
        String sqlSearch = "SELECT COUNT(*) FROM consultas WHERE cpf = ? OR email = ?";
        String sqlStore = "INSERT INTO consultas(id_paciente, id_funcionario, procedimento, data, hora, observacao) VALUES(?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            
            search.setString(1, data);
            search.setString(2, hora);
            
            ResultSet rs = search.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Já existe uma consulta registrada nesta data e hora");
                
                return false;
            }

            //formatar data
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(data); 
            String formattedDate = outputFormat.format(date);
            // formatar hora
            DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hour = LocalTime.parse(hora, hourFormat);
            String formattedHour = hour.toString();
            
            Consulta newConsulta = new Consulta(id_paciente, id_funcionario, procedimento, formattedDate, formattedHour, observacao);

            PreparedStatement store = con.prepareStatement(sqlStore);
            
            store.setInt(1, newConsulta.getId_paciente());
            store.setInt(2, newConsulta.getId_funcionario());
            store.setString(3, newConsulta.getProcedimento());
            store.setString(4, newConsulta.getData());
            store.setString(5, newConsulta.getHora());
            store.setString(6, newConsulta.getObservacao());

            if (store.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta");

                return false;
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data ou hora: " + e.getMessage());
            
            return false;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar consulta" + e.getMessage());

            return false;
        }
    }   
    
    public static boolean update(int id, int id_paciente, int id_funcionario, String procedimento, String data, String hora, String observacao) {
    
        String sqlSearch = "SELECT COUNT(*) FROM consultas WHERE (data = ? OR hora = ?) AND id != ?";
        String sqlUpdate = "UPDATE consultas SET id_paciente = ?, id_funcionario = ?, procedimento = ?, data = ?, hora = ?, observacao = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            search.setString(1, data);
            search.setString(2, hora);
            search.setInt(3, id);
 
            ResultSet rs = search.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Já existe uma consulta registrada nesta data e hora");
                return false;
            }
 
            //formatar data
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(data); 
            String formattedDate = outputFormat.format(date);
            // formatar hora
            DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hour = LocalTime.parse(hora, hourFormat);
            String formattedHour = hour.toString();

            PreparedStatement update = con.prepareStatement(sqlUpdate);
            update.setInt(1, id_paciente);
            update.setInt(2, id_funcionario);
            update.setString(3, procedimento);
            update.setString(4, formattedDate);
            update.setString(5, formattedHour);
            update.setString(6, observacao);
            update.setInt(7, id);

            if (update.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Consulta atualizada com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar consulta");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar data ou hora: " + e.getMessage());
            return false;
        } catch(SQLException e) {
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
}