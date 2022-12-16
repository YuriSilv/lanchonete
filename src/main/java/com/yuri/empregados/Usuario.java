package com.yuri.empregados;

/**
 *
 * @author yurid
 */
public abstract class Usuario {
    private String nome;
    private String cpf;

    /**
    *
    * @param nome define um nome para um objeto usuario
    * @param cpf define um cpf para um objeto usuario
    */
    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
        
    
    /**
    * 
    * @return retorna o nome do usuário
    */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome define o nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return retorna o cpf do usuário
     */
    public String getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf define o cpf do usuário
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", cpf=" + cpf + '}';
    }
}
