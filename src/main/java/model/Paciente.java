package model;

/**
 *
 * @author dudaf
 */
public class Paciente extends Pessoa{
    private String endereco;

    public Paciente(String endereco, String cpf, String dtNascimento, String nome, String email, String sexo, String tipoUsuario) {
        super(cpf, dtNascimento, nome, email, sexo, tipoUsuario);
        this.endereco = endereco;
    }

    // usado no upate para recriar com o mesmo id
    public Paciente(int id, String endereco, String cpf, String dtNascimento, String nome, String email, String sexo, String tipoUsuario) {
        super(id, cpf, dtNascimento, nome, email, sexo, tipoUsuario);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
