package model;

/**
 *
 * @author dudaf
 */
public abstract class Pessoa {
    private int id;
    private String cpf;
    private String dtNascimento;
    private String nome;
    private String email;
    private String sexo;
    private String tipoUsuario;

    public Pessoa(String cpf, String dtNascimento, String nome, String email, String sexo, String tipoUsuario) {
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
    }
    
    // usado no upate para recriar com o mesmo id
    public Pessoa(int id, String cpf, String dtNascimento, String nome, String email, String sexo, String tipoUsuario) {
        this.id = id;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipo_usuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
