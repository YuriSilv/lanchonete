package com.yuri.cliente;


public class Extrato extends Pedidos{
    
    private String nome;
    
    public Extrato(float valorTotal, String cpf, String nome) {
        super(valorTotal, cpf);
        this.nome = nome;
    }

    /**
     * 
     * @return retorna o nome do cliente no extrato
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome define o nome do cliente no extrato
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Extrato{" + "nome=" + nome + ", cpf=" + super.getCpf() + ", valor=" + super.getValorTotal() + '}';
    }
    
    
}
