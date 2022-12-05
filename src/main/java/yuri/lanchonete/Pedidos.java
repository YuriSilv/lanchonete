package yuri.lanchonete;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
public class Pedidos {

    ////////////////////// ATRIBUTOS //////////////////////
    private final static String[] statusPossiveis = {"em preparo", "pronto", "a caminho", "entregue", "devolvido"};
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private String dataPedido;
    private int horarioEntrega;
    private int horarioPedido;
    private float valor;
    private String status;
    private String cpf;
    private int id;
    private static int controlID;

    public Pedidos(float valor, String cpf) {
        dataPedido = LocalDate.now().toString();
        horarioPedido = LocalTime.now().getHour();
        this.valor = valor;
        this.status = statusPossiveis[0];
        this.cpf = cpf;
        setId();
    }

    ////////////////////// MÉTODOS //////////////////////
   
    public void atualizarStatus(int estadoAtual) {
        if(estadoAtual==1){
            setStatus(statusPossiveis[0]);
        }
        if(estadoAtual==2){
            setStatus(statusPossiveis[1]);
        }
        if(estadoAtual==3){
            setStatus(statusPossiveis[2]);
        }
        if(estadoAtual==4){
            setStatus(statusPossiveis[3]);
        }
        if(estadoAtual==5){
            setStatus(statusPossiveis[4]);
        }
    }

    // GETTERS E SETTERS 

    public float getValor() {
        return valor;
    }
    
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(int horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public int getHorarioPedido() {
        return horarioPedido;
    }

    public void setHorarioPedido(int horarioPedido) {
        this.horarioPedido = horarioPedido;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = controlID;
        controlID++;
    }

    public static int getControlID() {
        return controlID;
    }

    public static void setControlID(int controlID) {
        Pedidos.controlID = controlID;
    }

    
    
}
