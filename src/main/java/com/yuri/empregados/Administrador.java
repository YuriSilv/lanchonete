package com.yuri.empregados;


public class Administrador extends Funcionario{
    
    /**
    *
    * @param nome define um nome para um objeto administrador
    * @param cpf define um cpf para um objeto administrador
    * @param senha define a senha do administrador
    */
    public Administrador(String nome, String cpf, String senha, boolean isAdm){
        super(nome, cpf, senha, isAdm);
    }
}
