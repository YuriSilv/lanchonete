package com.yuri.cliente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Extrato extends Pedidos{
    
    private String nome;
   
    
    public Extrato(float valorTotal, String cpf, String nome) {
        super(valorTotal, cpf);
        this.nome = nome;
        final DateFormat df = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        final String dataFormatada = df.format(c1.getTime());
        super.setDataPedido(dataFormatada);
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
