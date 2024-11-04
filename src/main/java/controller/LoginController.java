/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dudaf
 */
public class LoginController {
   public static boolean verify(String email, String senha) {
        String sqlSearch = "SELECT senha, tipo_usuario FROM pessoas WHERE email = ?";

        try (Connection con = Conexao.getConnection()) {
            PreparedStatement search = con.prepareStatement(sqlSearch);
            search.setString(1, email);

            ResultSet rs = search.executeQuery();

            if (rs.next()) {
                String senhaArmazenada = rs.getString("senha");
                String tipoUsuario = rs.getString("tipo_usuario"); 

                if (senhaArmazenada.equals(senha)) {
                    if ("FUNCIONARIO".equals(tipoUsuario)) {
                        return true; 
                    } else {
                        JOptionPane.showMessageDialog(null, "Acesso negado. Você não tem permissão para acessar o sistema.");
                        return false; 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta");
                    return false; 
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário: " + e.getMessage());
            return false;
        }

        return false; 
    }

}
