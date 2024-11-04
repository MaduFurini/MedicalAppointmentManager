package model;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import connection.Conexao;
import javax.swing.JOptionPane;
import view.LoginView;

/**
 *
 * @author dudaf
 */
public class Main {
    public static void main(String args[]) {
        String dbName = JOptionPane.showInputDialog(null, "Digite um nome para banco de dados:", "Configuração do Banco de Dados", JOptionPane.QUESTION_MESSAGE);
        String dbUser = JOptionPane.showInputDialog(null, "Digite o usuário:", "Configuração do Banco de Dados", JOptionPane.QUESTION_MESSAGE);
        String dbPassword = JOptionPane.showInputDialog(null, "Digite a senha para acessar o banco:", "Configuração do Banco de Dados", JOptionPane.QUESTION_MESSAGE);

        if (!dbName.isEmpty() && !dbUser.isEmpty() && !dbPassword.isEmpty()) {
            Conexao.initialize(dbName, dbUser, dbPassword);
            Conexao.initializeDatabase();

            JOptionPane.showMessageDialog(null, "Banco configurado e criado com sucesso", "Sucesso", JOptionPane.DEFAULT_OPTION);

            LoginView login = new LoginView();
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}
