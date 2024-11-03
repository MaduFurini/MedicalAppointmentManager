package model;

/**
 *
 * @author dudaf
 */
public class Funcionario extends Pessoa {
    private String crm;
    private String cargo;

    public Funcionario(String crm, String cargo, String cpf, String dtNascimento, String nome, String email, String sexo, String tipoUsuario) {
        super(cpf, dtNascimento, nome, email, sexo, tipoUsuario);
        this.crm = crm;
        this.cargo = cargo;
    }
    
    // usado no upate para recriar com o mesmo id
    public Funcionario(int id, String crm, String cargo, String cpf, String dtNascimento, String nome, String email, String sexo, String tipoUsuario) {
        super(id, cpf, dtNascimento, nome, email, sexo, tipoUsuario);
        this.crm = crm;
        this.cargo = cargo;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
