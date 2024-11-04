package model;

/**
 *
 * @author dudaf
 */
public class Consulta {
    private int id;
    private int id_paciente;
    private int id_funcionario;
    private String procedimento;
    private String data;
    private String hora;
    private String observacao;
    
    private String nomePaciente;
    private String cpfPaciente;
    private String nomeFuncionario;
    private String cpfFuncionario;

    public Consulta(int id_paciente, int id_funcionario, String procedimento, String data, String hora, String observacao) {
        this.id_paciente = id_paciente;
        this.id_funcionario = id_funcionario;
        this.procedimento = procedimento;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
    }

    // usado no upate para recriar com o mesmo id
    public Consulta(int id, int id_paciente, int id_funcionario, String procedimento, String data, String hora, String observacao) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.id_funcionario = id_funcionario;
        this.procedimento = procedimento;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
    }
    
    // Usado para listagem de consultas
    public Consulta(int id, String nomePaciente, String cpfPaciente, String nomeFuncionario, String procedimento, String data, String hora, String observacao) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
        this.nomeFuncionario = nomeFuncionario;
        this.procedimento = procedimento;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }
    
    
}
